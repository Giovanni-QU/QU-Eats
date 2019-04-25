package edu.quinnipiac.ser210.masterqueats;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class MyData {
    static ArrayList<String> breakfastNameList;
    static ArrayList<String> breakfastPriceList;
    static ArrayList<String> grillNameList;
    static ArrayList<String> grillPriceList;
    static ArrayList<String> snackNameList;
    static ArrayList<String> snackPriceList;
    static ArrayList<String> drinkNameList;
    static ArrayList<String> drinkPriceList;
    static ArrayList<String> nameList;
    static ArrayList<String> priceList;

    static Integer[] drawableArray = {
            //R.drawable/};
    };
    private String[] ids = {"01J2fZRbUYFD53glC8Jb", "2R8iI6rbTU6UtSVVndFg", "847x5JRVQfrWpOF2QVCx", "8KDqWYCx548qYWOoiaui", "8qXDYOwTcx6IxUijvX6L",
            "CWJHvd2ZWYJqgp8goINM", "GSRTMyH6fPqARl72kgNU", "HhfNZfP8epPLTJABpFOY", "IDmynGBmAmMmVfLJi4g1", "JDG3RsvKHJmtz0RElJ6v",
            "JZ8T5MjDWrrwgfLsw5wF", "Km3blhOfuxqGxPBBWkBh", "NRwhKb8F9zupmcwAUhFt", "OGfkBXYcVQRZaBAppz4u", "OIQNNdZGTS9TvreeY6ri",
            "QxjXaINFtzoz0P2g3zWh", "VWsFT29pjNN3ZIM0NYI4", "XZexNSHXBJykrXuHZoeU", "bOFLtK7r1GZRbmmncQww", "fEFTocwoOvXTCx7abwa3",
            "iekI8nzKjX1dQirZKtNO", "jX5y0GOED4xWJe59k0ds", "jgCuP7xtWKKw4ZdyQlmx", "jsL2Zodt4zwUWuW8bcLA", "k76MkpO8B6tUU6udgSlf",
            "lpHwEKSGGO3NYGfHSzak", "mLMDgcRoYV2gnm3cuwiS", "oAL4496Rv2PgmppWuaZo", "pX0clObEyzhfvitfAYpd", "poKo0hDfgiC8lRPuhnXu",
            "ufwZ6rxXunT74CxkmEee", "uizzDlSCveD6DeJMexFi", "vCTGWfHNUrVt6eCbYVc4", "vSpqBush49SwxuwQUQWG", "xwxvqu1eOxKPiI0kt4FB",
            "yXIibGReaHi2hY8FaAab", "z8pAJSyEHLG6jpqQR5Hq", "zLCsGlHqxTmXRA59Jzqt"};
    protected CollectionReference Colref;
    protected DocumentReference Docref;
    private FirebaseFirestore db;




    public MyData() {

       nameList = new ArrayList<String>();
        priceList = new ArrayList<>();
        /* db = FirebaseFirestore.getInstance();
        Docref = db.collection("menu").document("items");
        Colref = Docref.collection("allItems");

        Log.v("PlaceOrderActivity", ".get() is being called");
        //pulling menu data from DB
        */
        //calling DB and navigating to correct collection
        db = FirebaseFirestore.getInstance();
        Docref = db.collection("menu").document("items");
        //breakfast items
        breakfastNameList = new ArrayList<String>();
        breakfastPriceList = new ArrayList<>();
        Colref = Docref.collection("breakfast");

        //pulling data
        Colref
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("name"));
                                //must pull data using method
                                populateBreakfastNameList(document.getData().get("name").toString());
                                populateBreakfastPriceList(document.getData().get("price").toString());


                            }
                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });

        //grill menu
       grillNameList = new ArrayList<String>();
       grillPriceList = new ArrayList<>();
        Colref = Docref.collection("grill");


        Colref
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("name"));
                                populateGrillNameList(document.getData().get("name").toString());
                                populateGrillPriceList(document.getData().get("price").toString());


                            }
                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });

        //Snacks Menu
       snackNameList = new ArrayList<String>();
       snackPriceList = new ArrayList<>();
        Colref = Docref.collection("snacks");


        Colref
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("name"));
                                populateSnacksNameList(document.getData().get("name").toString());
                                populateSnacksPriceList(document.getData().get("price").toString());


                            }
                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });

        //Drinks menu
        drinkNameList = new ArrayList<String>();
        drinkPriceList = new ArrayList<>();
        Colref = Docref.collection("drinks");


        Colref
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("msg", document.getId() + " => " + document.getData().get("name"));
                                populateDrinkNameList(document.getData().get("name").toString());
                                populateDrinkPriceList(document.getData().get("price").toString());


                            }
                        } else {
                            Log.d("msg", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
    private void populateBreakfastPriceList(String price) {
        breakfastPriceList.add(price);
        Log.v("popPriceList", " "+ price );
    }

    private void populateBreakfastNameList(String name) {
        breakfastNameList.add(name);
    }
    private void populateGrillPriceList(String price) {
        grillPriceList.add(price);
        Log.v("popPriceList", " "+ price );
    }

    private void populateGrillNameList(String name) {
        grillNameList.add(name);
    }
    private void populateSnacksNameList(String name) {
        snackNameList.add(name);
    }
    private void populateSnacksPriceList(String price) {
        snackPriceList.add(price);
        Log.v("popPriceList", " "+ price );
    }
    private void populateDrinkNameList(String name) {
        drinkNameList.add(name);
    }
    private void populateDrinkPriceList(String price) {
        drinkPriceList.add(price);
        Log.v("popPriceList", " "+ price );
    }
    public void populatePriceList() {

        //populating full price list
        priceList.addAll(breakfastPriceList);
        priceList.addAll(grillPriceList);
        priceList.addAll(snackPriceList);
        priceList.addAll(drinkPriceList);

       // nameList.add(price);

    }

    public void populateNameList(){
        //populating full name list to send to model
        nameList.addAll(breakfastNameList);
        nameList.addAll(grillNameList);
        nameList.addAll(snackNameList);
        nameList.addAll(drinkNameList);

       // nameList.add(name);
      Log.v("popNameList", "");

    }


    public String getName(int i){
        return nameList.get(i);
    }
    public String getPrice(int i){
        return priceList.get(i);
    }
        }











