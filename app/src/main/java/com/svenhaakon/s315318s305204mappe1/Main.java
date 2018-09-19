package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.app.DialogFragment;
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
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Main extends Activity implements ExitDialog.DialogClickListener{

    List<String> questionArray;
    List<String> answerArray;
    int points;
    int wrongs;
    int questionCounter;
    EditText inputText;
    TextView textView;
    int questions;
    TextView progressText;
    TextView correctText;
    TextView wrongText;
    ProgressBar progressBar;
    boolean inGame;
    ArrayList<Integer> indexList;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar settingsToolbar = (Toolbar)findViewById(R.id.game_toolbar);
        setActionBar(settingsToolbar);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        //XML arrays
        questionArray =  Arrays.asList(getResources().getStringArray(R.array.eq_array));
        answerArray =  Arrays.asList(getResources().getStringArray(R.array.answr_array));

        //The answer input field
        inputText = (EditText) findViewById(R.id.eqInputBox);

        //Keeps the keyboard hidden
        inputText.setShowSoftInputOnFocus(false);

        //TextViews
        progressText = (TextView) findViewById(R.id.progressionText);
        correctText = (TextView) findViewById(R.id.correctCounter);
        wrongText = (TextView) findViewById(R.id.wrongCounter);
        textView = (TextView)findViewById(R.id.EqDisplayBox);

        //ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //ArrayList of 25 indexes, when all indexes are gone user is asked to exit
        indexList = initList(25);

        //You will not be "in game" before you do an action
        inGame = false;

        //Only run initialize when not restoring from another instance
        if(savedInstanceState == null) initialize();
    }

    private void initialize(){
        points = 0;
        wrongs = 0;
        questionCounter = 0;
        Collections.shuffle(indexList);
        questions = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString("changeNumRoundsPref", ""));

        progressText.setText(getString(R.string.progression, questionCounter+1, questions));
        correctText.setText(getString(R.string.numPoints, points));
        wrongText.setText(getString(R.string.numWrongs, wrongs));

        progressBar.setProgress(0);
        progressBar.setMax(questions);

        showQuestion();
    }

    public ArrayList initList(int maxQuestions){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < maxQuestions; i++){
            list.add(i);
        }
        return list;
    }

    private void showQuestion(){
        currentIndex = indexList.remove(0);
        textView.setText(questionArray.get(currentIndex));
    }

    private void checkAns(){
        if((inputText.getText().toString().equals(answerArray.get(currentIndex)))){
            points++;
        }
        else wrongs++;
    }

    public void nextQuestion(View v){
        //inGame = true;
        checkAns();
        correctText.setText(getString(R.string.numPoints, points));
        wrongText.setText(getString(R.string.numWrongs, wrongs));
        inputText.setText("");
        progressBar.incrementProgressBy(1);
        questionCounter++;
        if (questionCounter == questions){
            textView.setText("Ferdig!");
            saveToSharedPreferences();
            return;
        }
        progressText.setText(getString(R.string.progression, questionCounter+1, questions));
        showQuestion();
    }

    public void saveToSharedPreferences(){
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("points", points).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("wrongs", wrongs).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("numQuestions", questions).apply();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalPoints", (points + getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalPoints", 0))).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalWrongs", (wrongs + getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalWrongs", 0))).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalQuestions", (questions + getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalQuestions", 0))).apply();

    }

    /////////////////////////////////// Toolbar Methods ///////////////////////////////////

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.playBtn:
                initialize();
                break;
            case R.id.statBtn:
                Intent i = new Intent(Main.this, Statistics.class);
                Main.this.startActivity(i);
                break;
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

    ///////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////// Custom keyboard methods /////////////////////////////

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

    public void backspace(View v){
        String s = inputText.getText().toString();
        if(s.length() > 0) inputText.setText(s.substring(0, s.length()-1));
    }

    ///////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// Dialog Methods ///////////////////////////////////
    @Override
    public void onYesClick() {
        finish();
    }

    @Override
    public void onNoClick() {
        return;
    }

    @Override
    public void onBackPressed() {
        if(inGame){
            visDialog();
        }
        else super.onBackPressed();
    }

    public void visDialog(){
        DialogFragment dialog = new ExitDialog();
        dialog.show(getFragmentManager(),"Avslutt");
    }

    //////////////////////////////////////////////////////////////////////////////////////

    ////////////////////// Saving current instance and restoring //////////////////////

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
        outState.putInt("questionCounter", questionCounter);
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
        questionCounter = savedInstanceState.getInt("questionCounter");
    }

    //////////////////////////////////////////////////////////////////////////////////
}
