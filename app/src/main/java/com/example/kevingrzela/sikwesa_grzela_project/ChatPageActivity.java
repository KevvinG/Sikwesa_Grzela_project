package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Message;

import java.util.ArrayList;

public class ChatPageActivity extends Activity {
    private RecyclerView mChatRecycler;
    //private ChatPagePracticeAdapter mMessageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        ArrayList<Message> messageList = new ArrayList<Message>();

        Message test1 = new Message("This is a test send", "10/15/11", "Bob", 1);
        Message test2 = new Message("This is a test rec", "12/21/22", "John", 2);
        Message test3 = new Message("This is another test rec", "12/11/22", "John", 2);
        Message test4 = new Message("this is another test send", "20/11/22", "Bob", 1);
        Message test5 = new Message("This is a test send", "10/15/11", "Bob", 1);
        Message test6 = new Message("This is a test rec", "12/21/22", "John", 2);
        Message test7 = new Message("This is another test rec", "12/11/22", "John", 2);
        Message test8 = new Message("this is another test send", "20/11/22", "Bob", 1);
        Message test9 = new Message("This is a test rec", "12/21/22", "John", 2);
        Message test10 = new Message("This is another test rec", "12/11/22", "John", 2);
        Message test11 = new Message("this is another test send", "20/11/22", "Bob", 1);
        Message test12 = new Message("this is another test send", "20/11/22", "Bob", 1);
        Message test13 = new Message("This is a test rec", "12/21/22", "John", 2);
        Message test14 = new Message("This is another test rec", "12/11/22", "John", 2);
        Message test15 = new Message("this is another test send", "20/11/22", "Bob", 1);
        messageList.add(test1);
        messageList.add(test2);
        messageList.add(test3);
        messageList.add(test4);
        messageList.add(test5);
        messageList.add(test6);
        messageList.add(test7);
        messageList.add(test8);
        messageList.add(test9);
        messageList.add(test10);
        messageList.add(test11);
        messageList.add(test12);
        messageList.add(test13);
        messageList.add(test14);
        messageList.add(test15);
        ChatPageAdapter adapter;

            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.recyclerview_message_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ChatPageAdapter(this, messageList);
            //adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }

//        @Override
//        public void onItemClick(View view, int position) {
//            Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//
//    }
}
