package cat.mrtxema.crispetes.service;

import android.util.Log;

import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.Collections;
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

    public int save(FavoriteMovie movie) {
        FavoriteMovieDto favoriteMovieDto =  new FavoriteMovieDto().fromModel(movie);
        try {
            return favoriteMovieDao.create(favoriteMovieDto);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Can't save movie", e);
            return 0;
        }
    }

    public List<FavoriteMovie> retrieveAll() {
        try {
            return new ListConverter<>(new DtoConverter<FavoriteMovie>()).convert(favoriteMovieDao.queryForAll());
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Can't retrieve movies", e);
            return Collections.emptyList();
        }
    }
}
