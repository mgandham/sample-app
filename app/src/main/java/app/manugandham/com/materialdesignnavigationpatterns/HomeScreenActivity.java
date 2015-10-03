package app.manugandham.com.materialdesignnavigationpatterns;

/**
 * Created by Manu on 10/2/2015.
 */

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Stack;

public class HomeScreenActivity extends AppCompatActivity implements RootFragment.FragmentListener{
    private boolean savePageToHistory; //Used to add viewPager frags to custom "backstack"
    private Stack<Integer> pageHistory;
    private int currentPage = 0;
    private String currentToolbarTitle = "Vida"; //used for state-tracking
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ListView drawerListView;
    private SideNavListViewAdapter mAdapter;
    private FragmentPagerAdapter adapterViewPager;
    private Toolbar toolbar;
    private ImageView userImage; //For Nav drawer header
    private TextView toolBarTitle;
    private ViewPager viewPager;
    private Handler mHandler;
    private FragmentManager supportFragmentManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerListView = (ListView) findViewById(R.id.navList);
        userImage = (ImageView) findViewById(R.id.side_nav_profile_pic);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.findViewById(R.id.toolbar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        addDrawerItems();
        setupDrawer();
        viewPager = (ViewPager) findViewById(R.id.home_profile_switcher);

        adapterViewPager = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new HomeBaseFragment();
                    case 1:
                        return new ProfileFragment();
                    default:
                        return null;
                }
            }
            @Override
            public int getCount() {
                return 2;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                return null;
            }
        };
        viewPager.setAdapter(adapterViewPager);
        pageHistory = new Stack<>();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { //Use this manage the backstack
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        toolBarTitle.setText(currentToolbarTitle);
                        if (!pageHistory.empty() && pageHistory.peek()==position)
                            pageHistory.pop(); //duplicate page removed from backstack
                        break;
                    case 1: //Profile is swiped open, add home page to backstack
                        toolBarTitle.setText(getString(R.string.profile_title));
                        if (savePageToHistory) {
                            pageHistory.push(currentPage);
                        }
                        break;
                }
                currentPage=position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        savePageToHistory = true;
        supportFragmentManager = getSupportFragmentManager();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Fragment homeDualFeedFragment = HomeDualFeedFragment.newInstance();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, homeDualFeedFragment, "HomeDualFeedFragment");
                transaction.commit();
                updateToolbarTitle(getString(R.string.home_title));
                viewPager.setCurrentItem(0);
            }
        }, 100);
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            if (pageHistory.empty())
                super.onBackPressed();
            else {
                savePageToHistory = false;
                viewPager.setCurrentItem(pageHistory.pop());
                savePageToHistory = true;
            }
        }
    }
    private void addDrawerItems() {
        //Bitmap squareBit = getImageFromInternalStorage(genID);
        String[] osArray = { getString(R.string.home_title), getString(R.string.trending_title), getString(R.string.inbox_title), getString(R.string.profile_title)};
        int[] iconArray = {R.drawable.ic_action_home_dark,R.drawable.ic_action_trending_up_dark,R.drawable.ic_action_email_dark,R.drawable.ic_action_emoticon_dark};
        mAdapter = new SideNavListViewAdapter(this, osArray, iconArray);
        drawerListView.setAdapter(mAdapter);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        loadHomeTab();
                        break;
                    case 1:
                        loadTrendingTab();
                        break;
                    case 2:
                        loadInboxTab();
                        break;
                    case 3:
                        loadProfileTab();
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(drawerView.equals(mDrawerLayout)) {
                    super.onDrawerSlide(drawerView, slideOffset);
                }
            }
            public void onDrawerOpened(View drawerView) {
                if(drawerView.equals(mDrawerLayout)) {
                    super.onDrawerOpened(drawerView);
                }
            }
            public void onDrawerClosed(View drawerView) {
                if(drawerView.equals(mDrawerLayout)) {
                    super.onDrawerClosed(drawerView);
                }
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.material_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            loadHomeTab();
        }
        if (id == R.id.action_trending) {
            loadTrendingTab();
        }
        if (id == R.id.action_profile) {
            loadProfileTab();
        }
        if (id == R.id.action_inbox) {
            loadInboxTab();
        }
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    public void loadTrendingTab(){
        Fragment currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (!(currentFragment instanceof TrendingFeedFragment)) {
            Fragment trendingFeedFragment = TrendingFeedFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, trendingFeedFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            updateToolbarTitle(getString(R.string.trending_title));
        }
        viewPager.setCurrentItem(0);
    }
    public void loadHomeTab(){
        Fragment currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (!(currentFragment instanceof HomeDualFeedFragment)) {
            Fragment homeDualFeedFragment = HomeDualFeedFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, homeDualFeedFragment, "HomeDualFeedFragment");
            transaction.addToBackStack(null);
            transaction.commit();
            updateToolbarTitle(getString(R.string.home_title));
        }
        viewPager.setCurrentItem(0);
    }
    public void loadInboxTab(){
        Fragment currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container);
        if (!(currentFragment instanceof InboxFragment)) {
            Fragment inboxFragment = InboxFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, inboxFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            updateToolbarTitle(getString(R.string.inbox_title));
        }
        viewPager.setCurrentItem(0);
    }
    public void loadProfileTab(){
        if (viewPager.getCurrentItem() != 1) {
            viewPager.setCurrentItem(1, true);
            toolBarTitle.setText(getString(R.string.profile_title));
        }
    }
    public void updateToolbarTitle(String title) {
        currentToolbarTitle = title;
        toolBarTitle.setText(currentToolbarTitle);
    }
}