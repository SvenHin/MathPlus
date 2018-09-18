package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Statistics extends Activity {

    int lastRunPoints, lastRunWrongs, lastRunQuestions, totalPoints, totalWrongs, totalQuestions;
    TextView lastRunPointsView, lastRunWrongsView, lastRunQuestionsView, totalPointsView, totalWrongsView, totalQuestionsView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        lastRunPointsView = (TextView) findViewById(R.id.lastRunPoints);
        lastRunWrongsView = (TextView) findViewById(R.id.lastRunWrongs);
        lastRunQuestionsView = (TextView) findViewById(R.id.lastRunQuestions);

        totalPointsView = (TextView) findViewById(R.id.totalPoints);
        totalWrongsView = (TextView) findViewById(R.id.totalWrongs);
        totalQuestionsView = (TextView) findViewById(R.id.totalQuestions);


        lastRunPoints = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("points", 0);
        lastRunWrongs = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("wrongs", 0);
        lastRunQuestions = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("numQuestions", 0);

        totalPoints = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalPoints", 0);
        totalWrongs = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalWrongs", 0);
        totalQuestions = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getInt("totalQuestions", 0);

        lastRunPointsView.setText(String.valueOf(lastRunPoints));
        lastRunWrongsView.setText(String.valueOf(lastRunWrongs));
        lastRunQuestionsView.setText(String.valueOf(lastRunQuestions));

        totalPointsView.setText(String.valueOf(totalPoints));
        totalWrongsView.setText(String.valueOf(totalWrongs));
        totalQuestionsView.setText(String.valueOf(totalQuestions));
    }


}
