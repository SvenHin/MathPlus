package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
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
import android.widget.Toast;

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

import static com.svenhaakon.s315318s305204mappe1.R.array.answr_array;
import static com.svenhaakon.s315318s305204mappe1.R.array.eq_array;

public class PlayGame extends Activity {

    List<String> questionArray;
    List<String> answerArray;
    int[] indexArray;
    public int points;
    public int arrayIterator;
    EditText inputText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        Toolbar settingsToolbar = (Toolbar)findViewById(R.id.game_toolbar);
        setActionBar(settingsToolbar);

        //mTestArray = getResources().getStringArray(R.array.eq_array);
        questionArray =  Arrays.asList(getResources().getStringArray(R.array.eq_array));
        answerArray =  Arrays.asList(getResources().getStringArray(R.array.answr_array));

        Resources r = getResources();
        indexArray = r.getIntArray(R.array.index_array);

        final Button button = findViewById(R.id.answrbtn);

        inputText = (EditText) findViewById(R.id.eqInputBox);
        initialize();







        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkAns();//sjekker og legger til poeng
                nextQuestion();

            }
        });
    }

    private void initialize(){

        points = 0;
        arrayIterator = 0;
        shuffleArray(indexArray);
        showQuestion();




    }

    private static void shuffleArray(int[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length -1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    private void showQuestion(){
        textView = (TextView)findViewById(R.id.EqDisplayBox);
        textView.setText(questionArray.get(indexArray[arrayIterator]));
    }

    private void checkAns(){
        if((inputText.getText().toString().equals(answerArray.get(indexArray[arrayIterator]).toString()))){
            points++;
            Log.d("Points", points +"/15");
        }

    }

    private void nextQuestion(){
        arrayIterator++;
        if (arrayIterator == questionArray.size()){textView.setText("Ferdig!");}
        showQuestion();
        inputText.setText("");
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
