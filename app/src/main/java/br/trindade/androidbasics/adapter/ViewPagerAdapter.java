package br.trindade.androidbasics.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.trindade.androidbasics.ui.fragment.BoxOfficeFragment;

/**
 * @author maiko.trindade
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    FragmentManager fragmentManager;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    public Fragment getItem(int num) {
        return BoxOfficeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "BoxOffice";
    }
}
