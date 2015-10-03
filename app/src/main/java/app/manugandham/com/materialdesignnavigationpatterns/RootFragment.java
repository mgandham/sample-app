package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class RootFragment extends Fragment {
    protected FragmentListener mListener;

    public interface FragmentListener {
        void updateToolbarTitle(String title);
    }
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Activity activity = getActivity();
        try {
            mListener = (FragmentListener) activity;
        } catch (ClassCastException e) {
            Log.d("F interface monitor", (activity.toString() + " must implement FragmentListener"));
        }
    }

}
