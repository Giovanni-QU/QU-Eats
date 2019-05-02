package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CurrentOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
    }

    public void onClickMain(View view) {
        Intent intent = new Intent(CurrentOrderActivity.this,DiningHall.class);
        intent.putExtra("curr", true);
        startActivity(intent);
    }
}
