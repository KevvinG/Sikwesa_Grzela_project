package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.DataOutput;

public class MainActivity extends Activity {
    private static final int SIGN_UP = 2;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    DatabaseReference myRefUser;
    FirebaseDatabase database;


    EditText edtUserName;
    EditText edtPassword;

//    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignIn = findViewById(R.id.btn_signin);
        Button btnSignUp = findViewById(R.id.btn_signup);
        edtUserName = findViewById(R.id.edt_name_input);
        edtPassword = findViewById(R.id.edt_pass_input);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivityForResult(intent, SIGN_UP);
            }
        });

    }//onCreate


    private void signInUser() {

        final String email = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            getUserInfo(email);
                            String[] testing = email.split("@");
                            String userName = testing[0];
                            Intent intent = new Intent(getApplicationContext(), ChatListActivity.class);
                            intent.putExtra("user",userName);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getUserInfo(final String user) {

        myRefUser = database.getReference("users");

        myRefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    String email = (String) dataSnapshot2.child("email").getValue();
                    if (email.equalsIgnoreCase(user)) {
                        String usern = (String) dataSnapshot2.child("userName").getValue();
//                        Toast.makeText(MainActivity.this, usern, Toast.LENGTH_LONG).show();
//                        userName=usern;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == SIGN_UP) {
            if (resultCode == RESULT_OK) {
                String email = data.getStringExtra("email");
//                userName = data.getStringExtra("name");
                ((EditText)findViewById(R.id.edt_name_input)).setText(email);
            } else {
                Toast.makeText(this, "Cancel tapped", Toast.LENGTH_LONG).show();
            }//else
        }//if
    }
}
