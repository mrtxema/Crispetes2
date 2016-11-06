package cat.mrtxema.crispetes.service;

import cat.mrtxema.crispetes.model.WatchStatus;
import com.j256.ormlite.dao.Dao;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import java.sql.SQLException;
import java.util.List;
import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.service.converter.ListConverter;
import cat.mrtxema.crispetes.store.DatabaseHelper;
import cat.mrtxema.crispetes.store.DtoConverter;
import cat.mrtxema.crispetes.store.FavoriteMovieDto;

@EBean
public class FavoriteMovieService {
    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<FavoriteMovieDto, Integer> favoriteMovieDao;

    public FavoriteMovie save(FavoriteMovie favoriteMovie) throws FavoriteMovieServiceException {
        FavoriteMovieDto favoriteMovieDto =  new FavoriteMovieDto().fromModel(favoriteMovie).setStatus(WatchStatus.PENDING);
        try {
            favoriteMovieDao.create(favoriteMovieDto);
        } catch (SQLException e) {
            throw new FavoriteMovieServiceException("Can't save movie", e);
        }
        return favoriteMovieDto.toModel();
    }

    public FavoriteMovie updateStatus(FavoriteMovie favoriteMovie, WatchStatus status) throws FavoriteMovieServiceException {
        FavoriteMovieDto favoriteMovieDto =  new FavoriteMovieDto().fromModel(favoriteMovie).setStatus(status);
        try {
            favoriteMovieDao.update(favoriteMovieDto);
        } catch (SQLException e) {
            throw new FavoriteMovieServiceException("Can't update movie", e);
        }
        return favoriteMovieDto.toModel();
    }

    public FavoriteMovie delete(FavoriteMovie favoriteMovie) throws FavoriteMovieServiceException {
        FavoriteMovieDto favoriteMovieDto =  new FavoriteMovieDto().fromModel(favoriteMovie);
        try {
            favoriteMovieDao.delete(favoriteMovieDto);
        } catch (SQLException e) {
            throw new FavoriteMovieServiceException("Can't delete movie", e);
        }
        return new FavoriteMovie(favoriteMovie.getMovie());
    }

    public List<FavoriteMovie> retrieveByStatus(WatchStatus watchStatus) throws FavoriteMovieServiceException {
        try {
            List<FavoriteMovieDto> dtos = favoriteMovieDao.queryBuilder()
                    .orderBy(FavoriteMovieDto.FIELD_TITLE, true)
                    .where().eq(FavoriteMovieDto.FIELD_STATUS, watchStatus)
                    .query();
            return new ListConverter<>(new DtoConverter<FavoriteMovie>()).convert(dtos);
        } catch (SQLException e) {
            throw new FavoriteMovieServiceException("Can't retrieve movies", e);
        }
    }
}
