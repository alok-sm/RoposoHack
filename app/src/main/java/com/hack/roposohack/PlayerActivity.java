package com.hack.roposohack;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

public class PlayerActivity extends AppCompatActivity {

    private File videoFile;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        videoFile = new File(getIntent().getStringExtra("videoFile"));
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.fromFile(videoFile));
        videoView.requestFocus();
        videoView.start();
    }
}
