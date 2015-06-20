package br.trindade.androidbasics.ui.swipeTabs;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

import br.trindade.androidbasics.ui.R;

public class SwipeTabsMainActivity extends ActionBarActivity implements
        ActionBar.TabListener {

    private ActionBar actionBar;
    private ViewPager viewpager;
    private FragmentPageTabAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        actionBar = getSupportActionBar();
        viewpager = (ViewPager) findViewById(R.id.pager);
        fragmentAdapter = new FragmentPageTabAdapter(getSupportFragmentManager());
        viewpager.setAdapter(fragmentAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.first_tab).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.second_tab).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.third_tab).setTabListener(this));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                actionBar.setSelectedNavigationItem(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }


    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }
}
