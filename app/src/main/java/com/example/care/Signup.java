package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Signup extends AppCompatActivity {

    Button joinnow;
    EditText username;
    EditText password;
    EditText conf_pass;
    EditText family_contact;
    EditText doctor_contact;
    private TextView mDatePicker;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        joinnow = findViewById(R.id.joinnow);

        username = findViewById(R.id.signup_username);
        password = findViewById(R.id.signup_password);
        conf_pass = findViewById(R.id.signup_confirm_pass);
        family_contact = findViewById(R.id.emergency);
        doctor_contact = findViewById(R.id.doctor_contact);

        joinnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String c_pass = conf_pass.getText().toString();
                String emergency = family_contact.getText().toString();
                String doctor_ct = doctor_contact.getText().toString();

                if (!pass.equals(c_pass)){
                    Toast.makeText(getApplicationContext(),"passwords no match", Toast.LENGTH_SHORT).show();
                }
                else{
                    OkHttpClient client = new OkHttpClient();
                    FormBody.Builder formBuilder = new FormBody.Builder();
                    formBuilder.add("username", user);
                    formBuilder.add("password", pass);
                    formBuilder.add("family_contact", emergency);
                    formBuilder.add("doctor_contact", doctor_ct);
                    // local backend connect please use 10.0.2.2
//                    Request request = new Request.Builder().url("http://10.0.2.2:8000/signup").post(formBuilder.build()).build();
                    // cloud backend connect please use 18.216.178.211, I deployed the backend in AWS
                    Request request = new Request.Builder().url("http://18.216.178.211/signup").post(formBuilder.build()).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback()
                    {
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String result = response.body().string();
                            if (result.equals("success")) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Signup.this, "Registration succeeds, please login", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Intent intent= new Intent(Signup.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Signup.this, "Registration fails", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException e)
                        {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(Signup.this, "Request fails", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }

            }
        });
//
//        joinnow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavController controller = Navigation.findNavController(view);
//                controller.navigate(R.id.action_login_page_to_signup_page);
//            }
//        });

        mDatePicker = findViewById(R.id.textView23);
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