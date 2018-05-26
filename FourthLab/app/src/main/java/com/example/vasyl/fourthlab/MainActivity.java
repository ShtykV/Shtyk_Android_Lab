package com.example.vasyl.fourthlab;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoPlayer;
    MediaPlayer mPlayer;

    Button startButton;
    Button pauseButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //video
        videoPlayer =  (VideoView)findViewById(R.id.videoPlayer);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video);
        videoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
        //

        //audio
        mPlayer=MediaPlayer.create(this, R.raw.song);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                StopPlay();
            }
        });
        startButton = (Button) findViewById(R.id.start);
        pauseButton = (Button) findViewById(R.id.pause);
        stopButton = (Button) findViewById(R.id.stop);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        //
    }

    public void StartPlay(View view)
    {
        mPlayer.start();
        startButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        videoPlayer.pause();
    }
    public void PausePlay(View view)
    {
        mPlayer.pause();
        startButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);
    }
    public void StopMedia(View view)
    {
        StopPlay();
    }
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()) {
            StopPlay();
        }
    }
    public void StopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            startButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
