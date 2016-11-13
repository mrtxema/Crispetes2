package cat.mrtxema.crispetes.service;

import java.sql.SQLException;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.store.DatabaseHelper;
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
            videoSourceDao.create(videoSourceDto);
        } catch (SQLException e) {
            throw new VideoSourceServiceException("Can't save video source", e);
        }
        return videoSourceDto.toModel();
    }

}
