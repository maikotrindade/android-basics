package br.trindade.androidbasics.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import br.trindade.androidbasics.adapter.ViewPagerAdapter;

public class VolleyActivity extends BaseActivity {

    private ViewPager mPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
    }
}
