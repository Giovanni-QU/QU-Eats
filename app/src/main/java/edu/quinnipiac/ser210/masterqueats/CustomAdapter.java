package edu.quinnipiac.ser210.masterqueats;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView textViewPrice;
        ImageView imageViewIcon;
        CheckBox checkBox;
        TextView nameLbl;

        public MyViewHolder(View itemView) {
            super(itemView);
           // this.textViewPrice = (TextView) itemView.findViewById(R.id.textViewVersion);
           // this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView);

            this.textViewPrice = (TextView) itemView.findViewById(R.id.priceLbl);
            this.nameLbl = (TextView) itemView.findViewById(R.id.name);





        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        Log.v("CustomAdapter", "constructor running");
        dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);
        Log.v("CustomAdapter", "onCreateViewHolder running");
        view.setOnClickListener(PlaceOrderActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int listPosition) {

        Log.v("CustomAdapter", "onBindViewHolder " + dataSet.get(0).getName());
       TextView textViewPrice = holder.textViewPrice;
       textViewPrice.setText("$"+dataSet.get(listPosition).getPrice());
       // ImageView imageView = holder.imageViewIcon;
        TextView nameLbl = holder.nameLbl;
        nameLbl.setText(dataSet.get(listPosition).getName());
       // textViewPrice.setText(" " +dataSet.get(listPosition).getPrice());
       // imageView.setImageResource(dataSet.get(listPosition).getImage());
        Log.v("CustomAdapter", "onBindViewHolder "+ nameLbl.getText());
    }

    @Override
    public int getItemCount() {
        Log.v("CustomAdapter", "getItemCount" + dataSet.size());
        return 38;
    }
}