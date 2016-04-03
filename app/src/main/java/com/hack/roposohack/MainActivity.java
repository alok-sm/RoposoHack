package com.hack.roposohack;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int RESULT_SUCCESS_AUDIO = 1;
    private int RESULT_SUCCESS_VIDEO = 2;

    private Button addAudioButton;
    private Button addVideoButton;

    private boolean audioAdded = false;
    private boolean videoAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addAudioButton = (Button) findViewById(R.id.addAudio);
        addVideoButton = (Button) findViewById(R.id.addVideo);

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
            Uri uri = data.getData();
            if(requestCode == RESULT_SUCCESS_AUDIO){
                addAudioButton.setText("\u2713");
                audioAdded = true;
                //Add stuff to do with Audio below
            }else if(requestCode == RESULT_SUCCESS_VIDEO){
                addVideoButton.setText("\u2713");
                videoAdded = true;
                //Add stuff to do with Audio below
            }

            if(audioAdded && videoAdded){
                Intent goToEffectsActivity = new Intent(this, EffectsActivity.class);
                startActivity(goToEffectsActivity);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
