package com.example.lisap.moodtracker;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoricActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;

    // SHAREDPREFERENCIES KEY //
    private static final String PREF_KEY_MOOD ="PREF_KEY_MOOD";
    private static final String PREF_KEY_COMMENT ="PREF_KEY_COMMENT";

    ConstraintLayout cl1, cl2, cl3, cl4, cl5, cl6, cl7;
    ImageView img1,img2,img3,img4,img5,img6,img7;

    List<ConstraintLayout> backgroundList = new ArrayList<>();
    List<ImageView> commentImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historic_activity);

        //1/5 de la largeur d'écran//
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels / 5;

        //DAY OF WEEK //
        cl1 = findViewById(R.id.historic_activity_background1);
        cl2 = findViewById(R.id.historic_activity_background2);
        cl3 = findViewById(R.id.historic_activity_background3);
        cl4 = findViewById(R.id.historic_activity_background4);
        cl5 = findViewById(R.id.historic_activity_background5);
        cl6 = findViewById(R.id.historic_activity_background6);
        cl7 = findViewById(R.id.historic_activity_background7);

        backgroundList.add(cl1);
        backgroundList.add(cl2);
        backgroundList.add(cl3);
        backgroundList.add(cl4);
        backgroundList.add(cl5);
        backgroundList.add(cl6);
        backgroundList.add(cl7);

        // IMAGE COMMENT //
        img1 = findViewById(R.id.historic_activity_img1);
        img2 = findViewById(R.id.historic_activity_img2);
        img3 = findViewById(R.id.historic_activity_img3);
        img4 = findViewById(R.id.historic_activity_img4);
        img5 = findViewById(R.id.historic_activity_img5);
        img6 = findViewById(R.id.historic_activity_img6);
        img7 = findViewById(R.id.historic_activity_img7);

        commentImageList.add(img1);
        commentImageList.add(img2);
        commentImageList.add(img3);
        commentImageList.add(img4);
        commentImageList.add(img5);
        commentImageList.add(img6);
        commentImageList.add(img7);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //HISTORIC OF MOOD FOR THE WEEK //
        Calendar cal = Calendar.getInstance();
        for (int i=0; i<7;i++) {

            //ON ENLEVE UN JOUR A LA DATE//
            cal.add(Calendar.DATE, -1);

            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);

            String date = day + "" + month + "" + year;

            int mood = mPreferences.getInt(date+PREF_KEY_MOOD, -1);
            final String comment = mPreferences.getString(date+PREF_KEY_COMMENT, "default value");

            //AFFICHER DANS LOGCAT//
            Log.e("mood", mood + "");
            Log.e("comment", comment);
            Log.e("date",date);

            // MOOD'S COLOR //
            switch (mood) {
                case 0:
                    backgroundList.get(i).getLayoutParams().width = width;
                    backgroundList.get(i).requestLayout();
                    backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.faded_red));
                    break;
                case 1:
                    backgroundList.get(i).getLayoutParams().width = width * 2;
                    backgroundList.get(i).requestLayout();
                    backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.warm_grey));
                    break;
                case 2:
                    backgroundList.get(i).getLayoutParams().width = width * 3;
                    backgroundList.get(i).requestLayout();
                    backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.cornflower_blue_65));
                    break;
                case 3:
                    backgroundList.get(i).getLayoutParams().width = width * 4;
                    backgroundList.get(i).requestLayout();
                    backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.light_sage));
                    break;
                case 4: backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.banana_yellow));break;

                case -1:
                    backgroundList.get(i).setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                    break;
            }

            if(comment.equals("default value")){
                commentImageList.get(i).setVisibility(View.GONE);
            }
            else{
                commentImageList.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HistoricActivity.this, comment, Toast.LENGTH_SHORT).show();
                        }
                });
            }
        }
    }
}
