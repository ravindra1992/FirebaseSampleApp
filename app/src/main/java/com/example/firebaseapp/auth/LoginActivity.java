package com.example.firebaseapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebaseapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    EditText editTextTextEmailAddress, editTextNumberPassword;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        login= findViewById(R.id.login);
        editTextTextEmailAddress= findViewById(R.id.editTextTextEmailAddress);
        editTextNumberPassword=findViewById(R.id.editTextNumberPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email= editTextTextEmailAddress.getText().toString();
                String text_password= editTextNumberPassword.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(text_email, text_password).addOnCompleteListener(LoginActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login user successfull", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, LogoutActivity.class));
                                    finish();

                                }else
                                {
                                    Toast.makeText(LoginActivity.this, "Login Failed.."+task.getException(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });


    }
}