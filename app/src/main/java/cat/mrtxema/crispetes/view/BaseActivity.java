package cat.mrtxema.crispetes.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

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

    @OptionsItem(R.id.menu_sources)
    void showVideoSources() {
        VideoSourceListActivity_.intent(this).start();
    }

    @OptionsItem(R.id.menu_about)
    void showAboutInfo() {
        showAboutDialog();
    }

    private void showAboutDialog() {
        TextView txtAbout = new TextView(this);
        txtAbout.setText(R.string.about_content);
        Linkify.addLinks(txtAbout, Linkify.EMAIL_ADDRESSES);
        new AlertDialog.Builder(this)
            .setTitle(R.string.about)
            .setView(txtAbout, 60, 0, 60, 0)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            })
            .show();
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
