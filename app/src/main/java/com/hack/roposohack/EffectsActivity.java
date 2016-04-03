package com.hack.roposohack;

import android.content.DialogInterface;
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
    private ProgressBar progressBar;

    private ArrayList selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        audioFile = new File(getIntent().getStringExtra("audioFile"));
        videoFile = new File(getIntent().getStringExtra("videoFile"));

        durationView = (TextView) findViewById(R.id.durationView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        durationViewLayout = (LinearLayout) findViewById(R.id.durationViewLayout);
        durationViewLayout.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_btn_add);


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
                        fab.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            fab.hide();
                            progressBar.setVisibility(View.VISIBLE);
                            new Thread(new Runnable() {
                                public void run() {
                                    //Write code to do stuff here
                                    //videoFile and audioFile are global in this class
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //Update UI here.
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
        progressBar.setVisibility(View.INVISIBLE);
        durationViewLayout.setVisibility(View.VISIBLE);
        durationView.setText("Duration: " + duration);
    }
}
