package com.demo.demo.pojo;

import java.util.Date;

public class Daily {
    private Integer id;

    private Date date;

    private String heartRate;

    private String bodyTemperature;

    private String bloodPressure;

    private String bloodSugar;

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

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate == null ? null : heartRate.trim();
    }

    public String getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(String bodyTemperature) {
        this.bodyTemperature = bodyTemperature == null ? null : bodyTemperature.trim();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar == null ? null : bloodSugar.trim();
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