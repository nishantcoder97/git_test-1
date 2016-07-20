package com.sushant.todolist;

import com.sushant.todolist.models.Task;

/**
 * Created by Sushant on 16-07-2016.
 */
public class TaskId {
    Task task;
    int id;

    public TaskId(Task task, int id) {
        this.task = task;
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public int getTaskId() {
        return id;
    }
}
