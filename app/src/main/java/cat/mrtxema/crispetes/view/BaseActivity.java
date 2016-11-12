package cat.mrtxema.crispetes.view;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity
@OptionsMenu(R.menu.menu)
public abstract class BaseActivity extends AppCompatActivity {
    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @AfterViews
    protected void bindActionBar() {
        setSupportActionBar(toolbar);
        if (!isMainActivity() && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected boolean isMainActivity() {
        return false;
    }

    @OptionsItem(R.id.action_settings)
    void showSettings() {
        View view = getWindow().getDecorView();
        Snackbar.make(view, "Settings clicked", Snackbar.LENGTH_LONG).setAction("Click", null).show();
    }

    @OptionsItem(android.R.id.home)
    void goBack() {
        finish();
    }

    protected void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
