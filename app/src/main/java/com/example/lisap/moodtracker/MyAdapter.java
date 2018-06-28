package com.example.lisap.moodtracker;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    final int NUMBER_OF_PAGES = 5;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentMood.newInstance(R.drawable.smiley_disappointed,R.color.faded_red);
            case 1:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentMood.newInstance(R.drawable.smiley_sad,R.color.warm_grey);
            case 2:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentMood.newInstance(R.drawable.smiley_normal, R.color.cornflower_blue_65);
            case 3:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentMood.newInstance(R.drawable.smiley_happy, R.color.light_sage);
            case 4:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentMood.newInstance(R.drawable.smiley_super_happy, R.color.banana_yellow);

            default:
                return null;
        }
    }
}