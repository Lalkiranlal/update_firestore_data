package com.example.retreivedatafromfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    FirebaseFirestore firestore;
    EditText oldname,newname;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        firestore=FirebaseFirestore.getInstance();
        oldname=findViewById(R.id.old);
        newname=findViewById(R.id.newname);
        button=findViewById(R.id.button3);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Newname=newname.getText().toString().trim();

                Map<String,Object> user =new HashMap<>();

                user.put("fname",Newname);
                firestore.collection("students").whereEqualTo("fname","amal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful() && !task.getResult().isEmpty())
                        {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID=documentSnapshot.getId();
                            firestore.collection("students").document(documentID).update(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(MainActivity3.this, "data updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(MainActivity3.this, "failed to insert data", Toast.LENGTH_SHORT).show();
                                }
                            });



                                // Log.d("Read data ",documentSnapshot.getId()+"===="+documentSnapshot.getData());

                        }

                    }
                });

            }
        });
    }
}