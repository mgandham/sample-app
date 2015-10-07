package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class SampleFeedFragment extends FeedFragment {
    public static Fragment newInstance() {
        FeedFragment fragmentFirst = new SampleFeedFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
}