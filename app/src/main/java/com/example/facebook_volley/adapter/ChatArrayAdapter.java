package com.example.facebook_volley.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.facebook_volley.R;
import com.example.facebook_volley.data.ChatMessageItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by MyConputer on 7/7/2015.
 */
public class ChatArrayAdapter extends ArrayAdapter<ChatMessageItem>{

    private TextView chatText;
    private List<ChatMessageItem> chatMessageList = new ArrayList<ChatMessageItem>();
    private LinearLayout singleMessageContainer;

    public void add(ChatMessageItem object)
    {
        chatMessageList.add(object);
        super.add(object);
    }

    public ChatArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    public int getCount() {
        return this.chatMessageList.size();
    }

    public ChatMessageItem getItem(int index) {
        return this.chatMessageList.get(index);
    }
    @Override
    public View getView (int position ,View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.tinnhan_canhan_item, parent, false);
        }

               singleMessageContainer = (LinearLayout) row.findViewById(R.id.singleMessageContainer);
        ChatMessageItem chatMessageObj = getItem(position);
        chatText = (TextView) row.findViewById(R.id.singleMessage);
        chatText.setText(chatMessageObj.message);
        chatText.setBackgroundResource(chatMessageObj.left ? R.drawable.hinh_tron1 : R.drawable.hinh_tron_right);
        singleMessageContainer.setGravity(chatMessageObj.left ? Gravity.RIGHT : Gravity.LEFT);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
