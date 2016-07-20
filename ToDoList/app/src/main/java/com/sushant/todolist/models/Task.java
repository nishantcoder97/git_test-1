package com.sushant.todolist.models;

import java.util.Date;

/**
 * Created by Sushant on 15-07-2016.
 */
public class Task {
    String title;
    String details;
    int done;
    long date;

    public Task(String title, String details, int done,long date) {
        this.title = title;
        this.details = details;
        this.done = done;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public int isDone() {
        return done;
    }

    public long getDate() {
        return date;
    }
}
