package com.example.care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Alarm_Page extends AppCompatActivity {
    private Spinner hourSpinner;
    private Spinner minuteSpinner;
    private Spinner APM;
    private Button button;
    private Button viewButton;
    private Button backButton;
    private Timer timer;
    private BufferedWriter writer;
    private FileWriter file;
    private EditText alarmInput;
    SimpleDateFormat sdf;

    private LinearLayout test;

    private TextToSpeech tts;
    float tts_pitch;
    float tts_speechrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_page);

        //语音设置
        tts_pitch = 1.0f;
        tts_speechrate = 1.0f;
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    Log.i("","create success");
                }
                else{
                    Toast.makeText(Alarm_Page.this, "Something went wrong with TexttoSpeech Service", Toast.LENGTH_SHORT).show();
                }/***********************need modification on activity name************************/
            }
        });

        timer = new Timer();

        SimpleDateFormat pattern = new SimpleDateFormat("dd/MM/yyyy");
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        test = findViewById(R.id.alarmLayout);


        alarmInput = findViewById(R.id.alarmInputText);
        hourSpinner = findViewById(R.id.spinner2);
        minuteSpinner = findViewById(R.id.spinner3);
        APM = findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> hourAdapter = ArrayAdapter.createFromResource(this, R.array.hour, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> minuteAdapter = ArrayAdapter.createFromResource(this, R.array.minute, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> apmAdapter = ArrayAdapter.createFromResource(this, R.array.APM, android.R.layout.simple_spinner_dropdown_item);
        hourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minuteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hourAdapter);
        minuteSpinner.setAdapter(minuteAdapter);
        APM.setAdapter(apmAdapter);
        button = findViewById(R.id.button);
        viewButton = findViewById(R.id.viewAlarmButton);
        backButton = findViewById(R.id.alarmToMain);
        button.setOnClickListener(view -> {
            String hours = hourSpinner.getSelectedItem().toString();
            String minutes = minuteSpinner.getSelectedItem().toString();
            String aorp = APM.getSelectedItem().toString();
            String say = hours + " " + " "+ minutes +" "+aorp;
            if(aorp.equals("PM")){
                int temp = Integer.parseInt(hours)+12;
                hours = String.valueOf(temp);
                if(hours.equals("24"))
                    hours="12";
            }
            else if(aorp.equals("AM")&&hours.equals("12")){
                hours="00";
            }
            Date date = new Date();
            String dateString = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss").format(date);
            dateString = dateString +" "+ hours +":"+minutes+":"+"00";
            Timestamp goodsC_date =Timestamp.valueOf(dateString);
            System.out.print(goodsC_date);
            try {
                date = sdf.parse(dateString);
                setSchedule(date, say);
//                Toast.makeText(getApplicationContext(), dateString, Toast.LENGTH_LONG).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String event = alarmInput.getText().toString();
            //显示添加的闹钟时间
            addView(say, event);
        });
        viewButton.setOnClickListener(view->{
            Intent intent = new Intent(this, View_Alarm_Page.class);
            startActivity(intent);
        });
        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });
    }

    /**
     * add schedule
     * @param time Date type of added alarm
     * @param say string of time for output, e.g. 10:40 am
     */
    protected void setSchedule(Date time, String say){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                exit=true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String output = "currently" + say + ", is time for "+alarmInput.getText().toString()+".";
                        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
                        fun_tts(output+" "+output+" "+output);
                    }
                });
            }
        };
        String s = sdf.format(time);
//        String date = sdf.format(new Date());
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        if(time.before(new Date()))
            return;
        timer.schedule(timerTask, time);
    }

    protected void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    /**
     * voice output
     * @param pretext string of voice output
     */
    void fun_tts(String pretext){
        tts.setLanguage(Locale.US);
        tts.setPitch(tts_pitch);
        tts.setSpeechRate(tts_speechrate);
        tts.speak(pretext,TextToSpeech.QUEUE_FLUSH,null);
    }

    /**
     * display added alarm view
     * @param time time in string format to be display
     */
    private void addView(String time, String event){
        final View alarmView = getLayoutInflater().inflate(R.layout.alarm_row, null, false);
        TextView tv = alarmView.findViewById(R.id.alarmTime);
        Button removeBtn = alarmView.findViewById(R.id.removeAlarm);
        tv.setText(time);
        TextView tv1 = alarmView.findViewById(R.id.alarmInput);
        tv1.setText(event);
        removeBtn.setOnClickListener(view->{
            test.removeView(alarmView);
        });
        test.addView(alarmView);
    }
}