package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Setting extends AppCompatActivity {

    private TextView mDatePicker;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mDatePicker = findViewById(R.id.textView24);
        mDatePicker.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,
                    android.R.style.Theme_Holo_Light_DialogWhenLarge,
//                        android.R.style.Theme,
//                        android.R.style.Theme_Wallpaper,
                    (DatePickerDialog.OnDateSetListener) mDateSetListener,
                    year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });


        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.d("timePicker", "onDateSet: date: " + year + "/" + month + "/" + dayOfMonth);
                int rm = month + 1;
                mDatePicker.setText(year + "/" + rm + "/" + dayOfMonth);
            }
        };
    }
}