package com.sushant.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sushant.todolist.db.TaskTable;
import com.sushant.todolist.models.Task;

import java.util.ArrayList;

/**
 * Created by Sushant on 16-07-2016.
 */
public class DbHandler {
    static SQLiteDatabase db;
    static void insertTaskIntoDB(Task reTask, Context context){

        db = ListDbOpener.openWritableDatabase(context);
        Task task=reTask;
        ContentValues cv=new ContentValues();
        cv.put(TaskTable.Columns.TITLE,task.getTitle());
        cv.put(TaskTable.Columns.DETAILS,task.getDetails());
        cv.put(TaskTable.Columns.DONE,task.isDone());
        db.insert(TaskTable.TABLE_NAME,null,cv);
    }
    static ArrayList<TaskId> ReadDB(String[] projection,Context context){
        db=ListDbOpener.openReadableDatabase(context);
        Cursor c=db.query(TaskTable.TABLE_NAME,projection,null,null,null,null,TaskTable.Columns.DATE);
        Log.d("123", "onCreate: "+c.getCount());

        ArrayList<TaskId> taskArrayList=new ArrayList<>();
        while (c.moveToNext()){
            Task t=new Task(c.getString(c.getColumnIndex(TaskTable.Columns.TITLE)),
                    c.getString(c.getColumnIndex(TaskTable.Columns.DETAILS)),
                    c.getInt(c.getColumnIndex(TaskTable.Columns.DONE)),
                    c.getLong(c.getColumnIndex(TaskTable.Columns.DATE)));
            taskArrayList.add(new TaskId(t,c.getInt(c.getColumnIndex(TaskTable.Columns.ID))));
            Log.d("123", "onCreate: isdone "+c.getInt(c.getColumnIndex(TaskTable.Columns.DONE))+" details "+c.getString(c.getColumnIndex(TaskTable.Columns.DETAILS)));
        }
        return taskArrayList;
    }
    static void setDone(int pos,Context context){
        final String[] projection = {TaskTable.Columns.ID,TaskTable.Columns.TITLE, TaskTable.Columns.DETAILS, TaskTable.Columns.DATE, TaskTable.Columns.DONE};

        ArrayList<TaskId> taskArrayList = DbHandler.ReadDB(projection, context);

        db=ListDbOpener.openWritableDatabase(context);
        ContentValues cv=new ContentValues();
        if(taskArrayList.get(pos).task.isDone()==0)
        cv.put(TaskTable.Columns.DONE,1);
        else if(taskArrayList.get(pos).task.isDone()==1)
            cv.put(TaskTable.Columns.DONE,0);
        db.update(TaskTable.TABLE_NAME,cv," "+TaskTable.Columns.ID+" = "+taskArrayList.get(pos).id+";",null);
    }

    static void deleteExtra(Context context){
        db=ListDbOpener.openWritableDatabase(context);
        db.delete(TaskTable.TABLE_NAME," "+TaskTable.Columns.DONE+" = 1",null);
    }
}
