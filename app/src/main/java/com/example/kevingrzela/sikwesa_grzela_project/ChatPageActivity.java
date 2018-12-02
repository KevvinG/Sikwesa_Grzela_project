package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatPageActivity extends Activity {
    private RecyclerView mChatRecycler;
    private ChatPageAdapter mMessageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        List<Message> messageList = new ArrayList<Message>();

        Message test1 = new Message("This is a test send", "10/15/11", "Bob", 1);
        Message test2 = new Message("This is a test rec", "12/21/22", "John", 2);
        Message test3 = new Message("This is another test rec", "12/11/22", "John", 2);
        Message test4 = new Message("this is another test send", "20/11/22", "Bob", 1);

        messageList.add(test1);
        messageList.add(test2);
        messageList.add(test3);
        messageList.add(test4);

        mChatRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new ChatPageAdapter(this, messageList);
        mChatRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
//Creating a message object, this will likely have to be moved to a different location

}
