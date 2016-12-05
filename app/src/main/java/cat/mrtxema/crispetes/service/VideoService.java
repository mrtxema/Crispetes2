package cat.mrtxema.crispetes.service;

import android.util.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import cat.mrtxema.crispetes.api.ExternalServiceException;
import cat.mrtxema.crispetes.api.videos.PluginVideoServiceException;
import cat.mrtxema.crispetes.api.videos.PluginVideoServiceManager;
import cat.mrtxema.crispetes.api.videos.PluginVideoServiceSession;
import cat.mrtxema.crispetes.api.videos.model.PluginMovieLink;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoLinkList;
import cat.mrtxema.crispetes.api.videos.model.PluginMovieLocale;
import cat.mrtxema.crispetes.api.videos.model.PluginMovieQuality;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoSourceDescriptor;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoSourceLoginParameter;
import cat.mrtxema.crispetes.model.Language;
import cat.mrtxema.crispetes.model.Link;
import cat.mrtxema.crispetes.model.LoginParameter;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.model.Quality;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.model.VideoSourceDescriptor;
import cat.mrtxema.crispetes.service.converter.Converter;
import cat.mrtxema.crispetes.service.converter.ListConverter;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean
public class VideoService {
    private static final Locale DEVICE_LOCALE = Locale.getDefault();
    private static final int NUM_RETRIES = 2;
    @Bean
    PluginVideoServiceManager pluginVideoServiceManager;
    private final Map<Integer,PluginVideoServiceSession> openSessions = new HashMap<>();

    public VideoSourceDescriptor getVideoSourceInfo(String baseUrl) throws VideoServiceException {
        try {
            PluginVideoSourceDescriptor apiDescriptor = pluginVideoServiceManager.getVideoSourceInfo(baseUrl, DEVICE_LOCALE);
            return VideoSourceDescriptor.builder()
                    .setBaseUrl(baseUrl)
                    .setCode(apiDescriptor.getCode())
                    .setName(apiDescriptor.getName())
                    .setDescription(apiDescriptor.getDescription())
                    .setSupportsMovies(apiDescriptor.isSupportsMovies())
                    .setSupportsTvShows(apiDescriptor.isSupportsTvShows())
                    .setRegistrationUrl(apiDescriptor.getRegistrationUrl())
                    .setLoginParameters(new ListConverter<>(new LoginParameterConverter()).convert(apiDescriptor.getLoginParameters()))
                    .build();
        } catch (PluginVideoServiceException e) {
            throw new VideoServiceException(e.getMessage(), e);
        }
    }

    private PluginVideoServiceSession getSession(VideoSource videoSource) throws VideoServiceException {
        PluginVideoServiceSession result = openSessions.get(videoSource.getId());
        if (result == null) {
            try {
                result = pluginVideoServiceManager.getVideoServiceSession(videoSource);
            } catch (ExternalServiceException e) {
                throw new VideoServiceException(e.getMessage(), e);
            }
            openSessions.put(videoSource.getId(), result);
        }
        return result;
    }

    public boolean checkCredentials(VideoSource videoSource) {
        try {
            openSessions.remove(videoSource.getId());
            return getSession(videoSource) != null;
        } catch (VideoServiceException e) {
            return false;
        }
    }

    public void initConnection(VideoSource videoSource) {
        try {
            getSession(videoSource);
        } catch (VideoServiceException e) {
            Log.e(getClass().getSimpleName(), "Can't connect to " + videoSource.getBaseUrl());
        }
    }

    public List<Link> getMovieLinks(MovieDetails movieDetails, VideoSource videoSource) throws VideoServiceException {
        for (int i=0; i<NUM_RETRIES; i++) {
            try {
                PluginVideoLinkList links = getSession(videoSource).getMovieLinks(movieDetails.getTmdbId(), movieDetails.getImdbId(), DEVICE_LOCALE);
                return new ListConverter<>(new LinkConverter(videoSource)).convert(links.getLinks());
            } catch (PluginVideoServiceException e) {
                if (!e.isAuthenticationError()) {
                    throw new VideoServiceException(e.getMessage(), e);
                }
            }
        }
        throw new VideoServiceException("Couldn't init session at " + videoSource.getName());
    }

    private static class LinkConverter implements Converter<PluginMovieLink,Link> {
        private final VideoSource videoSource;

        public LinkConverter(VideoSource videoSource) {
            this.videoSource = videoSource;
        }

        @Override
        public Link convert(PluginMovieLink apiLink) {
            return Link.builder()
                    .setVideoSource(videoSource)
                    .setId(apiLink.getId())
                    .setServer(apiLink.getServer())
                    .setAudioLanguage(buildLanguage(apiLink.getAudioLanguage()))
                    .setSubtitles(apiLink.isSubtitles())
                    .setSubtitleLanguage(buildLanguage(apiLink.getSubtitleLanguage()))
                    .setVideoQuality(buildQuality(apiLink.getVideoQuality()))
                    .setAudioQuality(buildQuality(apiLink.getAudioQuality()))
                    .build();
        }

        private Language buildLanguage(PluginMovieLocale apiLanguage) {
            return apiLanguage == null ?
                    null :
                    new Language(
                        apiLanguage.getLanguageCode(), apiLanguage.getLanguageName(),
                        apiLanguage.getCountryCode(), apiLanguage.getCountryName()
                    );
        }

        private Quality buildQuality(PluginMovieQuality apiQuality) {
            return apiQuality == null ?
                    null :
                    new Quality(apiQuality.getLevel(), apiQuality.getDescription());
        }
    }

    private static class LoginParameterConverter implements Converter<PluginVideoSourceLoginParameter,LoginParameter> {
        @Override
        public LoginParameter convert(PluginVideoSourceLoginParameter apiLoginParameter) {
            return new LoginParameter(apiLoginParameter.getKey(), apiLoginParameter.getLabel(), apiLoginParameter.isPassword());
        }
    }
}
