package edu.quinnipiac.ser210.masterqueats;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
private TextView testText;
private ArrayList<DataModel> orders;
private String[] orderFullStringArray;
private TextView total;
private double totalPrice;
    private FirebaseFirestore db;
    private String userId;
    private CollectionReference ordersDB;
    private String userName;
    private CollectionReference usersDB;
    private  Map<String, Object> orderObject;
    private  String[] itemNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        orders = (ArrayList<DataModel>) getIntent().getSerializableExtra("orders");
        orderFullStringArray = new String[orders.size()];
        for(int i = 0;i<orders.size();i++){
            orderFullStringArray[i] = orders.get(i).getName() + " .....  $" + orders.get(i).getPrice();
        }
        db = FirebaseFirestore.getInstance();
        ordersDB = db.collection("orders");
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, orderFullStringArray);

        ListView listView = (ListView) findViewById(R.id.ordersList);
        listView.setAdapter(adapter);
        total = (TextView) findViewById(R.id.total);
        totalPrice = calcTotal();
        total.setText("Total : $"+ totalPrice);
        userId = getIntent().getStringExtra("id");



    }

    private double calcTotal() {
        double theTotal = 0;
        for(int i = 0;i<orders.size();i++){
            Double price = Double.parseDouble(orders.get(i).getPrice());
            theTotal +=price;
        }
        theTotal += 2;
        //adding service charge
        return theTotal;
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(CartActivity.this,PlaceOrderActivity.class);
        startActivity(intent);
    }

    public void onClickPlace(View view) {

     orderObject = new HashMap<>();
     itemNames = new String[orders.size()];

        //populates an array of the name of all items ordered to be sent to admin
        for(int i=0;i<itemNames.length;i++){
            itemNames[i] = orders.get(i).getName();

        }

        //gets users name to be sent to admin
        findUsersName();
    }
public void reactToClick(){
    orderObject.put("name", userName);
    orderObject.put("Item", Arrays.asList(itemNames));
    ordersDB.add(orderObject)
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
    Intent intent = new Intent(CartActivity.this,CurrentOrderActivity.class);
    startActivity(intent);
}
    private void findUsersName() {
        db = FirebaseFirestore.getInstance();
        usersDB = db.collection("users");
        usersDB.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("qCard"));

                                //must pull data using method
                                if (document.getData().get("qCard").toString().equals(userId)) {
                                    Log.v("findUserName", document.getData().get("name").toString());
                                   setUserName(document.getData().get("name").toString());
                                    reactToClick();

                                }


                            }




                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    private void setUserName(String name) {
        Log.v("setUserName", name);
        userName = name;
    }
}
