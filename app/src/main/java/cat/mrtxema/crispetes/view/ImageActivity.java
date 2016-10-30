package cat.mrtxema.crispetes.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_image)
public class ImageActivity extends AppCompatActivity {
    @Extra
    String url;
    @ViewById(R.id.image)
    ImageView image;

    @AfterViews
    protected void initViews() {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_placeholder).into(image);
    }

    @Click(R.id.btnClose)
    void onCloseClick() {
        finish();
    }
}
