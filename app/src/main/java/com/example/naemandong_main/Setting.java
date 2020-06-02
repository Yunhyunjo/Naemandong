package com.example.naemandong_main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

 public class Setting extends AppCompatActivity {

    private ImageButton exit, sound_on, sound_off, subtitle_on, subtitle_off, logout, nmd_exit;
    public boolean sound;
    public boolean subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //셋팅
        exit = (ImageButton)findViewById(R.id.exit);
        sound_on = (ImageButton)findViewById(R.id.sound_on);
        sound_off = (ImageButton)findViewById(R.id.sound_off);
        subtitle_on = (ImageButton)findViewById(R.id.subtitle_on);
        subtitle_off = (ImageButton)findViewById(R.id.subitle_off);
        logout = (ImageButton)findViewById(R.id.logout);
        nmd_exit = (ImageButton)findViewById(R.id.nmd_exit);

        sound = ((Setting_data)this.getApplication()).getSound();
        subtitle = ((Setting_data)this.getApplication()).getSubtitle();


        if (sound){
            sound_on.setSelected(true);
            sound_off.setSelected(false);
        }
        else {
            sound_on.setSelected(false);
            sound_off.setSelected(true);
        }
        if (subtitle){
            subtitle_on.setSelected(true);
            subtitle_off.setSelected(false);
        }
        else {
            subtitle_on.setSelected(false);
            subtitle_off.setSelected(true);
        }

        //클릭 리스너 셋팅 (클릭버튼이 동작하도록 만들어줌.)
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("sound", sound);
                intent.putExtra("subtitle", subtitle);
                setResult(0, intent);
                finish();
            }
        });
        sound_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound_on.setSelected(true);
                sound_off.setSelected(false);
                sound = true;
                setSound(sound);
                AudioManager manager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                manager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);
                manager.setStreamMute(AudioManager.STREAM_ALARM, false);
                manager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                manager.setStreamMute(AudioManager.STREAM_RING, false);
                manager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
            }
        });
        sound_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sound_on.setSelected(false);
                sound_off.setSelected(true);
                sound = false;
                setSound(sound);
                AudioManager manager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                manager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                manager.setStreamMute(AudioManager.STREAM_ALARM, true);
                manager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                manager.setStreamMute(AudioManager.STREAM_RING, true);
                manager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
            }
        });
        subtitle_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtitle_on.setSelected(true);
                subtitle_off.setSelected(false);
                subtitle = true;
                setSubtitle(subtitle);
            }
        });
        subtitle_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtitle_on.setSelected(false);
                subtitle_off.setSelected(true);
                subtitle = false;
                setSubtitle(subtitle);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nmd_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("sound", sound);
                intent.putExtra("subtitle", subtitle);
                intent.putExtra("exit", true);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    public void setSound(boolean sound){
        ((Setting_data)this.getApplication()).setSound(sound);
    }

     public void setSubtitle(boolean subtitle){
         ((Setting_data)this.getApplication()).setSubtitle(subtitle);
     }
}
