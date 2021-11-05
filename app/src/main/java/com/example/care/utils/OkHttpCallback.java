package com.example.care.utils;

import android.util.Log;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkHttpCallback implements Callback{
    private final String TAG = OkHttpCallback.class.getSimpleName();
    
    public String url;
    public String result;

    @Override
    public void onFailure(Call call, IOException e) {
        Log.d(TAG,"url"+url);
        Log.d(TAG,"request failed:"+e.toString());
        onFinish("failure", e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Log.d(TAG,"url"+url);
        result = response.body().string().toString();
        Log.d(TAG,"request success:"+result);
        onFinish("success",result);
    }

    public void onFinish(String status, String msg) {
        Log.d(TAG,"url:"+url+"status:"+status);
    }
}

