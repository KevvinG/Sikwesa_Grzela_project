package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kevingrzela.sikwesa_grzela_project.ChatPageActivity;
import com.example.kevingrzela.sikwesa_grzela_project.R;

import java.util.List;

public class ChatPageAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Message> mMessageList;

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    public ChatPageAdapter(Context context, List<Message> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType (int position) {
        Message messageInstance = mMessageList.get(position);

        if (messageInstance.getUserId() == 1) {
            // If the current user is the sender of the message
            return VIEW_TYPE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_RECEIVED;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View view;

        switch (i) {
            case VIEW_TYPE_SENT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.sent_message_complete, parent, false);
                return new SentMessageHolder(view);
            case VIEW_TYPE_RECEIVED:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.received_message_complete, parent, false);
                return new ReceivedMessageHolder(view);
        }
        return null;
    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Message message = (Message) mMessageList.get(i);

        switch (viewHolder.getItemViewType()) {
            case VIEW_TYPE_SENT:
                ((SentMessageHolder) viewHolder).bind(message);
                break;
            case VIEW_TYPE_RECEIVED:
                ((ReceivedMessageHolder) viewHolder).bind(message);
        }

    }//onBindViewHolder

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime;

        SentMessageHolder(View view) {
            super(view);

            txtMessage = (TextView) view.findViewById(R.id.text_message_body);
            txtTime = (TextView) view.findViewById(R.id.text_message_time);
        }

        void bind(Message message) {
            txtMessage.setText(message.getMessage());

            txtTime.setText(message.getTime());

        }

    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView txtMessage, txtTime, txtSenderName;
        ImageView profilePic;

        ReceivedMessageHolder(View view) {
            super(view);

            txtMessage = (TextView) view.findViewById(R.id.text_message_body);
            txtTime = (TextView) view.findViewById(R.id.text_message_time);
            txtSenderName = (TextView) view.findViewById(R.id.text_message_name);
            profilePic = (ImageView) view.findViewById(R.id.image_message_profile);
        }

        void bind(Message message) {
            txtMessage.setText(message.getMessage());

            txtTime.setText(message.getTime());

            txtSenderName.setText(message.getName());

            //Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profilePic);

        }

    }

}