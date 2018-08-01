package com.example.lisap.moodtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

   private MyAdapter mAdapter;
   private ViewPager mPager;
   private SharedPreferences mPreferences;
   private static final String PREF_KEY_MOOD = "PREF_KEY_MOOD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);
        mPreferences = getPreferences(MODE_PRIVATE);


        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE,-2);
                Log.e("tag", Calendar.getInstance().get(Calendar.MONTH)+"");
                Log.e("tag",cal.get(Calendar.DATE)+"");

                mPreferences.edit().putInt(PREF_KEY_MOOD,position).apply();
            }
            @Override
            public void onPageSelected(int position) {}
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

    }
    //sharedPreferences preferences = getSharedPreferences("nom fichier", MODE_PRIVATE);
}
