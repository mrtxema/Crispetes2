package cat.mrtxema.crispetes.view;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    protected boolean isMainActivity() {
        return true;
    }

    @Click(R.id.btnAdd)
    void onClickAdd() {
        SearchMovieActivity_.intent(this).start();
    }
}
