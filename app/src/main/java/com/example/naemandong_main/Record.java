package com.example.naemandong_main;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Record extends AppCompatActivity {

    private final static String TAG = "Record";

    MediaRecorder mRecorder = new MediaRecorder();
    MediaPlayer mPlayer = new MediaPlayer();
    String mPath = null;
    private ImageButton record_preview, record_start, record_save;
    boolean isPlaying = false;
    boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        record_preview = (ImageButton) findViewById(R.id.record_preview);
        record_start = (ImageButton) findViewById(R.id.record_start);
        record_save = (ImageButton) findViewById(R.id.record_save);


        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.AAC_ADTS);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/rScene01.aac";
        Log.d(TAG, "file path is " + mPath);
        mRecorder.setOutputFile(mPath);

        record_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecorder = new MediaRecorder();

                try {
                    mRecorder.prepare();
                    mRecorder.start();
                    Toast.makeText(Record.this, "녹음시작", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "prepare() failed");
                }
                // recode.initAudioRecorder();
            }
        });

        record_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecorder != null) {
                    mRecorder.stop();
                    mRecorder.reset();
                    mRecorder.release();
                    mRecorder = null;
                    Toast.makeText(Record.this, "녹음 멈춤", Toast.LENGTH_SHORT).show();
                }
            }
        });

        record_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying == false) {
                    try {
                        mPlayer = new MediaPlayer();

                        mPlayer.setDataSource(mPath);
                        mPlayer.prepare();
                        mPlayer.start();

                        Toast.makeText(Record.this, "재생 시작", Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        Log.e(TAG, "prepare() failed");
                    }
                    isPlaying = true;
                }
                else {
                    mPlayer.stop();
                    isPlaying = false;
                }
            }
        });

    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            isPlaying = false;
        }
    });
    }

}
