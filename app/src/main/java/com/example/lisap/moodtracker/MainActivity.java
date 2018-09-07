package com.example.lisap.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;



public class MainActivity extends AppCompatActivity {

   private MyAdapter mAdapter;
   private ViewPager mPager;
   private SharedPreferences mPreferences;
   private static final String PREF_KEY_MOOD = "PREF_KEY_MOOD";

// VERTICAL SLIDE //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                int day = Calendar.getInstance().get(Calendar.DATE);
                int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                int year =  Calendar.getInstance().get(Calendar.YEAR);

                String date = day + "" + month + "" + year;

                mPreferences.edit().putInt(date+PREF_KEY_MOOD,position).apply();
            }
            @Override
            public void onPageSelected(int position) {
                Log.e("OnPageSelected",position +" ");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("Onpagescroll",state +" ");
            }
        });

    }
}
