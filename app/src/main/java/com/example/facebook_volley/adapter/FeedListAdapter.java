package com.example.facebook_volley.adapter;

import com.example.facebook_volley.FeedImageView;
import com.example.facebook_volley.FriendOnlineMenuActivity;
import com.example.facebook_volley.R;
import com.example.facebook_volley.ViewImage;
import com.example.facebook_volley.app.AppController;
import com.example.facebook_volley.customerviews.TouchImageView;
import com.example.facebook_volley.data.CommentItem;
import com.example.facebook_volley.data.FeedItem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by PHI LONG on 16/06/2015.
 */
public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    Dialog dialog = null;
    CommentAdapter commentAdapter;
    private ListView dialog_ListView;
    List<CommentItem> commentItems = new ArrayList<CommentItem>();

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FeedListAdapter(Activity activity, List<FeedItem> feedItems) {
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
        TextView name,timestamp, statusMsg, url, countLike, like, dislike;
        NetworkImageView profilePic ;
        FeedImageView feedImageView;
        TextView tv_like;
        RelativeLayout btn_comment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.feed_item, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
        }
        else
        holder = (ViewHolder)convertView.getTag();

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        holder.name = (TextView) convertView.findViewById(R.id.name);
        holder.timestamp = (TextView) convertView
                .findViewById(R.id.timestamp);
        holder.statusMsg = (TextView) convertView
                .findViewById(R.id.txtStatusMsg);
        holder.url = (TextView) convertView.findViewById(R.id.txtUrl);
        holder.profilePic = (NetworkImageView) convertView
                .findViewById(R.id.profilePic);
        holder.feedImageView = (FeedImageView) convertView
                .findViewById(R.id.feedImage1);



       // holder.tv_like=(TextView)convertView.findViewById(R.id.tv_like);

        holder.like =(TextView)convertView.findViewById(R.id.btn_like);
        holder.dislike =(TextView)convertView.findViewById(R.id.btn_dislike);
        holder.btn_comment = (RelativeLayout)convertView.findViewById(R.id.rl_comment);

        final FeedItem item = feedItems.get(position);

        holder.name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        holder.timestamp.setText(timeAgo);

        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            holder.statusMsg.setText(item.getStatus());
            holder.statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            holder.statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            holder.url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            holder.url.setMovementMethod(LinkMovementMethod.getInstance());
            holder. url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            holder.url.setVisibility(View.GONE);
        }

        // user profile pic
        holder.profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImge() != null) {
            holder.feedImageView.setImageUrl(item.getImge(), imageLoader);
            holder.feedImageView.setVisibility(View.VISIBLE);
            holder.feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });


        } else {
            holder.feedImageView.setVisibility(View.GONE);
        }

        // Listner for touch imageview
        holder.feedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity, ViewImage.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("img", item.getImge());
//                intent.putExtra("package", bundle);
//                activity.startActivity(intent);         code dung

                final Dialog dialog = new Dialog(activity,R.style.FullHeightDialog);
                dialog.setContentView(R.layout.tam_thoi);
                TouchImageView view = (TouchImageView)dialog.findViewById(R.id.feedImage1);
                //view.setImageUrl(item.getImge(), imageLoader);
                //dialog.getWindow().setLayout(500,800);
                Picasso.with(activity).load(item.getImge()).into(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


            }
        });



     holder.like.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             item.setLike(true);
             notifyDataSetChanged();
         }
     });

        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setLike(false);
                notifyDataSetChanged();
            }
        });


        if (item.isLike() == false) {
            holder.dislike.setVisibility(View.INVISIBLE);
            holder.like.setVisibility(View.VISIBLE);
        } else {
            holder.like.setVisibility(View.INVISIBLE);
            holder.dislike.setVisibility(View.VISIBLE);
        }

        //comment

        holder.btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(activity, R.style.FullHeightDialog);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
                dialog.setContentView(R.layout.comment_dialog);

                final CommentItem itemComment = new CommentItem();
                commentAdapter = new CommentAdapter(activity, commentItems);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setTitle("Custom Dialog");
//                dialog.setCancelable(true);
//                dialog.setCanceledOnTouchOutside(true);
                //Listview trong comment

                dialog_ListView = (ListView) dialog.findViewById(R.id.list);
                itemComment.setName("Tí");
                itemComment.setAvt(R.drawable.a2);
                itemComment.setComment("aaaaaaaaaaa");
                commentItems.add(itemComment);
                dialog_ListView.setAdapter(commentAdapter);


                dialog.show();
                final EditText comment = (EditText) dialog.findViewById(R.id.comment);
                final ImageView enter = (ImageView) dialog.findViewById(R.id.enter);
                final TextView likes = (TextView) dialog.findViewById(R.id.likes);
                final ImageView like = (ImageView) dialog.findViewById(R.id.like);
                final ImageView liked = (ImageView) dialog.findViewById(R.id.liked);
                final ImageView exit = (ImageView) dialog.findViewById(R.id.imageView2);

                like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setLike(true);
                        int a = item.getCountLike();
                        item.setCountLike(a + 1);
                        likes.setText("You and "+(item.getCountLike()-1) +" other person liked it" );
                        like.setVisibility(View.INVISIBLE);
                        liked.setVisibility(View.VISIBLE);
                        notifyDataSetChanged();
                    }
                });

                liked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setLike(false);
                        int a = item.getCountLike();
                        item.setCountLike(a - 1);
                        notifyDataSetChanged();
                        like.setVisibility(View.VISIBLE);
                        liked.setVisibility(View.INVISIBLE);
                        likes.setText(item.getCountLike() + " others person liked it");

                    }
                });


                comment.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String strUserName = comment.getText().toString();
                        enter.setVisibility(View.INVISIBLE);
                        if (strUserName.trim().equals("")) {
                            enter.setVisibility(View.INVISIBLE);
                        } else
                            enter.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommentItem itemComment = new CommentItem();
                        Toast.makeText(activity, "up", Toast.LENGTH_SHORT).show();

                        itemComment.setAvt(R.drawable.a1);
                        itemComment.setComment(comment.getText().toString());
                        itemComment.setName("Me");

                        commentItems.add(itemComment);

                        comment.setText("");
                        commentAdapter.notifyDataSetChanged();
                        Log.d("aaaaaaaaaaaaaaa", String.valueOf(commentItems.size()));
                    }
                });
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hide();
                    }
                });

            }
        });

        return convertView;
    }
}
