package edu.quinnipiac.ser210.masterqueats;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    private Button cartBtn;
    private Button pickupBtn;
    private Button delivBtn;
    private String hall;
    private ArrayList<DataModel> orders;

private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        hall = getIntent().getStringExtra("hall");
        theData = new MyData(hall);
        data = new ArrayList<DataModel>();
        cartBtn = findViewById(R.id.addToCartButton);
        pickupBtn = findViewById(R.id.pickupButton);
        delivBtn = findViewById(R.id.deliveryButton);
        orders = new ArrayList<>();



    }



    public void onClick(View view) {
        //displaying data from Database
        for (int i = 0; i < theData.getLength(); i++) {
            //  data.add(new DataModel(nameList.get(i)));
            // theData.priceArray[i],
            // theData.id_[i],
            //  theData.drawableArray[i]
            //Log.v("PlaceOrderActivity", "checking text" + nameList.get(i));
            theData.populatePriceList();
            theData.populateNameList();
            data.add(new DataModel(theData.getName(i), theData.getPrice(i)));





        }

        myOnClickListener = new MyOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));


        layoutManager = new LinearLayoutManager(this);

        recyclerView.setItemAnimator(new DefaultItemAnimator());



        Log.v("PlaceOrderActivity", "data model is being created");
        adapter = new CustomAdapter(data);
        //This is the code to provide a sectioned list
        List<SimpleSectionedRecyclerAdapter.Section> sections =
                new ArrayList<SimpleSectionedRecyclerAdapter.Section>();


        //Sections
       if(hall.equals("0")){
           sections.add(new SimpleSectionedRecyclerAdapter.Section(0,"Breakfast"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(8,"Grill Food"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(18,"Snacks"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(22,"Drinks"));
        }
       else{
           sections.add(new SimpleSectionedRecyclerAdapter.Section(0,"Breakfast"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(8,"Grill Food"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(18,"Snacks"));
           sections.add(new SimpleSectionedRecyclerAdapter.Section(26,"Drinks"));
       }



        //Add your adapter to the sectionAdapter
        SimpleSectionedRecyclerAdapter.Section[] dummy = new SimpleSectionedRecyclerAdapter.Section[sections.size()];
        SimpleSectionedRecyclerAdapter mSectionedAdapter = new
                SimpleSectionedRecyclerAdapter(this,R.layout.section,R.id.section_text,adapter);
        mSectionedAdapter.setSections(sections.toArray(dummy));

        //recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(mSectionedAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void onClickPickup(View view) {
        cartBtn.setEnabled(true);




    }

    public void onClickDeliv(View view) {
        cartBtn.setEnabled(true);

    }

    public void onClickCartBtn(View view) {
        adapter.populateOrderSet();
        orders = adapter.getOrderSet();

        Log.v("OnClickCartBtn", "is running");
        if(orders!=null){
            Intent intent = new Intent(PlaceOrderActivity.this,CartActivity.class);
            intent.putExtra("orders", orders);
            intent.putExtra("id", getIntent().getStringExtra("id"));
            startActivity(intent);
        }


    }

    public void onClickSelected(View view) {
        Log.v("OnClickSelected", "is running");


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