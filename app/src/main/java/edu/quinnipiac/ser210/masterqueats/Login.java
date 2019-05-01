package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void onClickLogin(View view){
        Intent intent = new Intent(Login.this,DiningHall.class);
        startActivity(intent);
    }
    public void onClickCreateAcct(View view){
        Intent intent = new Intent(Login.this,CreateAccount.class);
        startActivity(intent);
    }
}
