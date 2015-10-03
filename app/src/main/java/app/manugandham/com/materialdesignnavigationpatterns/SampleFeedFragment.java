package app.manugandham.com.materialdesignnavigationpatterns;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.manugandham.com.materialdesignnavigationpatterns.FeedFragment;

/**
 * Created by Manu on 7/24/2015.
 */
public class SampleFeedFragment extends FeedFragment {
    public static Fragment newInstance() {
        FeedFragment fragmentFirst = new SampleFeedFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

}
