package com.example.retreivedatafromfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText name,department,phone;
    Button submit;
    FirebaseFirestore firestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =(EditText) findViewById(R.id.name);
        department = findViewById(R.id.department);
        phone =findViewById(R.id.phone);
        submit=findViewById(R.id.button);


        firestore= FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dept=department.getText().toString().trim();
                String Name=name.getText().toString().trim();
                String Phonenumber=phone.getText().toString();
                Map<String,Object> user =new HashMap<>();
                user.put("fname",Name);
                user.put("fph",Phonenumber);
                user.put("dept",Dept);

                firestore.collection("students").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Intent i = new Intent(MainActivity.this,MainActivity2.class);
                        i.putExtra("key",Name);

                        startActivity(i);

                    }
                });

            }
        });





    }
}