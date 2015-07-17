package com.example.facebook_volley;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.facebook_volley.adapter.MessengerListAdapter;
import com.example.facebook_volley.data.FeedItem;
import com.example.facebook_volley.data.MessengerItem;
import com.example.facebook_volley.widget.Feedtinnhan_dialog;

import java.util.ArrayList;

/**
 * Created by Doan Vo Viet Hoai on 6/22/2015.
 */
public class MessengerActivity extends Fragment {
    private ListView listView;
    private ArrayList<MessengerItem> mlistItem = new ArrayList<>();
    int[] img ={R.drawable.icon_fb,R.drawable.icon_fb,R.drawable.icon_fb,};
    String[] name={"a","b","c"};
    String[] mess={"Alo","xin chao","Hi"};
    String[] time={"11h45","thu 7","thu 4"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.messenger_item_list,container,false);
        listView = (ListView)v.findViewById(R.id.list_messenger);
        for(int i=0;i<img.length;i++){
            MessengerItem item = new MessengerItem();
            item.setImg(img[i]);
            item.setName(name[i]);
            item.setMessage(mess[i]);
            item.setTime(time[i]);
            mlistItem.add(item);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(view.getContext(), Feedtinnhan_dialog.class);
                MessengerItem item = mlistItem.get(position);
                i.putExtra("Item",item);
                startActivity(i);
            }
        });

        MessengerListAdapter adapter = new MessengerListAdapter(getActivity(),mlistItem);
        listView.setAdapter(adapter);

        return v;
    }
}
