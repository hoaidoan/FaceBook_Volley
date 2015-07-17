package com.example.facebook_volley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.facebook_volley.R;
import com.example.facebook_volley.data.MessengerItem;

import java.util.ArrayList;

/**
 * Created by Doan Vo Viet Hoai on 6/22/2015.
 */
public class MessengerListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MessengerItem> list = new ArrayList<>();

    public MessengerListAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        ImageView img_ava;
        TextView tv_name, tv_mess, tv_time;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.messenger_item,parent,false);
            holder = new ViewHolder();
            holder.img_ava =(ImageView)convertView.findViewById(R.id.img_avata);
            holder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            holder.tv_mess=(TextView)convertView.findViewById(R.id.tv_mess);
            holder.tv_time=(TextView)convertView.findViewById(R.id.tv_time);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.img_ava.setImageResource(list.get(position).getImg());
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_mess.setText(list.get(position).getMessage());
        holder.tv_time.setText(list.get(position).getTime());
        return convertView;
    }
}
