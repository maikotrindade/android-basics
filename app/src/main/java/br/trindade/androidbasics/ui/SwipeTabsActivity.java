package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import br.trindade.androidbasics.adapter.FragmentPageTabAdapter;

/**
 * @author maiko.trindade
 */
public class SwipeTabsActivity extends BaseActivity implements
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
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
