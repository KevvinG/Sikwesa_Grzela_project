package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatListAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Conversation;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ChatListActivity extends Activity {

    private RecyclerView mChatRecycler;
    //private ChatPagePracticeAdapter mMessageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    DatabaseReference myRefUser;
    ArrayList<Conversation> convoList;
    FirebaseDatabase database;
    ChatListAdapter adapter;
    RecyclerView recyclerView;
    String user;
    ArrayList<String> people;
//    ArrayList<String> senders;
//    String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        convoList = new ArrayList<Conversation>();
//        senders = new ArrayList<>();
        people = new ArrayList<>();
        database = FirebaseDatabase.getInstance();

        recyclerView = findViewById(R.id.recyclerview_chat_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatListActivity.this));


        user = getIntent().getStringExtra("user");
//        Toast.makeText(ChatListActivity.this, user, Toast.LENGTH_SHORT).show();
//        getUserInfo(user);
        myRef = database.getReference("message/" + user);
        UpdateData();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_new_chat) {
            Intent intent = new Intent(getApplicationContext(), NewChatActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setRecycler(ArrayList<Conversation> convoList) {
//        adapter = new ChatListAdapter(ChatListActivity.this, convoList);

        //adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);

        recyclerView.setAdapter(new ChatListAdapter(ChatListActivity.this, convoList, new ChatListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Conversation convo) {
//                Toast.makeText(getApplicationContext(), convo.getSender(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ChatPageActivity.class);
                intent.putExtra("conversation", convo);
                intent.putExtra("user2",convo.getRecipient());
                intent.putExtra("user", user);
                startActivity(intent);
            }

        }));
    }
    public void UpdateData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                convoList.clear();
                people.clear();
//                senders.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String receiver = (String) dataSnapshot1.child("receiver").getValue();
                    String sender = (String) dataSnapshot1.child("sender").getValue();
                    String msgLast = "Test";

                    if(receiver.equalsIgnoreCase(user)) {
                        String temp = receiver;
                        receiver = sender;
                        sender = temp;
                    }

                    Conversation convo = new Conversation(sender, receiver, msgLast);
                    if (!(people.contains(receiver)) && !(people.contains(sender))) {
                        convoList.add(convo);
                        setRecycler(convoList);
                        if (!(receiver.equalsIgnoreCase(user))) {
                            people.add(receiver);
                        }
                        if (!(sender.equalsIgnoreCase(user))) {
                            people.add(sender);
                        }
                    }

//                    String name = (String) dataSnapshot1.child("name").getValue();
//                    long time = (long) dataSnapshot1.child("time").getValue();
//                    int uid = dataSnapshot1.child("userId").getValue(Integer.class);
//                    Message message = new Message(msg, name, time, uid);
//                    convoList.add(convo);
//                    Collections.sort(convoList);


//                    setRecycler(convoList);

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
}
