package com.example.kevingrzela.sikwesa_grzela_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnCreate = findViewById(R.id.btn_create_account);
        Button btnCancel = findViewById(R.id.btn_cancel);
        EditText userNameCreate = findViewById(R.id.edt_create_username);
        final EditText emailCreate = findViewById(R.id.edt_create_email);
        EditText passwordCreate = findViewById(R.id.edt_create_password);
        EditText passwordConfirm = findViewById(R.id.edt_confirm_password);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailCreate.getText().toString();
                //Logic to pass username back to login screen for QOL
                Intent data = new Intent();
                data.putExtra("message", email);

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
}
