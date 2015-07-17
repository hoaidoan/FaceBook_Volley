package com.example.facebook_volley.widget;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.facebook_volley.R;
import com.example.facebook_volley.adapter.ChatArrayAdapter;
import com.example.facebook_volley.data.ChatMessageItem;
import com.example.facebook_volley.data.FeedItem;
import com.example.facebook_volley.data.MessengerItem;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * Created by MyConputer on 7/6/2015.
 */
public class Feedtinnhan_dialog extends Activity {


    private static final String TAG = "ChatActivity";

    private ChatArrayAdapter chatArrayAdapter;
    private ListView listView;
    private EditText chatText;
    private RelativeLayout buttonSend;
    private boolean side = true;
    private TextView name;
    private SecureRandom random;
    Thread thread = new Thread();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tinnhan_canhan_dialog);

        Intent intent = getIntent();
        MessengerItem item = (MessengerItem) intent.getSerializableExtra("Item");
        name = (TextView) findViewById(R.id.txttennguoint);
        name.setText(item.getName());

        buttonSend = (RelativeLayout) findViewById(R.id.realcmtn);

        listView = (ListView) findViewById(R.id.listtndialog);

        chatArrayAdapter = new ChatArrayAdapter(getApplicationContext(), R.layout.tinnhan_canhan_item);
        listView.setAdapter(chatArrayAdapter);

        chatText = (EditText) findViewById(R.id.edtcmtn);
        chatText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    try {
                        return sendChatMessage();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                try {
                    sendChatMessage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        });

        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);

        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });


    }

    private boolean sendChatMessage() throws InterruptedException {
        chatArrayAdapter.add(new ChatMessageItem(side, chatText.getText().toString()));
        chatText.setText("");
      //  side = !side;
        thread.sleep(2000);
        autoChatMessage();
        return true;
    }

    private void autoChatMessage() throws InterruptedException {
        random = new SecureRandom();
        String result = new BigInteger(130, random).toString(32);
        chatArrayAdapter.add(new ChatMessageItem(false, result));

    }
}




