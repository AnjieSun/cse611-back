package com.demo.demo.vo;

import java.util.Date;

public class ScheduleVO {
    private Integer id;

    private String date;

    private String todo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo == null ? null : todo.trim();
    }
}