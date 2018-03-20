package www.jwalin.com.rjv1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * Created by BRAHMPRAKASHARORA on 3/19/2018.
 */


import java.util.List;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.NetworkImageView;

public class AdapterForPlaces extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Places> placesList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public AdapterForPlaces(Activity activity, List<Places> placesList) {
        this.activity = activity;
        this.placesList = placesList;
    }

    @Override
    public int getCount() {
        return placesList.size();
    }

    @Override
    public Object getItem(int location) {
        return placesList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.layout, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView type = (TextView) convertView.findViewById(R.id.Type);
        //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting place data for the row
        Places m = placesList.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnail(), imageLoader);

        // title
        title.setText(m.getName());

        // rating
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        // type
        type.setText(m.getType());


        // release year
        //year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}
