package app.manugandham.com.materialdesignnavigationpatterns;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Manu on 7/28/2015.
 */
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
        mListener.updateToolbarTitle("Inbox");
    }
}
