package cat.mrtxema.crispetes.service;

import cat.mrtxema.crispetes.api.videos.PluginVideoServiceManager;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean
public class VideoService {
    @Bean
    PluginVideoServiceManager pluginVideoServiceManager;

}
