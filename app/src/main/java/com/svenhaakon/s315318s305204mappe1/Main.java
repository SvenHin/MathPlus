package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import java.util.Locale;

public class Main extends Activity {

    Button playBtn;
    Button statBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mainToolbar = (Toolbar)findViewById(R.id.main_toolbar);
        setActionBar(mainToolbar);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        playBtn = (Button) findViewById(R.id.playBtn);
        statBtn = (Button) findViewById(R.id.statBtn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Main.this, PlayGame.class);
                Main.this.startActivity(i);
            }
        });

        statBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Statistics.class);
                Main.this.startActivity(i);
            }
        });

    }
    //TEST COMMIT


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.prefBtn:
                showPreferences();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public void showPreferences(){
        Intent intent = new Intent(Main.this, Settings.class);
        startActivity(intent);
    }
}
