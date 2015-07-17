package com.example.facebook_volley;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.facebook_volley.app.AppController;
import com.example.facebook_volley.customerviews.TouchImageView;
import com.example.facebook_volley.data.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * Created by Doan Vo Viet Hoai on 7/2/2015.
 */
public class ViewImage extends Activity {
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    FeedImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.tam_thoi);
        imageView1=new FeedImageView(ViewImage.this);
       // imageView1 = (FeedImageView) findViewById(R.id.feedImage1);
        //       setContentView(imageView1);

            Intent callIntent = getIntent();
            Bundle callBundle = callIntent.getBundleExtra("package");
            imageView1.setImageUrl(callBundle.getString("img"), imageLoader);
        setContentView(imageView1);







    }}