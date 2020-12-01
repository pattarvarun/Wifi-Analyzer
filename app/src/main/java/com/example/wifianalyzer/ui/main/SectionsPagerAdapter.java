package com.example.wifianalyzer.ui.main;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wifianalyzer.DeviceList;
import com.example.wifianalyzer.Frag1;
import com.example.wifianalyzer.R;
import com.example.wifianalyzer.ui.frag2.Frag2Fragment;
import com.example.wifianalyzer.ui.speed.SpeedFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES =
            new int[]{R.string.tab_text_1, /*R.string.tab_text_2,*/ R.string.Channel, /*R.string.devices*/};
    private final Context mContext;
    public Fragment[] fragments = new Fragment[TAB_TITLES.length];

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        fragments[position] = createdFragment;
        return createdFragment;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Frag1();
                break;
           /* case 1:
                fragment = new SpeedFragment();
                break;*/
            case 1:
                fragment = new Frag2Fragment();
                break;
            /*case 3:
                fragment = new DeviceList();
                break;*/
            default:
                return null;
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}