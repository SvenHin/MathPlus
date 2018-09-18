package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import org.w3c.dom.Text;

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

import static android.media.MediaCodec.MetricsConstants.MODE;
import static com.svenhaakon.s315318s305204mappe1.R.array.answr_array;
import static com.svenhaakon.s315318s305204mappe1.R.array.eq_array;

public class PlayGame extends Activity {

    List<String> questionArray;
    List<String> answerArray;
    int[] indexArray;
    int points;
    int wrongs;
    int arrayIterator;
    EditText inputText;
    TextView textView;
    int questions;
    TextView progressText;
    TextView correctText;
    TextView wrongText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);
        Toolbar settingsToolbar = (Toolbar)findViewById(R.id.game_toolbar);
        setActionBar(settingsToolbar);

        questionArray =  Arrays.asList(getResources().getStringArray(R.array.eq_array));
        answerArray =  Arrays.asList(getResources().getStringArray(R.array.answr_array));
        indexArray = getResources().getIntArray(R.array.index_array);
        inputText = (EditText) findViewById(R.id.eqInputBox);
        progressText = (TextView) findViewById(R.id.progressionText);
        correctText = (TextView) findViewById(R.id.correctCounter);
        wrongText = (TextView) findViewById(R.id.wrongCounter);
        textView = (TextView)findViewById(R.id.EqDisplayBox);

        if(savedInstanceState == null){
            initialize();
        }
    }

    private void initialize(){
        points = 0;
        wrongs = 0;
        arrayIterator = 0;
        shuffleArray(indexArray);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(PlayGame.this);
        questions = Integer.parseInt(sharedPreferences.getString("changeNumRoundsPref", ""));
        progressText.setText(arrayIterator+1 + "/" + String.valueOf(questions));
        correctText.setText(String.valueOf(points));
        wrongText.setText(String.valueOf(wrongs));
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
        textView.setText(questionArray.get(indexArray[arrayIterator]));
    }

    private void checkAns(){
        if((inputText.getText().toString().equals(answerArray.get(indexArray[arrayIterator]).toString()))){
            points++;
        }
        else wrongs++;
    }

    public void nextQuestion(View v){
        checkAns();
        arrayIterator++;
        correctText.setText(String.valueOf(points));
        wrongText.setText(String.valueOf(wrongs));
        inputText.setText("");
        if (arrayIterator >= questions){
            textView.setText("Ferdig!");
            return;
        }
        progressText.setText(arrayIterator+1 + "/" + String.valueOf(questions));
        showQuestion();
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

    public void number1(View v){
        inputText.append("1");
    }

    public void number2(View v){
        inputText.append("2");
    }

    public void number3(View v){
        inputText.append("3");
    }

    public void number4(View v){
        inputText.append("4");
    }

    public void number5(View v){
        inputText.append("5");
    }

    public void number6(View v){
        inputText.append("6");
    }

    public void number7(View v){
        inputText.append("7");
    }

    public void number8(View v){
        inputText.append("8");
    }

    public void number9(View v){
        inputText.append("9");
    }

    public void number0(View v){
        inputText.append("0");
    }

    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("inputText", inputText.getText().toString());
        outState.putString("cCounter", correctText.getText().toString());
        outState.putString("wCounter", wrongText.getText().toString());
        outState.putString("pText", progressText.getText().toString());
        outState.putString("questionText", textView.getText().toString());
        outState.putInt("points", points);
        outState.putInt("wrongs", wrongs);
        outState.putInt("questionNum", questions);
        outState.putInt("arrayCounter", arrayIterator);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        inputText.setText(savedInstanceState.getString("inputText"));
        correctText.setText(savedInstanceState.getString("cCounter"));
        wrongText.setText(savedInstanceState.getString("wCounter"));
        progressText.setText(savedInstanceState.getString("pText"));
        textView.setText(savedInstanceState.getString("questionText"));
        points = savedInstanceState.getInt("points");
        wrongs = savedInstanceState.getInt("wrongs");
        questions = savedInstanceState.getInt("questionNum");
        arrayIterator = savedInstanceState.getInt("arrayCounter");
    }
}
