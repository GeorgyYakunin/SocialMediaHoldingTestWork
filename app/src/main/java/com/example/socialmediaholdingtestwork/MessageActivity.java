package com.example.socialmediaholdingtestwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ArrayList<Message> messageList;
    private ArrayList<String> messages;
    private ImageView dot;
    private ImageButton settings;
    private Intent intent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        mContext = this;

        settings = findViewById(R.id.button_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, PayActivity.class);
                mContext.startActivity(intent);
            }
        });

        dot = findViewById(R.id.dot);
        Picasso.get().load(R.drawable.dot)
                .transform(new CircleTransform())
                .into(dot);

        messageList = new ArrayList();
        messageList.add(new Message("Also tell me if u get an invitation on the calendar now.", "12/10 2015 9.30", true));
        messageList.add(new Message("Nothing yet", "12/10 2015 9.35", false));
        messageList.add(new Message("So virtual Tuesday my 9.30?", "12/10/2015 9.35", false));
        messageList.add(new Message("Deal", "12/10/2015 9.42", true));


        mMessageRecycler = (RecyclerView) findViewById(R.id.rv_message_list);
        mMessageRecycler.setBackgroundColor(Color.parseColor("#edf3ff"));
        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
    }
}
