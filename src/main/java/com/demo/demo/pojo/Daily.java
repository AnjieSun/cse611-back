package com.demo.demo.pojo;

import java.util.Date;

public class Daily {
    private Integer id;

    private Date date;

    private String heartrate;

    private String bodytem;

    private String bloodpre;

    private String bloodsug;

    private String fever;

    private String abnormal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate == null ? null : heartrate.trim();
    }

    public String getBodyrem() {
        return bodytem;
    }

    public void setBodytem(String bodytem) {
        this.bodytem = bodytem == null ? null : bodytem.trim();
    }

    public String getBloodpre() {
        return bloodpre;
    }

    public void setBloodpre(String bloodpre) {
        this.bloodpre = bloodpre == null ? null : bloodpre.trim();
    }

    public String getBloodsug() {
        return bloodsug;
    }

    public void setBloodsug(String bloodsug) {
        this.bloodsug = bloodsug == null ? null : bloodsug.trim();
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever == null ? null : fever.trim();
    }

    public String getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(String abnormal) {
        this.abnormal = abnormal == null ? null : abnormal.trim();
    }
}