package com.demo.demo.common;

public enum ResponseCode {

    USERNAME_NOT_EMPTY(1,"username can not be null"),
    PASSWORD_NOT_EMPTY(2,"password can not be null"),
    USERNAME_NOT_EXIST(3,"username do not exist"),
    PASSWORD_ERROR(4,"password is error"),
    PARAMETER_NOT_EMPTY(5,"parameter can not be null"),
    AGE_NOT_EMPTY(6,"age can not be null"),
    DOC_EMAIL_NOT_EMPTY(7,"email of doctor can not be null"),
    E_MAIL_NOT_NULL(8,"E_mail can not be null"),
    GENDER_NOT_NULL(9,"gender can not be null"),
    PHONE_NOT_NULL(10,"PHONE can not be null"),
    USERNAME_ALREADY_EXIST(11,"username already exist"),
    INSERT_FAILED(12,"didn't insert successfully"),
    DELETE_FAILED(13,"didn't delete successfully"),
    UPDATE_FAILED(14,"didn't update successfully"),
    DATE_CAN_NULL(15,"date can't be null"),
    NOT_LOGIN(16,"didn't login"),
    EMAIL_ALREADY_EXIST(17,"e-mail already exist"),
    SEARCH_FAILED(18,"There is no schedule today"),
    WRONG_EMAIL(19,"This is not a validate email"),
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
