package com.example.lisap.moodtracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    private ImageView share;
    private ImageView historic;
    private SharedPreferences mPreferences;

    // PASS DATA TO CONSTRUCTOR //
    public static FragmentMood newInstance(int image, int color) {
        FragmentMood f = new FragmentMood();
        Bundle args = new Bundle();
        args.putInt(DRAWABLE, image);
        args.putInt(MY_COLOR_KEY, color);
        f.setArguments(args);
        return f;
    }

    // SELECT MOOD //
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImage = getArguments().getInt(DRAWABLE);
            mColor = getArguments().getInt(MY_COLOR_KEY);
        } else {
            mImage = 0;
            mColor = Color.BLACK;
        }
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mood, container, false);
        v.setBackgroundColor(ContextCompat.getColor(getContext(), mColor));
        ImageView smiley = v.findViewById(R.id.image);
        smiley.setImageDrawable(ContextCompat.getDrawable(getContext(), mImage));

        // TO ADD COMMENT //
        comment = v.findViewById(R.id.fragment_mood_img_comment);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                alert.setMessage("Commentaire");
                alert.setTitle("Moodtracker");
                alert.setView(edittext);

                alert.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String userInput = edittext.getText().toString();
                        Toast.makeText(getContext(), userInput, Toast.LENGTH_SHORT).show();

                        int day = Calendar.getInstance().get(Calendar.DATE);
                        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                        int year = Calendar.getInstance().get(Calendar.YEAR);

                        String date = day + "" + month + "" + year;

                        mPreferences.edit().putString(date + PREF_KEY_COMMENT, userInput).apply();
                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {}
                });

                alert.show();
            }
        });

        //TO GO TO HISTORIC PAGE //
        historic = v.findViewById(R.id.fragment_mood_img_historic);
        historic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), HistoricActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        // TO SHARE MOOD IN EMAIL //
        share = v.findViewById(R.id.fragment_mood_img_sharemood);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                final EditText edittext = new EditText(getContext());
                alert.setMessage("Partager à un ami");
                alert.setTitle("Moodtracker");
                alert.setView(edittext);

                alert.setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String userInput = edittext.getText().toString();

                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto",userInput, null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Humeur du jour by Moodtracker");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, userInput);
                        if(mColor== R.color.faded_red){
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"Je suis de très mauvaise humeur aujourd'hui");
                        }
                        else if(mColor== R.color.warm_grey){
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"Je ne suis pas de bonne humeur aujourd'hui ");
                        }
                        else if(mColor== R.color.cornflower_blue_65){
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"Je suis d'humeur moyenne aujourd'hui");
                        }
                        else if(mColor== R.color.light_sage){
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"Je suis de bonne humeur aujourd'hui");
                        }
                        else if(mColor== R.color.banana_yellow){
                            emailIntent.putExtra(Intent.EXTRA_TEXT,"Je suis de très bonne humeur aujourd'hui");
                        }

                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {}

                });
                alert.show();
            }
        });

        return v;
    }
}
