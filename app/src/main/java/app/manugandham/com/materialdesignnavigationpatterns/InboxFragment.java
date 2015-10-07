package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class InboxFragment extends FeedFragment {
    public static Fragment newInstance() {
        FeedFragment fragmentFirst = new InboxFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    @Override
    public void onResume(){
        super.onResume();
        mListener.updateToolbarTitle(getString(R.string.inbox_title));
    }
}