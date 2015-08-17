package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.trindade.androidbasics.adapter.FragmentPageTabAdapter;

/**
 * @author maiko.trindade
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static class SwipeTabsMainActivity extends ActionBarActivity implements
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
}
