package br.trindade.androidbasics.ui.swipeTabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageTabAdapter extends FragmentPagerAdapter {

	public FragmentPageTabAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {
		case 0:
			return new FirstTab();
		case 1:
			return new SecondTab();
		case 2:
			return new ThirdTab();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
