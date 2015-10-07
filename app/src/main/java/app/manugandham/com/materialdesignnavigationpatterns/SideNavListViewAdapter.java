package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SideNavListViewAdapter extends BaseAdapter {
    Context context;
    String[] data;
    int[] icons;
    private static LayoutInflater inflater = null;

    public SideNavListViewAdapter(Context context, String[] data, int[] icons) {
        this.context = context;
        this.data = data;
        this.icons = icons;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }
    @Override
    public Object getItem(int position) {
        return data[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.item_row, null);
        TextView text = (TextView) vi.findViewById(R.id.rowText);
        ImageView image = (ImageView) vi.findViewById(R.id.rowIcon);
        text.setText(data[position]);
        image.setImageResource(icons[position]);
        return vi;
    }
}