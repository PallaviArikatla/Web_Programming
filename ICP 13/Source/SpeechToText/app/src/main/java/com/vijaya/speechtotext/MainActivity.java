package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //---//
    private TextToSpeech textToSpeach;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static final String NAME = "name";
    //---//
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFS,0);
        editor = preferences.edit();

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        textToSpeach = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int text = textToSpeach.setLanguage(Locale.UK);
                    speak("Hello, what is your name?");
                } }});
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    ArrayList<String> res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String inSpeech = res.get(0);
                    recognition(inSpeech);
                }
                break;
            }
        }
    }

    private void speak(String text){
        textToSpeach.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    private void recognition(String text){
        Log.e("Speech","" + text);
        String[] speech = text.split(" ");
        if(text.contains("hi my name is ")){
            String name = speech[speech.length-1];
            editor.putString(NAME,name).apply();
            speak("How are you today?" + preferences.getString(NAME, null));
        }
        else if(text.contains("what medicine should I take")){
            speak(preferences.getString(NAME, null) + ", I think you have Fever. Please take this medicine.");
        }
        else if(text.contains("I'm not feeling good what should I do")){
            speak("I can understand. Please tell your symptoms in short.");
        }

        else if(text.contains("thank you")){
            speak("Thank you too Take care, bye " + preferences.getString(NAME, null) );
        }
        else if(text.contains("What time is it")) {
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");
            Date now = new Date();
            String[] strDate = sdfDate.format(now).split(":");
            if (strDate[1].contains("00"))
                strDate[1] = "o'clock";
            speak("The time is " + sdfDate.format(now));
        }
    }
}