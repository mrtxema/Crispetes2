package cat.mrtxema.crispetes.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sample)
@OptionsMenu(R.menu.menu_sample)
public class SampleActivity extends AppCompatActivity {
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @AfterViews
    protected void bindActionBar() {
        setSupportActionBar(toolbar);
    }

    @Click(R.id.fab)
    void onClickFab(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        SearchMovieActivity_.intent(this).start();
    }

    @OptionsItem(R.id.action_settings)
    void showSettings(MenuItem item) {
        View view = getWindow().getDecorView();
        Snackbar.make(view, "Settings clicked", Snackbar.LENGTH_LONG).setAction("Click", null).show();
    }
}
