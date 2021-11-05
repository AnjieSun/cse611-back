package com.example.care;

import static java.lang.Thread.sleep;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity2 extends AppCompatActivity {
//    protected static final int RESULT_SPEECH = 1;

    private Button btnSpeak;
    private TextView txtText;
    private Thread speechThread;
    private Button btnAlarm;
    Intent intent;
    StringParser stringParser;
    Boolean exit;
    Timer timer;

    private TextToSpeech tts;
    float tts_pitch;
    float tts_speechrate;

    private ImageButton speechInput;
    private ImageButton speechInputNo;
    private ImageView imageview;
    private Button setting;
    private Button healthPage;
    private ConstraintLayout pageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyPermissions();
        setContentView(R.layout.activity_main2);

        txtText =  findViewById(R.id.speech_result);
        btnSpeak = findViewById(R.id.speech);
        btnAlarm = findViewById(R.id.buttonAlarm);

        speechInput = findViewById(R.id.speechInput);
        speechInputNo = findViewById(R.id.imageButton);
        imageview = findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.hello);

        setting = findViewById(R.id.button2);
        healthPage = findViewById(R.id.button3);

        pageLayout = findViewById(R.id.pageLayout);

        timer = new Timer();
        exit=false;
        stringParser = new StringParser();
        //开启语音识别功能
        intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //设置模式
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");

//        setSchedule();
        startSpeechThread();
        btnAlarm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                start_alarm_page();
            }
        });

        //语音设置
        tts_pitch = 1.0f;
        tts_speechrate = 1.0f;
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    Log.i("","create success");
                    tts_startup();
                }
                else{
                    Toast.makeText(MainActivity2.this, "Something went wrong with TexttoSpeech Service", Toast.LENGTH_SHORT).show();
                }/***********************need modification on activity name************************/
            }
        });

        //语音监听按键
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechInputNo.setVisibility(v.INVISIBLE);
                speechInput.setVisibility(v.VISIBLE);
                if(speechThread.isAlive()){
                    speechThread.interrupt();
                }
                startSpeechThread();
            }
        });

        //麦开关语音监听
        speechInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechInputNo.setVisibility(view.VISIBLE);
                speechInput.setVisibility(view.INVISIBLE);
                if(speechThread.isAlive()){
                    speechThread.interrupt();
                }
            }
        });
        speechInputNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechInputNo.setVisibility(view.INVISIBLE);
                speechInput.setVisibility(view.VISIBLE);
                if(speechThread.isAlive()){
                    speechThread.interrupt();
                }
                startSpeechThread();
            }
        });

        // 触屏激活语音监听
        pageLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        speechInputNo.setVisibility(view.INVISIBLE);
                        speechInput.setVisibility(view.VISIBLE);
                        if(speechThread.isAlive()){
                            speechThread.interrupt();
                        }
                        startSpeechThread();
                        break;
                }
                return false;
            }
        });

        //Setting 按钮
        setting.setOnClickListener(view -> {
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        });

        //健康输入按钮
        healthPage.setOnClickListener(view->{
            Intent intent = new Intent(this, InputHealth.class);
            startActivity(intent);
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        try{
                            ArrayList<String> data = result.getData().getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                        Log.d("cse611", "onActivityResult: ");
                            String command = data.get(0);
                            String value = stringParser.convert(data.get(0));
                            if(command.contains("how are you") || command.contains("How are you")){
                                imageview.setImageResource(R.drawable.ok);
                                fun_tts("Good, thank you for asking");
                            }
                            else if(command.contains("hello") || command.contains("Hello")){
                                imageview.setImageResource(R.drawable.hello);
                                fun_tts("hello");
                            }
                            else if(command.contains("good morning") || command.contains("Good morning")){
                                imageview.setImageResource(R.drawable.good);
                                fun_tts("good morning");
                            }
                            else if(command.contains("good night") || command.contains("Good night")){
                                imageview.setImageResource(R.drawable.kiss);
                                fun_tts("good night, have a sweet dream");
                            }
                            else if(command.contains("nice to meet you") || command.contains("Nice to meet you")){
                                imageview.setImageResource(R.drawable.hello);
                                fun_tts("nice to meet you too");
                            }
                            else if(command.contains("alarm page") || command.contains("Alarm page")){
                                start_alarm_page();
                            }
                            else {
                                imageview.setImageResource(R.drawable.sorry);
                                fun_tts("sorry, I don't understand");
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txtText.setText("You said: " + data.get(0) + value);
                                    speechInputNo.setVisibility(View.VISIBLE);
                                    speechInput.setVisibility(View.INVISIBLE);
                                }
                            });
                        }
                        catch(NullPointerException error){
                        }
                    }
                    else {
                        speechInputNo.setVisibility(View.VISIBLE);
                        speechInput.setVisibility(View.INVISIBLE);
                        imageview.setImageResource(R.drawable.sorry);
                        fun_tts("sorry, I don't understand");
                    }
                }
            });

    private static String[] PERMISSION_ALL = {Manifest.permission.RECORD_AUDIO, Manifest.permission.INTERNET};

    public void verifyPermissions() {
        boolean permission = (ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
                || (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED);
        if (permission) {
            ActivityCompat.requestPermissions(this, PERMISSION_ALL, 1);
        }
    }

    protected void setSchedule(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                exit=true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fun_tts("alarm is on");
                    }
                });
            }
        };
        timer.schedule(timerTask, 15000);
    }

    protected void startSpeechThread(){
        if(speechThread!=null&&speechThread.isAlive()) {
            return;
        }

        speechThread = new Thread(()->{
            try{
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Please start your voice");
                someActivityResultLauncher.launch(intent);
            }
            catch (Exception ex){
                throw ex;
            }
        });
        speechThread.start();
    }

    public void start_alarm_page(){
        Intent intent= new Intent(this, Alarm_Page.class);
        startActivity(intent);
    }

    protected void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    void fun_tts(String pretext){
        tts.setLanguage(Locale.US);
        tts.setPitch(tts_pitch);
        tts.setSpeechRate(tts_speechrate);
        tts.speak(pretext,TextToSpeech.QUEUE_FLUSH,null);
    }
    //call this at startup
    void tts_startup(){
        String tts_str = "Welcome";
        fun_tts(tts_str);
    }
    //call this every morning
    void tts_greet(){
        String tts_str = "Good morning";
        fun_tts(tts_str);
    }
}