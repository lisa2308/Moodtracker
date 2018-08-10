package com.example.lisap.moodtracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;

public class HistoricActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;


    private static final String PREF_KEY_MOOD ="PREF_KEY_MOOD";
    private static final String PREF_KEY_COMMENT ="PREF_KEY_COMMENT";

    private int mImage;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_activity);

        mPreferences = getPreferences(MODE_PRIVATE);

        Calendar cal = Calendar.getInstance();
        for (int i=0; i<7;i++) {
            //cal.add(Calendar.DATE, -1);

            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            String date = day + "" + month + "" + year;

            int mood = mPreferences.getInt(date+PREF_KEY_MOOD, -1);


            String comment = mPreferences.getString(date+PREF_KEY_COMMENT, "default value");

            Log.e("mood", mood + "");
            Log.e("comment", comment);

          //  if mood = 1


        }

//        Calendar cal = Calendar.getInstance();
//
//        for(int i=0; i<7;i++){
//
//            Cal.add(Calendar.DATE,-1)
//
//            int day = Calendar.getInstance().get(Calendar.DATE);
//            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
//            int year =  Calendar.getInstance().get(Calendar.YEAR);
//
//            String date = day + "" + month + "" + year;
//            Log.e("tag",day+"");
//
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");
//
//            Cal.add(Calendar.DATE,-1)
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");
//
//            Cal.add(Calendar.DATE,-1)
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");
//
//            Cal.add(Calendar.DATE,-1)
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");
//
//            Cal.add(Calendar.DATE,-1)
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");
//
//            Cal.add(Calendar.DATE,-1)
//
//            day = cal.get(Calendar.DATE);
//            date = day+""+month+""+year;
//
//            Log.e("tag",date+"");}
    }

}
