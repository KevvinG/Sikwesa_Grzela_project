package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPagePracticeAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPagePracticeAdapterV2;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatPageActivity extends Activity {
    private RecyclerView mChatRecycler;
    private ChatPagePracticeAdapter mMessageAdapter;
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

        messageList.add(test1);
        messageList.add(test2);
        messageList.add(test3);
        messageList.add(test4);

        Message[] messageArray = new Message[2];

        messageArray[0] = test1;
        messageArray[1] = test2;

//        mChatRecycler = (RecyclerView) findViewById(R.id.recyclerview_message_list);
//        mLayoutManager = new LinearLayoutManager(this);
//        mChatRecycler.setLayoutManager(mLayoutManager);
        //mMessageAdapter = new ChatPageAdapter(this, messageList);
        //mMessageAdapter = new ChatPagePracticeAdapter(messageArray);
        //mChatRecycler.setAdapter(mMessageAdapter);
        //mChatRecycler.setLayoutManager(new LinearLayoutManager(this));


        ChatPagePracticeAdapterV2 adapter;


            // data to populate the RecyclerView with
            ArrayList<String> animalNames = new ArrayList<>();
            animalNames.add("Horse");
            animalNames.add("Cow");
            animalNames.add("Camel");
            animalNames.add("Sheep");
            animalNames.add("Goat");
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

            // set up the RecyclerView
            RecyclerView recyclerView = findViewById(R.id.recyclerview_message_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ChatPagePracticeAdapterV2(this, messageList);
            //adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
        }

//        @Override
//        public void onItemClick(View view, int position) {
//            Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//
//    }
}
