package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kevingrzela.sikwesa_grzela_project.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends Activity {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        Button btnCreate = findViewById(R.id.btn_create_account);
        Button btnCancel = findViewById(R.id.btn_cancel);
        final EditText userNameCreate = findViewById(R.id.edt_create_username);
        final EditText emailCreate = findViewById(R.id.edt_create_email);
        EditText passwordCreate = findViewById(R.id.edt_create_password);
        final EditText passwordConfirm = findViewById(R.id.edt_confirm_password);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailCreate.getText().toString();
                String password = passwordConfirm.getText().toString();
                String userName = userNameCreate.getText().toString();
                //Implement error checking
                User user = new User(userName,password,email);
                //Add the user to db
                addUser(user);
                //Logic to pass username back to login screen for QOL
                Intent data = new Intent();
                data.putExtra("message", email);
                addUser(user);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void addUser(final User u) {
        final User userTest = u;
//TODO: Implement error checking here (make sure two users of the same username cannot exist)

        mAuth.createUserWithEmailAndPassword(userTest.getEmail().toString(), userTest.getPassword().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mDatabase.child("users").child(userTest.getUserName()).setValue(userTest);
                        } else {
                            Toast.makeText(SignUp.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("SignUP:", "onComplete: Failed=" + task.getException().getMessage());
                        }
                    }
                });

    }
}
