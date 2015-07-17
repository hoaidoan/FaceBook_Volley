package com.example.facebook_volley.adapter;

/**
 * Created by Doan Vo Viet Hoai on 6/19/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.facebook_volley.FeedImageView;
import com.example.facebook_volley.R;
import com.example.facebook_volley.app.AppController;
import com.example.facebook_volley.data.FeedItem;
import com.example.facebook_volley.data.RequestItem;

import java.util.List;

/**
 * Created by PHI LONG on 16/06/2015.
 */
public class RequestListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<RequestItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public RequestListAdapter(Activity activity, List<RequestItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView name;
        NetworkImageView profilePic ;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.request_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder)convertView.getTag();

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        holder.name = (TextView) convertView.findViewById(R.id.name);
               holder.profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        RequestItem item = feedItems.get(position);
        holder.name.setText(item.getName());
        holder.profilePic.setImageUrl(item.getProfilePic(), imageLoader);
        return convertView;
    }
}
