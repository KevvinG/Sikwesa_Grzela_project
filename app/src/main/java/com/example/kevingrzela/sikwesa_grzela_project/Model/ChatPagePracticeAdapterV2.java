package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevingrzela.sikwesa_grzela_project.R;

import org.w3c.dom.Text;

import java.util.List;

public class ChatPagePracticeAdapterV2 extends RecyclerView.Adapter<ChatPagePracticeAdapterV2.ViewHolder> {
    private List<Message> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public static final int MESSAGE_SENT = 0;
    public static final int MESSAGE_RECEIVED = 1;

    // data is passed into the constructor
    public ChatPagePracticeAdapterV2(Context context, List<Message> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == MESSAGE_SENT) {
            view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
            return new ViewHolder(view);
        } else {
            view = mInflater.inflate(R.layout.received_message_complete, parent, false);
            return new ViewHolder(view);
        }
//        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);


        String animal = mData.get(position).getMessage();
        String time = mData.get(position).getTime();
        holder.myTextView.setText(animal);
        holder.myTextView2.setText(time);
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.get(position).getUserId() == 1) {
            return MESSAGE_SENT;
        } else {
            return MESSAGE_RECEIVED;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myTextView2;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.text_message_body);
            myTextView2 = itemView.findViewById(R.id.text_message_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
//    String getItem(int id) {
//        //return mData.get(id);
//    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}