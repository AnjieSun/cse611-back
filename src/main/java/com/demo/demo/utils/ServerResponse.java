package com.demo.demo.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServerResponse<T> {

    private int status; // 0: success, 1:fail
    private T data;
    private String msg;

    private ServerResponse()
    {

    }

    private ServerResponse(int status)
    {
        this.status = status;
    }

    private ServerResponse(int status, T data)
    {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status,T data, String msg)
    {
        this.status = status;
        this.data =data;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess()
    {
        return this.status == 0;
    }

    public  static  ServerResponse createServerResponseBySuccess()
    {
        return new ServerResponse(0);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data)
    {
        return new ServerResponse(0,data);
    }

    public static <T> ServerResponse createServerResponseBySuccess(T data, String msg)
    {
        return new ServerResponse(0,data,msg);
    }

    public  static  ServerResponse createServerResponseByfail()
    {
        return new ServerResponse(1);
    }

    public static <T> ServerResponse createServerResponseByfail(T data)
    {
        return new ServerResponse(1,data);
    }

    public static <T> ServerResponse createServerResponseByfail(T data, String msg)
    {
        return new ServerResponse(1,data,msg);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
