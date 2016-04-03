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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class EffectsActivity extends AppCompatActivity {
    private File audioFile;
    private File videoFile;

    private FloatingActionButton fab;
    private Button addEffectsButton;

    private boolean effectsSelected;
    private ArrayList seletedItems;

    private TextView durationView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        audioFile = new File(getIntent().getStringExtra("audioFile"));
        videoFile = new File(getIntent().getStringExtra("videoFile"));

        durationView = (TextView) findViewById(R.id.durationView);
        durationView.setText("");

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();

        addEffectsButton = (Button) findViewById(R.id.addEffects);
        addEffectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {" Easy "," Medium "," Hard "," Very Hard "};
                seletedItems = new ArrayList();

                AlertDialog dialog = new AlertDialog.Builder(EffectsActivity.this)
                    .setTitle("Select Effects")
                    .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                            if (isChecked) {
                                seletedItems.add(indexSelected);
                            } else if (seletedItems.contains(indexSelected)) {
                                seletedItems.remove(Integer.valueOf(indexSelected));
                            }
                        }
                    }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            effectsSelected = true;
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
                    //Write code to do stuff here
                    //set duration string to durationView object
                    //videoFile and audioFile are global in this class
                }
            });
        }
    }
}
