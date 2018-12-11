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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {
    private static final int SIGN_UP = 2;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    EditText edtUserName;
    EditText edtPassword;

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

        String email = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), ChatPageActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == SIGN_UP) {
            if (resultCode == RESULT_OK) {
                String userName = data.getStringExtra("message");
                ((EditText)findViewById(R.id.edt_name_input)).setText(userName);
            } else {
                Toast.makeText(this, "Cancel tapped", Toast.LENGTH_LONG).show();
            }//else
        }//if
    }
}
