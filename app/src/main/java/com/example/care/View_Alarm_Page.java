package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * WORK IN PROGRESS
 * DO NOT ACCESS THIS ACTIVITY
 * TEMPORARY TESTING PAGE
 */

public class View_Alarm_Page extends AppCompatActivity {
    private Button backButton;
    private LinearLayout test;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alarm_page);
        test = findViewById(R.id.layout);
        backButton = findViewById(R.id.testButton);
        backButton.setOnClickListener(view -> {
            addView();
        });
    }
    private void addView(){
        final View alarmView = getLayoutInflater().inflate(R.layout.alarm_row, null, false);
        TextView tv = alarmView.findViewById(R.id.alarmTime);
        Button removeBtn = alarmView.findViewById(R.id.removeAlarm);

        removeBtn.setOnClickListener(view->{
            test.removeView(alarmView);
        });
        test.addView(alarmView);
    }
}