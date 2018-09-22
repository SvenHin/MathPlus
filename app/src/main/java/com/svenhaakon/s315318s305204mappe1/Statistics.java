package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Statistics extends Activity {

    private int lastRunPoints, lastRunWrongs, lastRunQuestions, totalPoints, totalWrongs, totalQuestions;
    private TextView lastRunPointsView, lastRunWrongsView, lastRunQuestionsView, lastRunPercentView, totalPointsView, totalWrongsView, totalQuestionsView, totalPercentView;
    private double lastRunPercent, totalPercent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        lastRunPointsView = (TextView) findViewById(R.id.lastRunPoints);
        lastRunWrongsView = (TextView) findViewById(R.id.lastRunWrongs);
        lastRunQuestionsView = (TextView) findViewById(R.id.lastRunQuestions);
        lastRunPercentView = (TextView) findViewById(R.id.lastRunPercent);

        totalPointsView = (TextView) findViewById(R.id.totalPoints);
        totalWrongsView = (TextView) findViewById(R.id.totalWrongs);
        totalQuestionsView = (TextView) findViewById(R.id.totalQuestions);
        totalPercentView = (TextView) findViewById(R.id.totalPercent);

        getSharedPreferences();

        lastRunPercent = calcPercent(lastRunPoints, lastRunQuestions);
        totalPercent = calcPercent(totalPoints, totalQuestions);

        printValues();
    }

    private double calcPercent(double a, double b){
        return ((a/b)*100);
    }

    private void setPercentBackground(TextView v, double percent){
        if (percent > 59){
            v.setBackgroundColor(getColor(R.color.Color7));
        }
        else if (percent < 60 && percent > 19){
            v.setBackgroundColor(getColor(R.color.Color5));
        }

        else v.setBackgroundColor(getColor(R.color.Color3));
    }

    private void getSharedPreferences(){
        lastRunPoints = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("points", 0);
        lastRunWrongs = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("wrongs", 0);
        lastRunQuestions = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("numQuestions", 0);


        totalPoints = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalPoints", 0);
        totalWrongs = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalWrongs", 0);
        totalQuestions = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalQuestions", 0);
    }

    private void printValues(){
        lastRunPointsView.setText(String.valueOf(lastRunPoints));
        lastRunWrongsView.setText(String.valueOf(lastRunWrongs));
        lastRunQuestionsView.setText(String.valueOf(lastRunQuestions));
        lastRunPercentView.setText(String.valueOf((int)lastRunPercent) + "%");

        totalPointsView.setText(String.valueOf(totalPoints));
        totalWrongsView.setText(String.valueOf(totalWrongs));
        totalQuestionsView.setText(String.valueOf(totalQuestions));
        totalPercentView.setText(String.valueOf((int)totalPercent) + "%");

        setPercentBackground(lastRunPercentView, lastRunPercent);
        setPercentBackground(totalPercentView, totalPercent);
    }

    public void wipeStats(View v){
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("points", 0).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("wrongs", 0).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("numQuestions", 0).apply();

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalPoints", 0).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalWrongs", 0).apply();
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putInt("totalQuestions", 0).apply();

        lastRunPercent = 0;
        totalPercent = 0;

        getSharedPreferences();
        printValues();
    }


}
