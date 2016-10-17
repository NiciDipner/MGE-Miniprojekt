package ch.hsr.mge.gadgeothek.presentation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new GadgetListFragment();
            case 1:
                return new GadgetListFragment();
            case 2:
                return new GadgetListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Gadgets";
            case 1:
                return "Ausgeliehen";
            case 2:
                return "Reserviert";
            default:
                return null;
        }
    }
}
