package com.example.facebook_volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 *
 */
public class VideoPlayActivity extends Activity {
    ImageButton imb_play;
    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;
    int current;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video_main);
        myVideoView = (VideoView) findViewById(R.id.myVideo1);
        Intent i = getIntent();
        String path = i.getBundleExtra("bundle").getString("key");

        mediaControls = new MediaController(VideoPlayActivity.this);
        mediaControls.setAnchorView(myVideoView);
        myVideoView.setMediaController(mediaControls);

//        Uri vidUri = Uri.parse(path);
//        myVideoView.setVideoURI(vidUri);
        myVideoView.setVideoPath(path);

//        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) myVideoView.getLayoutParams();
//        params.width = metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        myVideoView.setLayoutParams(params);
        myVideoView.start();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode ==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            current = myVideoView.getCurrentPosition();
            Log.d("aaaaaaaaaaaaaaaa", current + "");
            Bundle bundle = new Bundle();
            bundle.putInt("currentTime",current);
            Intent i = new Intent(VideoPlayActivity.this,ProfileActivity.class);
            i.putExtra("bundle",bundle);
            startActivity(i);
             return super.onKeyDown(keyCode, event);

        }
        else {
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
