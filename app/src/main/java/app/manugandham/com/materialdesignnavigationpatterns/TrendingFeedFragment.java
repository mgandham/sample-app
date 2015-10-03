package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class TrendingFeedFragment extends FeedFragment {
    public static Fragment newInstance() {
        FeedFragment fragmentFirst = new TrendingFeedFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onResume(){
        super.onResume();
        mListener.updateToolbarTitle(getString(R.string.trending_title));
    }
}
