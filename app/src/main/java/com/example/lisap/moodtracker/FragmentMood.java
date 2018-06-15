package com.example.lisap.moodtracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentMood extends Fragment {

    private static final String MY_NUM_KEY = "num";
    private static final String MY_COLOR_KEY = "color";

    private int mNum;
    private int mColor;

    // You can modify the parameters to pass in whatever you want
    public static FragmentMood newInstance(int num, int color) {
        FragmentMood f = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt(MY_NUM_KEY, num);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments() != null ? getArguments().getInt(MY_NUM_KEY) : 0;
        mColor = getArguments() != null ? getArguments().getInt(MY_COLOR_KEY) : Color.BLACK;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mood, container, false);
        v.setBackgroundColor(mColor);
        TextView textView = v.findViewById(R.id.textview);
        textView.setText("Page " + mNum);
        return v;
    }
}