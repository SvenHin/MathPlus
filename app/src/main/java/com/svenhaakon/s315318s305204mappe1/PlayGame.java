package com.svenhaakon.s315318s305204mappe1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;

import static com.svenhaakon.s315318s305204mappe1.R.array.eq_array;

public class PlayGame extends Activity {

    Button answrBtn;
    String[] mTestArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playgame);

        mTestArray = getResources().getStringArray(R.array.eq_array);
        updateTextView();
        final Button button = findViewById(R.id.answrbtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateTextView();
            }
        });
    }




    private void updateTextView(){
        TextView textView = (TextView)findViewById(R.id.EqDisplayBox);
        Random random = new Random();

        int maxIndex = mTestArray.length;
        int generatedIndex = random.nextInt(maxIndex);

        textView.setText(mTestArray[generatedIndex]);
    }


}
