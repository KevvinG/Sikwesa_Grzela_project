package com.example.kevingrzela.sikwesa_grzela_project.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kevingrzela.sikwesa_grzela_project.R;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private List<Conversation> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private final ItemClickListener listener;



    // data is passed into the constructor
    public ChatListAdapter(Context context, List<Conversation> data, ItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.listener = listener;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = mInflater.inflate(R.layout.conversation_list_item, parent, false);
        return new ViewHolder(view);
        }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int itemType = getItemViewType(position);


        String receiver = mData.get(position).getRecipient();
        String sender = mData.get(position).getSender();
        holder.myTextView.setText(receiver);
        holder.myTextView2.setText(sender);

        holder.bind(mData.get(position), listener);
    }



    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        TextView myTextView2;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.txt_chat_name);
            myTextView2 = itemView.findViewById(R.id.txt_chat_preview);
//            myTextView = itemView.findViewById(R.id.text_message_body);
//            myTextView2 = itemView.findViewById(R.id.text_message_time);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }

        public void bind(final Conversation convo, final ItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(convo);
                }
            });
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
        void onItemClick(Conversation convo);
    }
}
