package com.hack.roposohack;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;

public class StartActivity extends AppCompatActivity {

    private int RESULT_SUCCESS_AUDIO = 1;
    private int RESULT_SUCCESS_VIDEO = 2;

    private Button addAudioButton;
    private Button addVideoButton;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    private boolean audioAdded = false;
    private boolean videoAdded = false;

    private File audioFile;
    private File videoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        addAudioButton = (Button) findViewById(R.id.addAudio);
        addVideoButton = (Button) findViewById(R.id.addVideo);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Video Editor");

        if (addAudioButton != null) {
            addAudioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("audio/*");
                    startActivityForResult(intent, RESULT_SUCCESS_AUDIO);
                }
            });
        }

        if (addVideoButton != null) {
            addVideoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("video/*");
                    startActivityForResult(intent, RESULT_SUCCESS_VIDEO);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){

        if(resultCode == RESULT_OK){
            Uri selectedUri = data.getData();
            File selectedFile = new File(selectedUri.getPath());
            if(requestCode == RESULT_SUCCESS_AUDIO){
                addAudioButton.setText("\u2713");
                audioAdded = true;
                audioFile = selectedFile;
            }else if(requestCode == RESULT_SUCCESS_VIDEO){
                addVideoButton.setText("\u2713");
                videoAdded = true;
                videoFile = selectedFile;
            }

            if(audioAdded && videoAdded){
                fab.show();
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent goToEffectsActivity = new Intent(StartActivity.this, EffectsActivity.class);
                        goToEffectsActivity.putExtra("audioFile", audioFile.getAbsolutePath());
                        goToEffectsActivity.putExtra("videoFile", videoFile.getAbsolutePath());
                        startActivity(goToEffectsActivity);
                        finish();
                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
