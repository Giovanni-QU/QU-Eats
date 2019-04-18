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
    static String[] nameArray;
    static String[] breakfastNameArray = {};
    static String[] grillNameArray = {};
    static String[] snacksNameArray = {};
    static String[] drinksNameArray = {};
    static ArrayList<String> nameList = new ArrayList<String>();
    static double[] priceArray = {};
    public String tempname;
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
    static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
private populateNames popNames;
    public MyData() {

popNames = new populateNames();
nameList = popNames.sendNames();


    }
    private class populateNames implements OnCompleteListener<QuerySnapshot> {
        private ArrayList<String> names;
        protected CollectionReference Colref;
        protected DocumentReference Docref;
        private FirebaseFirestore db;

        public populateNames() {
            Log.v("pop constructor ", " is running");
            names = new ArrayList<String>();
            db = FirebaseFirestore.getInstance();
            Docref = db.collection("menu").document("items");
            Colref = Docref.collection("allItems");

            //accessing document items
            Colref.get().addOnCompleteListener(this);
            //current: .get() is running, so onComplete should be triggered, it is not running
            //PlaceOrderActivity creates instance of this class and populates the dataModel using this data.
            // Data Model is then applied to a Custom Adapter and displayed in UI
        }
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            Log.v("OnComplete ", " is running");
            if (task.isSuccessful()) {
                Log.v("onComplete ", " running");
                Log.d("onComplete", "isSuccessful");

                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.v("individualDocs", "DocumentSnapshot data: " + document.getData().get("name"));


                    addToNames(document.getData().get("name").toString());

                }
            } else {
                Log.d("MSG", "Error getting documents: ", task.getException());
            }
        }
        public void addToNames(String name) {
            Log.v("addToNames", " " + name);
            if (names.isEmpty()) {
                names.set(0, name);
            } else {
                names.add(name);
            }
        }

        public ArrayList<String> sendNames() {
            Log.v("sendNames", "msg");
            return names;
        }


        /*public void setNameArray() {
            Log.v("set nameArray ", " is running");
            //reference to document items inside menu
            //  DocumentReference ref = db.collection("menu").document("items");
            Docref = db.collection("menu").document("items");
            Colref = Docref.collection("allItems");


            //accessing document items
            Colref.get().addOnCompleteListener(this);
            Log.v("set nameArray ", " " + this.sendNames());
            //current: .get() is running, so onComplete should be triggered, it is not running
*/

          /*  Log.v("for loop ", "is running ");
            ref.document(ids[i]).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            tempname = document.getData().get("name").toString();
                            nameList.add(tempname);
                            //current: nameList isn't updated in parent class, list is null
                            Log.d("individualDocs", "DocumentSnapshot data: " + document.getData().get("name"));
                            Log.v("nameList ", " " + nameList.get(0));
                        } else {
                            Log.d("item doc level", "No such document");
                        }
                    } else {
                        Log.d("tag", "get failed with ", task.getException());
                    }
                }

            });


        }
*/


        }





      /*  @Override
        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            Log.d("onComplete", "isSuccessful");

            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Log.d("individualDocs", "DocumentSnapshot data: " + document.getData().get("name"));
                nameList.add(document.getData().get("name").toString());
            }
        }
        */



    public String getName(int i) {
        return nameList.get(i);
    }
    }






        //populates breakfastNameArray


        //populates breakfastNameArray
      /*  public void setBreakNameArray() {

            //reference to document items inside menu
            DocumentReference ref = db.collection("menu").document("items");
            //accessing document items
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("msg", "DocumentSnapshot data: " + document.getData());
                            //accessing collection breakfast
                            CollectionReference breakfast = document.getReference().collection("breakfast");


                            //loop to populate
                            for (int i = 0; i < 8; i++) {


                                breakfast.document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Log.d("msg", "DocumentSnapshot data: " + document.getData());
                                                tempname = document.get("name").toString();
                                            } else {
                                                Log.d("msg", "No such document");
                                            }
                                        } else {
                                            Log.d("tag", "get failed with ", task.getException());
                                        }
                                    }
                                });
                                breakfastNameArray[i] = tempname;
                            }


                        } else {
                            Log.d("msg", "No such document");
                        }
                    } else {
                        Log.d("msg", "get failed with ", task.getException());
                    }
                }
            });
        }


        //populates breakfastNameArray
        public void setGrillNameArray() {


            //reference to document items inside menu
            DocumentReference ref = db.collection("menu").document("items");
            //accessing document items
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("msg", "DocumentSnapshot data: " + document.getData());
                            //accessing collection breakfast
                            CollectionReference breakfast = document.getReference().collection("grill");


                            //loop to populate
                            for (int i = 0; i < 10; i++) {


                                breakfast.document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Log.d("msg", "DocumentSnapshot data: " + document.getData());
                                                tempname = document.get("name").toString();
                                            } else {
                                                Log.d("msg", "No such document");
                                            }
                                        } else {
                                            Log.d("tag", "get failed with ", task.getException());
                                        }
                                    }
                                });
                                grillNameArray[i] = tempname;
                            }


                        } else {
                            Log.d("msg", "No such document");
                        }
                    } else {
                        Log.d("msg", "get failed with ", task.getException());
                    }
                }
            });
        }


        //populates breakfastNameArray
        public void setSnacksNameArray() {


            //reference to document items inside menu
            DocumentReference ref = db.collection("menu").document("items");
            //accessing document items
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("msg", "DocumentSnapshot data: " + document.getData());
                            //accessing collection breakfast
                            CollectionReference breakfast = document.getReference().collection("snacks");


                            //loop to populate
                            for (int i = 0; i < 10; i++) {


                                breakfast.document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Log.d("msg", "DocumentSnapshot data: " + document.getData());
                                                tempname = document.get("name").toString();
                                            } else {
                                                Log.d("msg", "No such document");
                                            }
                                        } else {
                                            Log.d("tag", "get failed with ", task.getException());
                                        }
                                    }
                                });
                                snacksNameArray[i] = tempname;
                            }


                        } else {
                            Log.d("msg", "No such document");
                        }
                    } else {
                        Log.d("msg", "get failed with ", task.getException());
                    }
                }
            });
        }


        //populates breakfastNameArray
        public void setDrinksNameArray() {


            //reference to document items inside menu
            DocumentReference ref = db.collection("menu").document("items");
            //accessing document items
            ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("msg", "DocumentSnapshot data: " + document.getData());
                            //accessing collection breakfast
                            CollectionReference breakfast = document.getReference().collection("drinks");


                            //loop to populate
                            for (int i = 0; i < 10; i++) {


                                breakfast.document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                Log.d("msg", "DocumentSnapshot data: " + document.getData());
                                                tempname = document.get("name").toString();
                                            } else {
                                                Log.d("msg", "No such document");
                                            }
                                        } else {
                                            Log.d("tag", "get failed with ", task.getException());
                                        }
                                    }
                                });
                                drinksNameArray[i] = tempname;
                            }


                        } else {
                            Log.d("msg", "No such document");
                        }
                    } else {
                        Log.d("msg", "get failed with ", task.getException());
                    }
                }
            });
        }


    */








