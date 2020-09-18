package com.bongamnguni.hogwarts.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bongamnguni.hogwarts.HouseFragment;
import com.bongamnguni.hogwarts.R;
import com.bongamnguni.hogwarts.SpellFragment;
import com.bongamnguni.hogwarts.StudentFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Fragment fragment;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                fragment = new HouseFragment();
               //tabLayout.getTabAt(0).setIcon(R.drawable.tab_radio);
                return fragment;

            case 1:
                fragment = new SpellFragment();
                //tabLayout.getTabAt(1).setIcon(R.drawable.tab_gallery);
                return fragment;

            case 2:
                fragment = new StudentFragment();
               // tabLayout.getTabAt(2).setIcon(R.drawable.tab_video);
                return fragment;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}