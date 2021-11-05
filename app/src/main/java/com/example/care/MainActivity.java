package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.EventListener;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button signup;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login.setOnClickListener(view -> {
            String user = username.getText().toString();
//            Toast.makeText(this,"user " + user, Toast.LENGTH_SHORT).show();
            String pwd = password.getText().toString();

            // 使用下面的code来测试后端和database
            OkHttpClient client = new OkHttpClient();
            FormBody.Builder formBuilder = new FormBody.Builder();
            formBuilder.add("username", user);
            formBuilder.add("password", pwd);
            // local connection please use 10.0.2.2
//            Request request = new Request.Builder().url("http://10.0.2.2:8000/login").post(formBuilder.build()).build();
            // cloud backend connect please use 18.216.178.211, I deployed the backend in AWS
            Request request = new Request.Builder().url("http://18.216.178.211/login").post(formBuilder.build()).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback()
            {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                    Toast.makeText(MainActivity.this,"Welcome back, " + user, Toast.LENGTH_SHORT).show();
                    String result = response.body().string();
                    Log.d("loginpage", result);

                    if (result.equals("successful")) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this,"Welcome back, " + user, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                    } else if (result.equals("username not exists")){
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this,"This username, " + user + ", not exists", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this,"Username or password not correct", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call call, IOException e)
                {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(MainActivity.this,"Request failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

//            本地不连接database时，使用下面的code来跳转登入
//            if (user.equals("admin") && pwd.equals("1234")) {
//                Toast.makeText(this,"Welcome back, " + user, Toast.LENGTH_SHORT).show();
//                Intent intent= new Intent(this, MainActivity2.class);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this,"Fail to login", Toast.LENGTH_LONG).show();
//            }

        });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(getContext(), Signup.class);
//                startActivity(intent);
//            }
//        });

        signup.setOnClickListener(view -> {
            Intent intent= new Intent(this, Signup.class);
            startActivity(intent);
        });
    }
}