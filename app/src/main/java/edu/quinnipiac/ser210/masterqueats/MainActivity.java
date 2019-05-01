package edu.quinnipiac.ser210.masterqueats;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Giovanni");
        user.put("last", "Greco");
        user.put("qcard", 2306343);
        user.put("email", "gagreco@qu.edu");
        user.put("room", "Village 481");
        user.put("phone", "8604719580");


        //adds a user w first and last name
// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("sucess", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("fail", "Error adding document", e);
                    }
                });











        //setting text to DB  value
        //get reference of textview
        testText = findViewById(R.id.test);
        //get reference to DB
        DocumentReference docRef = db.collection("users").document("2o6lx0z4oc65Y3L2X0zR");
        //calls reference
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("msg", "DocumentSnapshot data: " + document.getData());
                        //here is where you can call data from the DB and access it
                        String username = document.get("first").toString();
                        testText.setText("Username " + username);
                    } else {
                        Log.d("msg", "No such document");
                    }
                } else {
                    Log.d("msg", "get failed with ", task.getException());
                }
            }
        });

*/
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,PlaceOrderActivity.class);
        startActivity(intent);
    }
}

