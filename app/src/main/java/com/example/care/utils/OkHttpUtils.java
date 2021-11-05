package com.example.care.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {

    private static final OkHttpClient CLIENT =new OkHttpClient();

    public static void get(String url, OkHttpCallback callback){
        callback.url = url;
        Request request = new Request.Builder().url(url).build();
        CLIENT.newCall(request).enqueue(callback);
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset = utf-8");

    public static void post(String url, String json,OkHttpCallback callback){
        callback.url = url;
        RequestBody body = RequestBody.create(JSON,json);
        Request request = new Request.Builder().url(url).post(body).build();
        CLIENT.newCall(request).enqueue(callback);
    }


}
