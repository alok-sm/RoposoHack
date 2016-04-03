package com.hack.roposohack;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
    private Button addEffectsButton;
    private LinearLayout addEffectsLayout;
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

        addEffectsLayout = (LinearLayout) findViewById(R.id.addEffectsLayout);
        durationViewLayout = (LinearLayout) findViewById(R.id.durationViewLayout);
        addEffectsLayout.setVisibility(View.INVISIBLE);
        durationViewLayout.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        setDurationText("test");

        addEffectsButton = (Button) findViewById(R.id.addEffects);
        addEffectsButton.setOnClickListener(new View.OnClickListener() {
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
                        fab.show();
                    }
                }).create();
            dialog.show();
            }
        });

        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                fab.hide();
                progressBar.setVisibility(View.VISIBLE);
                addEffectsLayout.setVisibility(View.INVISIBLE);
                //Write code to do stuff here
                //videoFile and audioFile are global in this class
                }
            });
        }
    }

    //Send something like 1.30
    private void setDurationText(String duration){
        progressBar.setVisibility(View.INVISIBLE);
        addEffectsLayout.setVisibility(View.VISIBLE);
        durationViewLayout.setVisibility(View.VISIBLE);
        durationView.setText("Duration:" + duration);
    }
}
