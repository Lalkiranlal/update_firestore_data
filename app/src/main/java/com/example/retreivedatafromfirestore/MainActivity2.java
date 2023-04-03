package com.example.retreivedatafromfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity2 extends AppCompatActivity {
    FirebaseFirestore firestore;
  Button button;
  ListView lv;

  List<String> values=new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       lv= findViewById(R.id.list);
        firestore=FirebaseFirestore.getInstance();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,values);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);

            }
        });







        firestore.collection("students").whereEqualTo("fname","amal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity2.this, "Read data", Toast.LENGTH_SHORT).show();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {

                        values.add(documentSnapshot.getString("fname")+"   \nph :"+documentSnapshot.getString("fph")+"\n"+ documentSnapshot.getString("dept"));

                        // Log.d("Read data ",documentSnapshot.getId()+"===="+documentSnapshot.getData());
                    }
                    arrayAdapter.notifyDataSetChanged();;
                }

            }
        });





}}