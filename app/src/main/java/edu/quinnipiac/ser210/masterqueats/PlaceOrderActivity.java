package edu.quinnipiac.ser210.masterqueats;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderActivity extends AppCompatActivity {

    private static CustomAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    private MyData theData;
    protected CollectionReference Colref;
    protected DocumentReference Docref;
    private FirebaseFirestore db;
    protected ArrayList<DataModel> order;
private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        theData = new MyData();
        order = new ArrayList<DataModel>();
        data = new ArrayList<DataModel>();
    }



    public void onClick(View view) {
        //displaying data from Database
        for (int i = 0; i < 38; i++) {
            //  data.add(new DataModel(nameList.get(i)));
            // theData.priceArray[i],
            // theData.id_[i],
            //  theData.drawableArray[i]
            //Log.v("PlaceOrderActivity", "checking text" + nameList.get(i));
            data.add(new DataModel(theData.getName(i), theData.getPrice(i)));





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
    public void onClickAdd(View view){
        int position = adapter.getPosition(view);
        order.add(data.get(position));
        //to see if this even works at all
        Intent i = new Intent(this,OrderCardsActivity.class);
        i.putExtra("order",order);
        startActivity(i);
    }


           /* Colref.get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            if (documentSnapshots.isEmpty()) {
                                Log.d("msg", "onSuccess: LIST EMPTY");
                                return;
                            } else {
                                // Convert the whole Query Snapshot to a list
                                // of objects directly! No need to fetch each
                                // document.
                                List<String> names = documentSnapshots.toObjects(String.class);

                                // Add all to your list
                                nameList.addAll(names);
                                Log.d("nameList populated", "onSuccess: " + nameList);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                }
            });
            */


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