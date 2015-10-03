package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

public class HomeDualFeedFragment extends DualFeedFragment {

    private final int number_of_pages = 2;

    public static Fragment newInstance(){
        return new HomeDualFeedFragment();
    }
    @Override
    public void setAdapterViewPager(){
        adapterViewPager = new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return SampleFeedFragment.newInstance();
                    case 1:
                        return SampleFeedFragment.newInstance();
                    default:
                        return null;
                }
            }
            @Override
            public int getCount() {
                return number_of_pages;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return getString(R.string.top_title);
                    case 1:
                        return getString(R.string.new_title);
                }
                return null;
            }
        };
        viewPager.setAdapter(adapterViewPager);
    }
    @Override
    public void onResume(){
        super.onResume();
        mListener.updateToolbarTitle(getString(R.string.home_title));
    }
}