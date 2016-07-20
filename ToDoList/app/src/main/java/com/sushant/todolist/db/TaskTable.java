package com.sushant.todolist.db;

/**
 * Created by Sushant on 15-07-2016.
 */
public class TaskTable {
    public static final String TABLE_NAME = "TASK_TABLE";

    public interface Columns {
        String ID = "taskId";
        String TITLE = "taskTitle";
        String DETAILS = "taskDetails";
        String DONE = "done";
        String DATE="date";
    }

    public static final String TABLE_CREATE_CMD =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                    + " ( "
                    + Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                    + Columns.TITLE + " STRING , "
                    + Columns.DONE + " INTEGER ,"
                    + Columns.DETAILS + " STRING , "
                    +Columns.DATE+" REAL "
                    + ");";

}
