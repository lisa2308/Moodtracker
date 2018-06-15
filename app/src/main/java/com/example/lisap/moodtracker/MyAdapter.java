package com.example.lisap.moodtracker;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    final int NUMBER_OF_PAGES = 2;

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
                return FragmentMood.newInstance(0, Color.WHITE);
            case 1:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentMood.newInstance(1, Color.CYAN);
            default:
                return null;
        }
    }
}