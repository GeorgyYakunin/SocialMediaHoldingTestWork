package com.example.socialmediaholdingtestwork;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {
    private static final String TAG = "MessageListAdapter";
    private static final int VIEW_TYPE_MESSAGE_SENT = 0;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 1;
    private Context mContext;
    private ArrayList<Message> mMessageList;

    public MessageListAdapter(Context context, ArrayList<Message> messageList) {
        Log.e(TAG, "MessageListAdapter");
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType");
        Message message = mMessageList.get(position);
        if (message.getIsReceived() == true) {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        } else {
            return VIEW_TYPE_MESSAGE_SENT;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.e(TAG, "onCreateViewHolder");
        View view;
        ViewHolder vh;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_sent, viewGroup, false);
            Log.e(TAG, "onCreateViewHolder item_message_sent");
            vh =  new SentMessageHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_message_received, viewGroup, false);
            Log.e(TAG, "onCreateViewHolder item_message_received");
            vh =  new ReceivedMessageHolder(view);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Log.e(TAG, "onBindViewHolder");
        Message message = (Message) mMessageList.get(i);
        int typeViewOfPosition = getItemViewType(i);
        int typeViewOfNextPosition;
        if(i+1 != mMessageList.size()){
            typeViewOfNextPosition = getItemViewType(i+1);
        } else{
            typeViewOfNextPosition = -1;
        }
        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) viewHolder).bind(message, typeViewOfPosition, typeViewOfNextPosition);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount " + mMessageList.size());
        return mMessageList.size();
    }

    class SentMessageHolder extends ViewHolder{
        Message message;
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            timeText.setTextColor(Color.parseColor("#8197d3"));
        }
        void bind(Message message, int position, int pastPosition) {
            messageText.setText(message.getMessage());
            if(position == pastPosition ){
                timeText.setVisibility(View.GONE);
            } else {
                timeText.setText(message.getCreatedAt());
            }
        }
    }

    class ReceivedMessageHolder extends ViewHolder {
        TextView messageText, timeText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            messageText.setTextColor(Color.parseColor("#8197d3"));
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            timeText.setTextColor(Color.parseColor("#8197d3"));
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
            Picasso.get().load(R.drawable.user_photo)
                    .transform(new CircleTransform())
                    .into(profileImage);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
                timeText.setText(message.getCreatedAt());

        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
        }
    }
}
