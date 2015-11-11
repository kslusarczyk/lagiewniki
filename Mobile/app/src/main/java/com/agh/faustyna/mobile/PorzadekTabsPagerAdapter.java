package com.agh.faustyna.mobile;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Karolina on 2015-11-10.
 */
public class PorzadekTabsPagerAdapter extends FragmentPagerAdapter {

    public PorzadekTabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new KaplicaFragment();
            case 1:
                return new BazylikaFragment();
            case 2:
                return new UroczystosciFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
