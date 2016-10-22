package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import cat.mrtxema.crispetes.model.Crew;
import cat.mrtxema.crispetes.view.R;

public class CrewListAdapter extends BaseListAdapter<Crew> {

    public CrewListAdapter(Context context, List<Crew> crewList) {
        super(context, R.layout.listitem_cast, crewList);
    }

    @Override
    protected void populateView(View view, Crew crew) {
        TextView.class.cast(view.findViewById(R.id.txtName)).setText(crew.getName());
        TextView.class.cast(view.findViewById(R.id.txtCharacter)).setText(crew.getJob());
    }
}
