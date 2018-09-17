package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.svenhaakon.s315318s305204mappe1.R.array.eq_array;

public class PlayGame extends Activity {

    Button answrBtn;
    List<String> questionArray;
    List<String> answerArray;
    List<int[]> indexArray;

    public int points;
    public int arrayIterator = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        Toolbar settingsToolbar = (Toolbar)findViewById(R.id.game_toolbar);
        setActionBar(settingsToolbar);

        //mTestArray = getResources().getStringArray(R.array.eq_array);
        questionArray =  Arrays.asList(getResources().getStringArray(R.array.eq_array));
        answerArray =  Arrays.asList(getResources().getStringArray(R.array.answr_array));
        indexArray =   Arrays.asList(getResources().getIntArray(R.array.index_array));



        final Button button = findViewById(R.id.answrbtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTextView();

            }
        });
    }



    private void shuffleList(ArrayList<String> t){
        Random rnd = new Random();
        rnd.setSeed(123);
        Collections.shuffle(questionArray, rnd);


    }

    private void updateTextView(){

        TextView textView = (TextView)findViewById(R.id.EqDisplayBox);
        textView.setText(questionArray.get(arrayIterator));
        Log.d("qu", questionArray.get(arrayIterator));
        Log.d("ans", answerArray.get(arrayIterator));
        if (arrayIterator < questionArray.size() -1) arrayIterator ++;
        else textView.setText("Ferdig!");
    }

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
        Intent intent = new Intent(PlayGame.this, Settings.class);
        startActivity(intent);
    }
}
