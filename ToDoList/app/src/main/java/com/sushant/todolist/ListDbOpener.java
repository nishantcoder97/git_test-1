package com.sushant.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sushant.todolist.db.TaskTable;
import com.sushant.todolist.models.Task;

/**
 * Created by Sushant on 15-07-2016.
 */
public class ListDbOpener extends SQLiteOpenHelper {

    public static final String DB_NAME="ListData";
    public static final int DB_VER=1;
    public ListDbOpener(Context context) {
        super(context,DB_NAME, null, DB_VER);
    }
    private static ListDbOpener myOpener=null;

    public static SQLiteDatabase openReadableDatabase(Context c){
        if(myOpener==null){
            myOpener=new ListDbOpener(c);
        }
        return myOpener.getReadableDatabase();
    }

    public static SQLiteDatabase openWritableDatabase(Context c){
        if(myOpener==null){
            myOpener=new ListDbOpener(c);
        }
        return myOpener.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TaskTable.TABLE_CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
