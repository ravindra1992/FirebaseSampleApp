package com.example.firebaseapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseapp.R;
import com.example.firebaseapp.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LogoutActivity extends AppCompatActivity {

    Button logout, add;
    TextView text_name;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logout=findViewById(R.id.logout);
        firebaseAuth= FirebaseAuth.getInstance();
        add= findViewById(R.id.add);
        text_name= findViewById(R.id.text_name);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= text_name.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("FirebaseApp").push().child("Name").setValue(name);
                Toast.makeText(getApplicationContext(), "Add name to db..", Toast.LENGTH_LONG).show();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // retriving database contents
                retriveDatabase();

               /* firebaseAuth.signOut();
                Toast.makeText(LogoutActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LogoutActivity.this, StartActivity.class));*/
            }
        });




       /* HashMap<String, Object> map=new HashMap<>();
        map.put("Name", "Ravindra");
        map.put("Address", "GHZ");
        map.put("Email", "ravindradangwal01@gmail.com");
        FirebaseDatabase.getInstance().getReference().child("FirebaseApp").child("personValues").updateChildren(map);*/
    }



    public void retriveDatabase(){
        DatabaseReference df= FirebaseDatabase.getInstance().getReference().child("FirebaseApp");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    Log.e("debug", String.valueOf(snapshot1.getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}