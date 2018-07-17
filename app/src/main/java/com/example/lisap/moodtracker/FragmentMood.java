package com.example.lisap.moodtracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentMood extends Fragment {

    private static final String DRAWABLE = "image";
    private static final String MY_COLOR_KEY = "color";

    private int mImage;
    private int mColor;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mood, container, false);
        v.setBackgroundColor(ContextCompat.getColor(getContext(),mColor));
        ImageView smiley = v.findViewById(R.id.image);
        smiley.setImageDrawable(ContextCompat.getDrawable(getContext(),mImage));
        return v;
    }
}