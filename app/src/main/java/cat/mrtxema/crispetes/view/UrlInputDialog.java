package cat.mrtxema.crispetes.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class UrlInputDialog implements DialogInterface.OnClickListener {
    private final Context context;
    private final EditText input;
    private final DialogCallback callback;

    public UrlInputDialog(Context context, DialogCallback callback) {
        this.context = context;
        this.input = new EditText(context);
        this.input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        this.callback = callback;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getText(R.string.enter_url));
        builder.setView(input);
        builder.setPositiveButton(context.getText(R.string.ok), this);
        builder.setNegativeButton(context.getText(R.string.cancel), this);
        builder.show();
    }

    private String buildUrl() {
        String url = input.getText().toString();
        if (!url.contains("://")) {
            url = "http://" + url;
        }
        return url;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                String url = buildUrl();
                if (!Patterns.WEB_URL.matcher(url).matches()) {
                    Toast.makeText(context, R.string.invalid_url, Toast.LENGTH_LONG).show();
                } else {
                    dialog.dismiss();
                    callback.onResult(url, false);
                }
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.cancel();
                callback.onResult(buildUrl(), true);
                break;
        }
    }
}
