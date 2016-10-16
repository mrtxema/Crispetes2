package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import cat.mrtxema.crispetes.model.Cast;
import cat.mrtxema.crispetes.view.R;

public class CastListAdapter extends BaseListAdapter<Cast> {

    public CastListAdapter(Context context, List<Cast> castList) {
        super(context, R.layout.listitem_cast, castList);
    }

    @Override
    protected void populateView(View view, Cast cast) {
        TextView.class.cast(view.findViewById(R.id.txtName)).setText(cast.getName());
        TextView.class.cast(view.findViewById(R.id.txtCharacter)).setText(cast.getCharacter());
    }
}
