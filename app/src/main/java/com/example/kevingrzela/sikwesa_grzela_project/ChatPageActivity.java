package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatListAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.ChatPageAdapter;
import com.example.kevingrzela.sikwesa_grzela_project.Model.Conversation;
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
    DatabaseReference myRefUser1;
    DatabaseReference myRefUser2;
    ArrayList<Message> messageList;
    FirebaseDatabase database;
    ChatPageAdapter adapter;
    RecyclerView recyclerView;
    Button btnSend;
    EditText edtMessage;
    String userName;
    String user2;

    private static final int SENT = 1;
    private static final int RECEIVED = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        btnSend = findViewById(R.id.button_chatbox_send);
        edtMessage = findViewById(R.id.edittext_chatbox);
        messageList = new ArrayList<Message>();
        database = FirebaseDatabase.getInstance();
        Conversation convo = getIntent().getParcelableExtra("conversation");
        userName = getIntent().getStringExtra("user");
        user2 = getIntent().getStringExtra("user2");
        myRefUser1 = database.getReference("message/"+userName);
        myRefUser2 = database.getReference("message/"+user2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

//        DatabaseReference mDB = mDatabase.child("posts");



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texting = edtMessage.getText().toString();

                Message message = new Message(texting, System.currentTimeMillis(), userName, user2, 0);

                String key = myRefUser1.push().getKey();
                String key2 = myRefUser2.push().getKey();
                myRefUser1.child(key).setValue(message);
                myRefUser2.child(key2).setValue(message);
                edtMessage.setText("");

                UpdateData(userName, user2);
            }
        });//Adding a new message


        recyclerView = findViewById(R.id.recyclerview_message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatPageActivity.this));
        UpdateData(userName, user2);
    }


    public void setRecycler(ArrayList<Message> messageList) {
        adapter = new ChatPageAdapter(ChatPageActivity.this, messageList);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitems, menu);
        menu.findItem(R.id.action_new_chat).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.action_log_out) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if (id==R.id.action_back) {
            Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
            intent.putExtra("user", userName);
            startActivity(intent);
        } else if (id==R.id.action_delete) {
            DeleteData(userName, user2);
        }

        return super.onOptionsItemSelected(item);
    }

        public void UpdateData(final String user1, final String user2) {
            myRefUser1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   messageList.clear();
                    // StringBuffer stringbuffer = new StringBuffer();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        String msg = (String) dataSnapshot1.child("message").getValue();
                        long time = (long) dataSnapshot1.child("time").getValue();
                        String sender = (String) dataSnapshot1.child("sender").getValue();
                        String receiver = (String) dataSnapshot1.child("receiver").getValue();
                        if ((user1.equalsIgnoreCase(sender) && user2.equalsIgnoreCase(receiver))) {
                            Message message = new Message(msg, time, sender, receiver, SENT);
                            messageList.add(message);
                            Collections.sort(messageList);
                        } else if ((user1.equalsIgnoreCase(receiver)) && (user2.equalsIgnoreCase(sender))) {
                            Message message = new Message(msg, time, sender, receiver, RECEIVED);
                            messageList.add(message);
                            Collections.sort(messageList);
                        }
                        setRecycler(messageList);

                    }//forLoop


                }//onDataChange

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    //  Log.w(TAG, "Failed to read value.", error.toException());
                }//onCancelled
            });

        }

    public void DeleteData(final String user1, final String user2) {
        myRefUser1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messageList.clear();
                // StringBuffer stringbuffer = new StringBuffer();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    String msg = (String) dataSnapshot1.child("message").getValue();
                    long time = (long) dataSnapshot1.child("time").getValue();
                    String sender = (String) dataSnapshot1.child("sender").getValue();
                    String receiver = (String) dataSnapshot1.child("receiver").getValue();
                    if ((user1.equalsIgnoreCase(sender) && user2.equalsIgnoreCase(receiver))) {
                        dataSnapshot1.getRef().removeValue();
                    } else if ((user1.equalsIgnoreCase(receiver)) && (user2.equalsIgnoreCase(sender))) {
                        dataSnapshot1.getRef().removeValue();

                    }
                    Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
                    intent.putExtra("user", userName);
                    startActivity(intent);

                }//forLoop


            }//onDataChange

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //  Log.w(TAG, "Failed to read value.", error.toException());
            }//onCancelled
        });
    }




        }

//        @Override
//        public void onItemClick(View view, int position) {
//            Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
//
//    }

//    private void getUserInfo(final String user) {
//
//        myRefUser1 = database.getReference("users");
//
//        myRefUser1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
//                    String email = (String) dataSnapshot2.child("email").getValue();
//                    if (email.equalsIgnoreCase(user)) {
//                        String usern = (String) dataSnapshot2.child("userName").getValue();
//                        userName=usern;
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }