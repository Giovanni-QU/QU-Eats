package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DiningHall extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_hall);
    }

    public void onClickMainCafe(View view) {
        Intent intent = new Intent(DiningHall.this,PlaceOrderActivity.class);
        intent.putExtra("hall", "0");
        startActivity(intent);
    }
    public void onBobcatDen(View view) {
        Intent intent = new Intent(DiningHall.this,PlaceOrderActivity.class);
        intent.putExtra("hall", "1");
        startActivity(intent);
    }

}
