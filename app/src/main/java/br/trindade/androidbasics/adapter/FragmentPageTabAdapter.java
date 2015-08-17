package br.trindade.androidbasics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.trindade.androidbasics.ui.fragment.FirstTabFragment;
import br.trindade.androidbasics.ui.fragment.SecondTabFragment;
import br.trindade.androidbasics.ui.fragment.ThirdTabFragment;

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
			return new FirstTabFragment();
		case 1:
			return new SecondTabFragment();
		case 2:
			return new ThirdTabFragment();

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
