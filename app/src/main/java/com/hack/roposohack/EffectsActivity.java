package com.hack.roposohack;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.File;
import java.util.ArrayList;

public class EffectsActivity extends AppCompatActivity {
    private File audioFile;
    private File videoFile;

    private FloatingActionButton fab;
    private LinearLayout durationViewLayout;
    private TextView durationView;
    private TextView suggestView;
    private ProgressBar progressBar;

    private ArrayList selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Video Editor");

        audioFile = new File(getIntent().getStringExtra("audioFile"));
        videoFile = new File(getIntent().getStringExtra("videoFile"));

        durationView = (TextView) findViewById(R.id.durationView);
        suggestView = (TextView) findViewById(R.id.suggestView);
        suggestView.setVisibility(View.INVISIBLE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        durationViewLayout = (LinearLayout) findViewById(R.id.durationViewLayout);
        durationViewLayout.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_btn_add);
        fab.hide();


        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Do the operation to find duration here
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // set the duration here.
                        setDurationText("test");
                    }
                });
            }
        }).start();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            final CharSequence[] items = {" Easy "," Medium "," Hard "," Very Hard "};
            selectedItems = new ArrayList();

            AlertDialog dialog = new AlertDialog.Builder(EffectsActivity.this)
                .setTitle("Select Effects")
                .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                        if (isChecked) {
                            selectedItems.add(indexSelected);
                        } else if (selectedItems.contains(indexSelected)) {
                            selectedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        fab.setImageResource(R.drawable.ic_btn_check);
                        suggestView.setText("Click on the Click on the \"✔\" button to start conversion");
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            fab.hide();
                            suggestView.setVisibility(View.INVISIBLE);
                            progressBar.setVisibility(View.VISIBLE);
                            new Thread(new Runnable() {
                                public void run() {
                                    //Write code to do stuff here
                                    //videoFile and audioFile are global in this class
                                    try {
                                        Thread.sleep(3000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Update UI here.
                                            AlertDialog.Builder builder = new AlertDialog.Builder(EffectsActivity.this);
                                            builder.setMessage("Write your message here.");
                                            builder.setCancelable(true);

                                            builder.setPositiveButton(
                                                    "OK",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            //Stuff to do on "OK"
                                                            Intent goToPlayerActivity = new Intent(
                                                                    EffectsActivity.this, PlayerActivity.class);
                                                            goToPlayerActivity.putExtra("videoFile",
                                                                    "/sdcard/vid.mp4");
                                                            startActivity(goToPlayerActivity);
                                                            finish();
                                                            dialog.cancel();
                                                        }
                                                    });

                                            builder.setNegativeButton(
                                                    "Cancel",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int id) {
                                                            //Stuff to do on cancel
                                                            Intent goToStart = new Intent(EffectsActivity.this, StartActivity.class);
                                                            startActivity(goToStart);
                                                            finish();
                                                            dialog.cancel();
                                                        }
                                                    });
                                            AlertDialog alert11 = builder.create();
                                            alert11.show();
                                        }
                                    });
                                }
                            }).start();
                            }
                        });
                    }
                }).create();
            dialog.show();
            }
        });
    }

    //Send something like 1.30
    private void setDurationText(String duration){
        fab.show();
        suggestView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        durationViewLayout.setVisibility(View.VISIBLE);
        durationView.setText("Duration: " + duration);
    }
}
