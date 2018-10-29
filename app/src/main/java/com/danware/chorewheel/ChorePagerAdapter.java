package com.danware.chorewheel;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChorePagerAdapter extends FragmentPagerAdapter {

    public ChorePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        ChoreFragment choreFragment = new ChoreFragment();

        switch (i) {
            case 0:
                return choreFragment;
            case 1:
                return choreFragment;
            case 2:
                return choreFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
