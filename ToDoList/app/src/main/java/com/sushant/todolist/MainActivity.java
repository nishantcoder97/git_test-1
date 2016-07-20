package com.sushant.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.sushant.todolist.db.TaskTable;
import com.sushant.todolist.models.Task;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b= (Button) findViewById(R.id.bt_adder);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("123", "onClick: click");
                goToAdder();
            }
        });

        Button butDel= (Button) findViewById(R.id.del_but);
        butDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler.deleteExtra(MainActivity.this);
                refreshList();
            }
        });

    }

    void goToAdder(){
        Log.d("123", "goToAdder: ");
        Intent i=new Intent(this,InsertActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpandableListView listView=refreshList();

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.d("456", "onItemLongClick: ");
                DbHandler.setDone(i,MainActivity.this);
                refreshList();
                return false;
            }
        });
    }
    ExpandableListView refreshList(){
           final String[] projection = {TaskTable.Columns.ID,TaskTable.Columns.TITLE, TaskTable.Columns.DETAILS, TaskTable.Columns.DATE, TaskTable.Columns.DONE};

        ArrayList<TaskId> taskArrayList = DbHandler.ReadDB(projection, this);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.lv);
        listView.setAdapter(new MyAdapter(this, taskArrayList));
        return listView;
    }
}
