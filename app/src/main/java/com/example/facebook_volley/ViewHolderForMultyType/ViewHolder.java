package com.example.facebook_volley.ViewHolderForMultyType;


import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * Created by Doan Vo Viet Hoai on 7/9/2015.
 */
public class ViewHolder {

    public class ImageViewHolder extends ViewHolder{
        public ImageView imageView;
    }

    public class VideoViewHolder extends ViewHolder{
        public VideoView videoView;
        public ImageButton imageButton;

    }

    public class SurfaceViewHolder extends ViewHolder{
        public FrameLayout frameLayout;
        public SurfaceView surfaceView;
    }

}
