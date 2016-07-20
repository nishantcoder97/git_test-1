package com.sushant.todolist;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.sushant.todolist.models.Task;

public class InsertActivity extends AppCompatActivity {
    EditText title;
    EditText details;

    Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        final DbHandler dbHandler=new DbHandler();
        final Context c=this;
        title= (EditText) findViewById(R.id.et_title);
        details=(EditText) findViewById(R.id.et_details);
        final CalendarView calendarView= (CalendarView) findViewById(R.id.calender);
        but= (Button) findViewById(R.id.but1);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                    if(calendarView.getDate()< SystemClock.uptimeMillis()){
                        Toast.makeText(InsertActivity.this, "Wrong Date", Toast.LENGTH_SHORT).show();
                        calendarView.setDate(SystemClock.uptimeMillis());
                    }
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleString= title.getText().toString();
                String detailsString=details.getText().toString();
                long date=calendarView.getDate();
                dbHandler.insertTaskIntoDB(new Task(titleString,detailsString,0,date),c);
                onBackPressed();

            }
        });
    }
}
