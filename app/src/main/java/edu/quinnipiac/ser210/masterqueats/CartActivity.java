package edu.quinnipiac.ser210.masterqueats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
private TextView testText;
private ArrayList<DataModel> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        testText = (TextView) findViewById(R.id.testText);
        orders = (ArrayList<DataModel>) getIntent().getSerializableExtra("orders");
        testText.setText(orders.get(0).getName());

    }
}
