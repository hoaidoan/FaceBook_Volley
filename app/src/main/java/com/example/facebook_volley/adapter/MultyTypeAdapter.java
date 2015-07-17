package com.example.facebook_volley.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.facebook_volley.R;
import com.example.facebook_volley.VideoPlayActivity;
import com.example.facebook_volley.ViewHolderForMultyType.ViewHolder;
import com.example.facebook_volley.controler.VideoPlayer;
import com.example.facebook_volley.data.ImageModel;
import com.example.facebook_volley.data.VideoModel;

import java.util.ArrayList;

/**
 * Created by Doan Vo Viet Hoai on 7/9/2015.
 */
public class MultyTypeAdapter extends BaseAdapter {

    public static final int VIEW_TYPE_VIDEO = 0;
    public static final int VIEW_TYPE_IMAGE = 1;
    private ArrayList<Object> mList = new ArrayList<Object>();
    private Activity context;


    public MultyTypeAdapter(ArrayList<Object> mList, Activity context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder.VideoViewHolder vidViewHolder = null;
        ViewHolder.ImageViewHolder imgViewHolder = null;
        ViewHolder.SurfaceViewHolder surViewHolder = null;
        int type = getItemViewType(position);
        if (type == VIEW_TYPE_IMAGE) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_type_image, parent, false);
                imgViewHolder = new ViewHolder().new ImageViewHolder();
                imgViewHolder.imageView = (ImageView) convertView.findViewById(R.id.img_type_img);
                convertView.setTag(imgViewHolder);
            } else
                imgViewHolder = (ViewHolder.ImageViewHolder) convertView.getTag();
            imgViewHolder.imageView.setImageResource(((ImageModel) mList.get(position)).getImage());
        } else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.layout_type_video, parent, false);
                vidViewHolder = new ViewHolder().new VideoViewHolder();
                vidViewHolder.videoView = (VideoView) convertView.findViewById(R.id.myVideo);
                vidViewHolder.imageButton = (ImageButton) convertView.findViewById(R.id.imb_play);
                convertView.setTag(vidViewHolder);
            } else
                vidViewHolder = (ViewHolder.VideoViewHolder) convertView.getTag();


            final String path = ((VideoModel) mList.get(position)).getVideoUrl();
            //String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
            Log.e("", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + path);
            final Uri vidUri = Uri.parse(path);
            vidViewHolder.videoView.setVideoURI(vidUri);
            final ViewHolder.VideoViewHolder finalVidViewHolder = vidViewHolder;
            vidViewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalVidViewHolder.imageButton.setVisibility(View.INVISIBLE);   /// FOR VIDEO VIEW
                    // finalVidViewHolder.videoView.start();
                    Bundle bundle = new Bundle();
//                bundle.putString("key", path);
                    bundle.putString("key", path);
                    Intent i = new Intent(context, VideoPlayActivity.class);
                    i.putExtra("bundle", bundle);
                    v.getContext().startActivity(i);
                }
            });

//
//            DisplayMetrics metrics = new DisplayMetrics();
//            context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
//            android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) vidViewHolder.videoView.getLayoutParams();
//            params.width = metrics.widthPixels;
//            params.height = metrics.heightPixels;
//            params.leftMargin = 0;
//            vidViewHolder.videoView.setLayoutParams(params);
            Intent intent = context.getIntent();
            if (intent.getBundleExtra("bundle") != null) {
                int current = intent.getBundleExtra("bundle").getInt("currentTime");
                Log.d("aaaaaaaaaaaaaaaa", current + "");

                final MediaController vidControl = new MediaController(context);
                vidControl.setAnchorView(vidViewHolder.videoView);
                ;
                vidViewHolder.videoView.setMediaController(vidControl);
                //Uri vidUri = Uri.parse(path);

                //myVideoView.setVideoURI(vidUri);
                vidViewHolder.videoView.setVideoPath(path);
                vidViewHolder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setVolume(0f, 0f);
                    }
                });
                vidViewHolder.imageButton.setVisibility(View.GONE);
                vidViewHolder.videoView.seekTo(current);
                vidViewHolder.videoView.start();


//            if (convertView == null) {
//                convertView = LayoutInflater.from(context).inflate(R.layout.layout_type_video_1, parent, false);
//                surViewHolder = new ViewHolder().new SurfaceViewHolder();
//                surViewHolder.surfaceView = (SurfaceView) convertView.findViewById(R.id.videoSurface);
//              //  vidViewHolder.imageButton = (ImageButton) convertView.findViewById(R.id.imb_play);
//                convertView.setTag(surViewHolder);
//            } else
//                surViewHolder = (ViewHolder.SurfaceViewHolder) convertView.getTag();
//            VideoPlayer videoPlayer = new VideoPlayer();
//            String path = ((VideoModel) mList.get(position)).getVideoUrl();
//            videoPlayer.VideoPlayerControler(context,path);
            }
        }

        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof ImageModel)
            return VIEW_TYPE_IMAGE;
        else return VIEW_TYPE_VIDEO;
    }
}


