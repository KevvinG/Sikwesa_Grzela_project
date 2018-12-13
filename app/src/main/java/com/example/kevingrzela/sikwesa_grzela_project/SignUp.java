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

    DatabaseReference myRef;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        Button btnCreate = findViewById(R.id.btn_create_account);
        Button btnCancel = findViewById(R.id.btn_cancel);
        final EditText emailCreate = findViewById(R.id.edt_create_email);
        final EditText passwordCreate = findViewById(R.id.edt_create_password);
        final EditText passwordConfirm = findViewById(R.id.edt_confirm_password);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailCreate.getText().toString();   //takes name input and checksif textbox is empty
                if(email.isEmpty())
                    emailCreate.setError("Email is required field");
                String passwordC=passwordCreate.getText().toString();  //takes Id input and checks if textbox is empty
                if(passwordC.isEmpty())
                    passwordCreate.setError("Password is a required field");
                String passwordConf=passwordConfirm.getText().toString();
                if(passwordConf.isEmpty())
                    passwordConfirm.setError("Must confirm password");
                if((!email.isEmpty() && !passwordC.isEmpty() && passwordC.equals(passwordConf))) {
                    String[] names = email.split("@");
                    String userName = names[0];


                    User user = new User(userName,passwordConf,email);
                    addUser(user);
                } else {
                    passwordCreate.setError("Passwords must match");
                    passwordConfirm.setError("Passwords must match");
                }

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
//                            mDatabase.child("users").child(userTest.getUserName()).setValue(userTest);
                            String key = myRef.push().getKey();
                            myRef.child(key).setValue(userTest);
                            Intent data = new Intent();
                            data.putExtra("email", u.getEmail());
                            data.putExtra("name", u.getUserName());
                            addUser(u);
                            setResult(RESULT_OK, data);
                            finish();

                        } else {
                            Toast.makeText(SignUp.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                            Log.d("SignUP:", "onComplete: Failed=" + task.getException().getMessage());
                        }
                    }
                });

    }
}
