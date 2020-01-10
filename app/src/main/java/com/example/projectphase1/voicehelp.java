package com.example.projectphase1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class voicehelp extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voicehelp);
    }
    public void gotoback(View view)
    {
        startActivity(new Intent(voicehelp.this,HomePageTabScreen.class));
    }
    public void playvoice(View view)
    {
        if(mediaPlayer==null)
        {
            mediaPlayer=MediaPlayer.create(this,R.raw.help);

        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopmedia();
            }
        });

    }
    public void stopvoice(View view)
    {
        stopmedia();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopmedia();
    }

    public void pausevoice(View view)
    {
        if(mediaPlayer!=null)
               mediaPlayer.pause();
    }
    private void stopmedia()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
