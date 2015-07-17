package com.example.facebook_volley.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.facebook_volley.FeedActivity;
import com.example.facebook_volley.MessengerActivity;
import com.example.facebook_volley.MoreActivity;
import com.example.facebook_volley.RequestActivity;


/**
 * Created by Doan Vo Viet Hoai on 6/9/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int img[];
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ViewPagerAdapter(FragmentManager fm, int[] img) {
        super(fm);
        this.img = img;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeedActivity();
            case 1:
                return new RequestActivity();
            case 2:
                return new MessengerActivity();
            case 3:
                return new MessengerActivity();
            case 4:
                return new MoreActivity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    public  int getDrawableId(int position){
        return img[position];
    }
}



