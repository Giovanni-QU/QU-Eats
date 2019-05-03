package edu.quinnipiac.ser210.masterqueats;
//Created by Giovanni Greco
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CreateAccount extends AppCompatActivity {
private EditText name;
private EditText password;
    private EditText qCard;
    private EditText roomNum;
    private CollectionReference usersDB;
    private boolean isRepeat;
    private String qCardNum;
    private boolean isNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        //instantiating editTexts
        name = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        qCard = findViewById(R.id.qcard);
        roomNum = findViewById(R.id.RoomNum);
        //instantiating db references
        FirebaseFirestore db = FirebaseFirestore.getInstance();
            usersDB = db.collection("users");
        //instance vars needed
            isRepeat = false; //defualt
            qCardNum = "";
            isNull = true; //default

    }
    public void onClickCreate(View view){
        //every time Create button is clicked
        Log.v("onClickCreate", "is running");
        isRepeat = false;
        qCardNum = qCard.getText().toString();

        checkNullentry();
       //checks if all data is not null and if user is repeated, sets isRepeat and isNull to respective values
        checkRepeatUser();
        //reacts to the click using the updated instance data, needed to be seperate method bc of firebase









    }

    private void checkNullentry() {
        if (!qCardNum.equals("")  && !password.getText().toString().equals("") && !name.getText().toString().equals("") && !roomNum.getText().toString().equals("")) {
            isNull = false;
        }
        else{
            isNull = true;
        }
    }

    //current : problem is react to click, which contains all the toasts if stuff is invalid and adds user after stuff is checked, is only being called when-
    //... setisRepeat is called, because it needs the updated data, however also needs to be called every time is clicked to check for null entries
    private void reactToClick(Boolean _isNull, Boolean _isRepeat) {
        //reacts to click using the updated instance data, needed to be seperate method bc of firebase
        Map<String, Object> user = new HashMap<>();

        Log.v("reactClick", "is running");
        if(!_isNull){
            Log.v("reactClick", "isNull");
            if(!isRepeat){
                user.put("qCard", qCard.getText().toString());
                user.put("password", password.getText().toString());
                user.put("name", name.getText().toString());
                user.put("room", roomNum.getText().toString());
                Log.v("isNotRepeat", "Code is running");

                usersDB.add(user)
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

                Intent intent = new Intent(CreateAccount.this,Login.class);
                 startActivity(intent);
            }  else{
                Log.v("reactClick", "repeatToast");
                Toast.makeText(getApplicationContext(),"User with that Q Card number Already exists.",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Log.v("reactClick", "empty data toast");
            Toast.makeText(getApplicationContext(),"Invalid. Not all Fields Completed.",Toast.LENGTH_SHORT).show();

        }
    }

    public void checkRepeatUser() {
        //checks if all data is not null and if user is repeated, sets isRepeat and isNull to respective values


            usersDB.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                isRepeat = false;
                                //default
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Log.d("msg", document.getId() + " => " + document.getData().get("qCard"));

                                    //must pull data using method
                                    if (document.getData().get("qCard").toString().equals(qCardNum)) {
                                        Log.v("onComplete", qCardNum);
                                        setisRepeat(true);


                                    }


                                }
                                reactToClick(isNull, isRepeat);



                            } else {
                                Log.d("msg", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }






    public void setisRepeat(boolean b) {
        Log.v("setIsRepeat", "isRunning");
        isRepeat = b;
        reactToClick(isNull,isRepeat);

    }
}
