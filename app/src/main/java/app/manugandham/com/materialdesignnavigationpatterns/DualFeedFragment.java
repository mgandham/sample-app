package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualFeedFragment extends RootFragment {
    PagerTabStrip pagerTabStrip;
    FragmentPagerAdapter adapterViewPager;
    ViewPager viewPager;
    View view;
    Handler mHandler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dual_feed_layout, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pager_header);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.ColorPrimary));
        setAdapterViewPager();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setMenuTabs();
            }
        }, 100);
        return view;
    }

    public void setAdapterViewPager() {
        adapterViewPager = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    default:
                        return null;
                }
            }
            @Override
            public int getCount() {
                return 0;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return null;
            }
        };
        viewPager.setAdapter(adapterViewPager);
    }
    public void setMenuTabs(){

    }
}