package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {
private CollectionReference usersDB;
private Boolean q;
private Boolean password;
private EditText qCardEditText;
private EditText passwordEditText;
private String qStr;
private String pwdStr;
private Boolean isNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        usersDB = db.collection("users");
        q = false;
        password = false;
        isNull = true;

        qCardEditText = findViewById(R.id.Qcard);
        qStr = "";
        passwordEditText = findViewById(R.id.passwordText);
        pwdStr = "";


    }
    public void onClickLogin(View view){
        qStr = qCardEditText.getText().toString();
        pwdStr = passwordEditText.getText().toString();
        checkNullentry();
        //need to check if q card and password are correct
        usersDB.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("qCard"));

                                //must pull data using method
                                if (document.getData().get("qCard").toString().equals(qStr)) {
                                    Log.v("onComplete", qStr);
                                    setQ(true);
                                    if(document.getData().get("password").toString().equals(pwdStr)){
                                        setPassword(true);
                                    }

                                }



                            }
                            reactToClick(q, password, isNull);



                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });


    }
    public void reactToClick(Boolean q,Boolean pwd, Boolean _isNull){
        //first make sure not Null
        if(!_isNull){
            //then check q card and password are correct before advancing
            if(q && pwd) {
                Intent intent = new Intent(Login.this, DiningHall.class);
                startActivity(intent);
            }
            else{
                Log.v("reactToClick", "Wrong info");
                Toast.makeText(getApplicationContext(),"Incorrect Q-card number or Password",Toast.LENGTH_SHORT).show();

            }



        }

    }
    private void checkNullentry() {
        if (!qStr.equals("")  && !pwdStr.equals("")) {
            isNull = false;
        }
        else{
            isNull = true;
            Toast.makeText(getApplicationContext(),"Invalid. Not all fields completed",Toast.LENGTH_SHORT).show();
        }
    }
    public void setQ(Boolean b){
        q = b;
    }
    public void setPassword(Boolean b){
        password = b;
    }

    public void onClickCreateAcct(View view){
        Intent intent = new Intent(Login.this,CreateAccount.class);
        startActivity(intent);
    }
}
