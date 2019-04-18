package edu.quinnipiac.ser210.masterqueats;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class PlaceOrderActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private MyData theData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        theData = new MyData();
        data = new ArrayList<DataModel>();
        int size = MyData.grillNameArray.length + MyData.drinksNameArray.length + MyData.snacksNameArray.length+ MyData.breakfastNameArray.length;


            for (int i = 0; i < 38; i++) {
                data.add(new DataModel(theData.getName(i)));
                // theData.priceArray[i],
                // theData.id_[i],
                //  theData.drawableArray[i]
//failing because data is not being populated because theData is empty


            Log.v("PlaceOrderActivity", "checking text" + data.get(1).getName());
        }

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        layoutManager = new LinearLayoutManager(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



        Log.v("PlaceOrderActivity", "data model is being created");
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }


        @Override
        public void onClick(View v) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        return true;
    }


}