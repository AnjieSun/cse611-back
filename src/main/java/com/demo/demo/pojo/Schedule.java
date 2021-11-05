package com.demo.demo.pojo;

import java.util.Date;

public class Schedule {
    private Integer id;

    private Date date;

    private String todo;

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

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo == null ? null : todo.trim();
    }
}