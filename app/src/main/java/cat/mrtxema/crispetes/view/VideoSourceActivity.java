package cat.mrtxema.crispetes.view;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import cat.mrtxema.crispetes.model.LoginParameter;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.model.VideoSourceDescriptor;
import cat.mrtxema.crispetes.service.VideoSourceService;
import cat.mrtxema.crispetes.service.VideoSourceServiceException;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_videosource)
public class VideoSourceActivity extends BaseActivity {
    @Bean
    VideoSourceService videoSourceService;
    @Extra
    VideoSourceDescriptor videoSourceDescriptor;
    @Extra
    VideoSource videoSource;
    @ViewById
    ViewGroup lstParameters;
    private Map<String, EditText> formFields;

    @AfterViews
    void initViews() {
        setTitle(videoSourceDescriptor.getName());
        formFields = new HashMap<>();
        for (LoginParameter parameter : videoSourceDescriptor.getLoginParameters()) {
            String parameterValue = videoSource == null ? null : videoSource.getCredentials().get(parameter.getKey());
            lstParameters.addView(inflateView(parameter, parameterValue));
        }
    }

    private View inflateView(LoginParameter parameter, String value) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View rowView = inflater.inflate(R.layout.listitem_form, null, false);
        TextView fieldLabel = (TextView) rowView.findViewById(R.id.fieldLabel);
        fieldLabel.setText(parameter.getLabel());
        EditText formField = (EditText) rowView.findViewById(R.id.fieldInput);
        if (parameter.isPassword()) {
            formField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        if (value != null) {
            formField.setText(value);
        }
        formFields.put(parameter.getKey(), formField);
        return rowView;
    }

    @Click
    void btnCancel() {
        finish();
    }

    @Click
    void btnOk() {
        saveCredentials();
    }

    private Map<String, String> getFormParameters() {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, EditText> entry : formFields.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getText().toString());
        }
        return result;
    }

    @Background
    void saveCredentials() {
        try {
            if (videoSource == null) {
                VideoSource toSave = VideoSource.builder()
                        .setBaseUrl(videoSourceDescriptor.getBaseUrl())
                        .setCode(videoSourceDescriptor.getCode())
                        .setName(videoSourceDescriptor.getName())
                        .setSupportsMovies(videoSourceDescriptor.isSupportsMovies())
                        .setSupportsTvShows(videoSourceDescriptor.isSupportsTvShows())
                        .setCredentials(getFormParameters())
                        .build();
                videoSourceService.save(toSave);
            } else {
                VideoSource toSave = VideoSource.builder()
                        .setId(videoSource.getId())
                        .setBaseUrl(videoSource.getBaseUrl())
                        .setCode(videoSource.getCode())
                        .setName(videoSource.getName())
                        .setSupportsMovies(videoSource.isSupportsMovies())
                        .setSupportsTvShows(videoSource.isSupportsTvShows())
                        .setCredentials(getFormParameters())
                        .build();
                videoSourceService.update(toSave);
            }
            finishActivity();
        } catch (VideoSourceServiceException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    @UiThread
    void finishActivity() {
        finish();
    }
}
