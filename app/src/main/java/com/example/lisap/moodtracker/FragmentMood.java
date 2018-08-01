package com.example.lisap.moodtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class FragmentMood extends Fragment {

    private static final String DRAWABLE = "image";
    private static final String MY_COLOR_KEY = "color";
    private static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";

    private int mImage;
    private int mColor;
    private ImageView comment;
    private SharedPreferences mPreferences;

    // You can modify the parameters to pass in whatever you want
    public static FragmentMood newInstance(int image, int color) {
        FragmentMood f = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, image);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mImage = getArguments().getInt(DRAWABLE);
        }
        else{
            mImage = 0;
        }
        if(getArguments() !=null){
            mColor = getArguments().getInt(MY_COLOR_KEY);
        }
        else {
            mColor = Color.BLACK;
        }

        mPreferences = getActivity().getPreferences(MODE_PRIVATE);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mood, container, false);
        v.setBackgroundColor(ContextCompat.getColor(getContext(),mColor));
        ImageView smiley = v.findViewById(R.id.image);
        smiley.setImageDrawable(ContextCompat.getDrawable(getContext(),mImage));

        comment = v.findViewById(R.id.fragment_mood_img_comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                alert.setMessage("Commentaire");
                alert.setTitle("Moodtracker");

                alert.setView(edittext);

                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String userInput = edittext.getText().toString();
                        Toast.makeText(getContext(), userInput, Toast.LENGTH_SHORT).show();

                        Calendar cal = Calendar.getInstance();

                        int day = Calendar.getInstance().get(Calendar.DATE);
                        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                        int year =  Calendar.getInstance().get(Calendar.YEAR);

                        String date = day + "" + month + "" + year;
                        Log.e("tag",day+"");

                        mPreferences.edit().putString(PREF_KEY_COMMENT,userInput).apply();
                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.

                    }
                });

                alert.show();
            }
        });
        return v;
    }
}