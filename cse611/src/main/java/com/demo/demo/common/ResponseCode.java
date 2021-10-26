package com.demo.demo.common;

public enum ResponseCode {

    USERNAME_NOT_EMPTY(1,"username can not null"),
    PASSWORD_NOT_EMPTY(2,"password can not null"),
    USERNAME_NOT_EXIST(3,"username do not exist"),
    PASSWORD_ERROR(4,"password is error"),
    PARAMETER_NOT_EMPTY(5,"parameter can not null")
    ;

    private int code;
    private String msg;

    ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
