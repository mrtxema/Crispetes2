package cat.mrtxema.crispetes.service;

import java.sql.SQLException;
import java.util.List;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.service.converter.ListConverter;
import cat.mrtxema.crispetes.store.DatabaseHelper;
import cat.mrtxema.crispetes.store.DtoConverter;
import cat.mrtxema.crispetes.store.VideoSourceDto;
import com.j256.ormlite.dao.Dao;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

@EBean
public class VideoSourceService {
    @OrmLiteDao(helper = DatabaseHelper.class)
    Dao<VideoSourceDto, Integer> videoSourceDao;

    public VideoSource save(VideoSource videoSource) throws VideoSourceServiceException {
        VideoSourceDto videoSourceDto =  new VideoSourceDto().fromModel(videoSource);
        try {
            if (videoSourceDto.getOrder() == 0) {
                videoSourceDto.setOrder((int) videoSourceDao.countOf() + 1);
            }
            videoSourceDao.create(videoSourceDto);
        } catch (SQLException e) {
            throw new VideoSourceServiceException("Can't save video source", e);
        }
        return videoSourceDto.toModel();
    }

    public VideoSource update(VideoSource videoSource) throws VideoSourceServiceException {
        VideoSourceDto videoSourceDto =  new VideoSourceDto().fromModel(videoSource);
        try {
            videoSourceDao.update(videoSourceDto);
        } catch (SQLException e) {
            throw new VideoSourceServiceException("Can't update video source", e);
        }
        return videoSourceDto.toModel();
    }

    public List<VideoSource> retrieveAll() throws VideoSourceServiceException {
        try {
            List<VideoSourceDto> dtos = videoSourceDao.queryBuilder()
                    .orderBy(VideoSourceDto.FIELD_ORDER, true)
                    .query();
            return new ListConverter<>(new DtoConverter<VideoSource>()).convert(dtos);
        } catch (SQLException e) {
            throw new VideoSourceServiceException("Can't retrieve video sources", e);
        }
    }
}
