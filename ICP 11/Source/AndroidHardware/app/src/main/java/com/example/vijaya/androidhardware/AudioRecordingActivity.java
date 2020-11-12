package com.example.vijaya.androidhardware;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioRecordingActivity extends AppCompatActivity {
    Button btnStartRecord, btnStopRecord, btnStartPlay, btnStopPlay;
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    String pathSave = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiorecording);
        btnStartRecord = findViewById(R.id.btnStartRecord);
        btnStopRecord = findViewById(R.id.btnStopRecord);
        btnStartPlay = findViewById(R.id.btnStartPlay);
        btnStopPlay = findViewById(R.id.btnStopPlay);
        if (!checkPermission()) {
            requestPermission();
        }
    }

    public void startrecord(View view) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename;
        filename = "Recording" + timeStamp + ".3gp";
        pathSave = getFilesDir() + filename;
        Toast.makeText(getApplicationContext(), "Recording - Start Speaking..", Toast.LENGTH_SHORT).show();

        setupMediaRecord();
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECORD_AUDIO
        }, 1000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000:
                break;
        }
    }

    private boolean checkPermission() {
        int recordPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return recordPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void setupMediaRecord() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    public void stopRecord(View view) {
        Toast.makeText(getApplicationContext(), "Stop Recording...!!", Toast.LENGTH_SHORT).show();
        mediaRecorder.stop();
        mediaRecorder.reset();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    public void playRecord(View view) {
        mediaPlayer = new MediaPlayer();
        try {
            Toast.makeText(getApplicationContext(), "Play Recording..!!", Toast.LENGTH_SHORT).show();
            mediaPlayer.setDataSource(pathSave);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPlay(View view) {
        Toast.makeText(getApplicationContext(), "Stop playing Recording..!!", Toast.LENGTH_SHORT).show();
        mediaPlayer.stop();
        mediaPlayer.release();
        setupMediaRecord();
    }
}