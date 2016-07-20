package com.sushant.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sushant.todolist.models.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sushant on 16-07-2016.
 */
class MyAdapter extends BaseExpandableListAdapter {

    Context context;
    ArrayList<TaskId> taskArrayList;

    public MyAdapter(Context context, ArrayList<TaskId> taskArrayList) {
        this.context = context;
        this.taskArrayList = taskArrayList;
    }

    class Holder{
        TextView textView;
        TextView date;
    }
    @Override
    public int getGroupCount() {
        return taskArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public String getGroup(int i) {
        return taskArrayList.get(i).task.getTitle();
    }

    @Override
    public String getChild(int i, int i1) {
        return taskArrayList.get(i).task.getDetails();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        Holder holder=new Holder();
        if(convertView==null){
            LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=li.inflate(R.layout.group_layout,null);

            holder.textView= (TextView) convertView.findViewById(R.id.group_title);
            holder.date= (TextView) convertView.findViewById(R.id.group_date);
            convertView.setTag(holder);
        }
        else{
            holder= (Holder) convertView.getTag();
        }
        holder.textView.setText(getGroup(i));
        Date d=new Date(taskArrayList.get(i).getTask().getDate());
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy");
        String dateout=sdf.format(d);
        holder.date.setText(dateout);
        if(taskArrayList.get(i).task.isDone()==1){
            holder.textView.setTextColor(Color.RED);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPos, int childPos, boolean isExpanded, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        if(convertView==null){
            LayoutInflater li= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=li.inflate(R.layout.child_layout,null);

            holder.textView= (TextView) convertView.findViewById(R.id.child);
            convertView.setTag(holder);
        }
        else{
            holder= (Holder) convertView.getTag();
        }
        holder.textView.setText(getChild(groupPos,childPos));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
