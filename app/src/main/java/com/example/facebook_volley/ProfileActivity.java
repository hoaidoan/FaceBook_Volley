package com.example.facebook_volley;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.facebook_volley.ViewHolderForMultyType.ViewHolder;
import com.example.facebook_volley.adapter.FeedListAdapter;
import com.example.facebook_volley.adapter.MultyTypeAdapter;
import com.example.facebook_volley.data.ImageModel;
import com.example.facebook_volley.data.VideoModel;

import java.util.ArrayList;

/**
 * Created by Doan Vo Viet Hoai on 7/9/2015.
 */
public class ProfileActivity extends Activity {
    int image = R.drawable.bg_1;
    String path = "/sdcard/Download/video1.mp4";
    String path1 = "/sdcard/Download/video2.mp4";
    ListView listView;
    ImageButton imb_play;
    ArrayList mList = new ArrayList();
    VideoView vidView;

    @Override
    protected void onRestart() {
        listView.getSelectedItemPosition();
        listView.deferNotifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        listView = (ListView) findViewById(R.id.lvProfile);
        ImageModel a = new ImageModel();
        a.setImage(image);
        VideoModel b = new VideoModel();
        b.setVideoUrl(path);
        VideoModel c = new VideoModel();
        c.setVideoUrl(path1);
        mList.add(b);
        mList.add(c);
        mList.add(a);







//        Log.d("aa", "aaaaaaaaaaaaaaaaaaaaa" + ((VideoModel) mList.get(1)).getVideoUrl());
        MultyTypeAdapter adapter = new MultyTypeAdapter(mList, ProfileActivity.this);

//        View header=getLayoutInflater().inflate(R.layout.header_profile,null);
//        listView.addHeaderView(header);
        listView.setAdapter(adapter);

    }


}
