package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProfileFragment extends RootFragment {
    private ImageView userimage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        userimage = (ImageView) view.findViewById(R.id.settings_image);
        Bitmap squarebit = BitmapFactory.decodeResource(getResources(), R.drawable.manu);
        userimage.setImageBitmap(HomeScreenActivity.roundCorners(squarebit));
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        mListener.updateToolbarTitle(getString(R.string.profile_title));
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
    }
}