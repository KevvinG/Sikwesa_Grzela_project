package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewChatActivity extends Activity {

    Button btnChat;
    EditText edtChatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_chat);

        edtChatName = findViewById(R.id.edt_new_chat);

        btnChat = findViewById(R.id.btn_start_chat);

        final String userName = getIntent().getStringExtra("user");

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user2 = edtChatName.getText().toString();
                Intent intent = new Intent(getApplicationContext(), ChatPageActivity.class);
                intent.putExtra("user", userName);
                intent.putExtra("user2", user2);
                startActivity(intent);
            }
        });
    }
}
