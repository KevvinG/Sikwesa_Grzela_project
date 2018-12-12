package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatListAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatPageActivity extends Activity {
    private RecyclerView mChatRecycler;
    //private ChatPagePracticeAdapter mMessageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    DatabaseReference myRef;
    ArrayList<Message> messageList;
    FirebaseDatabase database;
    ChatPageAdapter adapter;
    RecyclerView recyclerView;
    Button btnSend;
    EditText edtMessage;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        btnSend = findViewById(R.id.button_chatbox_send);
        edtMessage = findViewById(R.id.edittext_chatbox);
        messageList = new ArrayList<Message>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDB = mDatabase.child("posts");


        userEmail = getIntent().getStringExtra("user");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texting = edtMessage.getText().toString();

                Message message = new Message(texting, userEmail, System.currentTimeMillis(), 0);

//                String key = mDatabase.child("posts").push().getKey();
//                Map<String, Object> postValues = message.toMap();
//
//                Map<String, Object> childUpdates = new HashMap<>();
//                childUpdates.put("/posts/" + key, postValues);
//
//                mDatabase.updateChildren(childUpdates);

                String key = myRef.push().getKey();

                myRef.child(key).setValue(message);
                edtMessage.setText("");

                UpdateData();
            }
        });

        recyclerView = findViewById(R.id.recyclerview_message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatPageActivity.this));
        UpdateData();
    }


    public void setRecycler(ArrayList<Message> messageList) {
        adapter = new ChatPageAdapter(ChatPageActivity.this, messageList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

        public void UpdateData() {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   messageList.clear();
                    // StringBuffer stringbuffer = new StringBuffer();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

//                            Message message = dataSnapshot1.getValue(Message.class);
                        String msg = (String) dataSnapshot1.child("message").getValue();
//                            Toast.makeText(ChatPageActivity.this, name, Toast.LENGTH_LONG).show();
                        String name = (String) dataSnapshot1.child("name").getValue();
                        long time = (long) dataSnapshot1.child("time").getValue();
                        int uid = dataSnapshot1.child("userId").getValue(Integer.class);
                        Message message = new Message(msg, name, time, uid);
                        messageList.add(message);
                        Collections.sort(messageList);
                        setRecycler(messageList);

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

//        @Override
//        public void onItemClick(View view, int position) {
//            Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//
//    }
