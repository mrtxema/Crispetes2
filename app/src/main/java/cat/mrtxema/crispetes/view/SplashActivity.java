package cat.mrtxema.crispetes.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity implements Runnable {
    private static final long SPLASH_DISPLAY_MILLIS = 3000;

    @AfterViews
    void showSplash() {
        new Handler().postDelayed(this, SPLASH_DISPLAY_MILLIS);
    }

    public void run() {
        goToMainActivity();
    }

    void goToMainActivity() {
        MainActivity_.intent(this).start();
        this.finish();
    }
}
