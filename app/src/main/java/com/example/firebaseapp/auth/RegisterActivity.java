package com.example.firebaseapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseapp.R;
import com.example.firebaseapp.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    Button register;
    EditText editTextTextEmailAddress, editTextNumberPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register= findViewById(R.id.register);
        editTextTextEmailAddress= findViewById(R.id.editTextTextEmailAddress);
        editTextNumberPassword=findViewById(R.id.editTextNumberPassword);
        firebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text_email= editTextTextEmailAddress.getText().toString();
                String text_password= editTextNumberPassword.getText().toString();

                if(TextUtils.isEmpty(text_email)|| TextUtils.isEmpty(text_password)){
                    Toast.makeText(RegisterActivity.this, "Empty Credentials", Toast.LENGTH_SHORT).show();
                }else if(text_password.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password too short", Toast.LENGTH_SHORT).show();

                }else{
                    registerUser(text_email,text_password);
                }

            }
        });

    }

    private void registerUser(String text_email, String text_password) {

        firebaseAuth.createUserWithEmailAndPassword(text_email, text_password).addOnCompleteListener(RegisterActivity.this,
                new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Registering user successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, StartActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registering Failed..", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}