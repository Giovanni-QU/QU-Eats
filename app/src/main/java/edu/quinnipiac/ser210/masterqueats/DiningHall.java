package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//Created by Giovanni Greco
public class DiningHall extends AppCompatActivity {
String userID;
private Boolean currOrder;
private Button currBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_hall);
        userID = getIntent().getStringExtra("id");
        currOrder = getIntent().getBooleanExtra("curr", false);
        currBtn = (Button) findViewById(R.id.currOrder);
        if(currOrder){
            currBtn.setEnabled(true);
        }


    }

    public void onClickMainCafe(View view) {
        Intent intent = new Intent(DiningHall.this,PlaceOrderActivity.class);
        intent.putExtra("hall", "0");
        intent.putExtra("id", userID);
        startActivity(intent);
    }
    public void onBobcatDen(View view) {
        Intent intent = new Intent(DiningHall.this,PlaceOrderActivity.class);
        intent.putExtra("hall", "1");
        intent.putExtra("id", userID);
        startActivity(intent);
    }

    public void onClickLogout(View view) {
        Intent intent = new Intent(DiningHall.this,Login.class);
        startActivity(intent);
    }

    public void onClickcurrOrder(View view) {
        Intent intent = new Intent(DiningHall.this,CurrentOrderActivity.class);
        startActivity(intent);
    }
}
