package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int SIGN_UP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignIn = findViewById(R.id.btn_signin);
        Button btnSignUp = findViewById(R.id.btn_signup);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChatPageActivity.class);
                startActivity(intent);
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
