package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.kevingrzela.sikwesa_grzela_project.R;
import java.util.List;

public class ChatPagePracticeAdapter extends RecyclerView.Adapter<ChatPagePracticeAdapter.MyViewHolder> {
    private Context mContext;
    private Message[] messages;

    private static final int SENT = 1;
    private static final int RECEIVED = 2;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyViewHolder(TextView v) {
            super(v);
            mTextView=v;
        }
    }

    public ChatPagePracticeAdapter(Message[] messageList) {
        messages = messageList;
    }

    @Override
    public ChatPagePracticeAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.sent_message_complete, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(messages[i].getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.length;
    }
}


