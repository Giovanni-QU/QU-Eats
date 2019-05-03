package edu.quinnipiac.ser210.masterqueats;
import android.content.ClipData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
//Created by Giovanni Greco
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> orderSet;

    public void populateOrderSet() {
        for(int i = 0;i<dataSet.size();i++){
            if(dataSet.get(i).getSelected()){
                orderSet.add(dataSet.get(i));
            }
        }
    }


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
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);





        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        Log.v("CustomAdapter", "constructor running");
        dataSet = data;
        orderSet = new ArrayList<>();
    }

    public ArrayList<DataModel> sendUpdatedSelection(){
        return orderSet;
    }
    public ArrayList<DataModel> getOrderSet(){
        return orderSet;
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
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        //this is where we can modify what is being displayed per card
       TextView textViewPrice = holder.textViewPrice;
       textViewPrice.setText("$"+dataSet.get(listPosition).getPrice());
       Log.v("onBindViewHolder", " Price : "+ textViewPrice.getText());
       // ImageView imageView = holder.imageViewIcon;
        TextView nameLbl = holder.nameLbl;
        nameLbl.setText(dataSet.get(listPosition).getName());

        CheckBox checkBox = holder.checkBox;
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( dataSet.get(listPosition).getSelected() == false){
                    dataSet.get(listPosition).setSelected(true);
                }
                else{
                    dataSet.get(listPosition).setSelected(false);
                }

            }
        });

       // textViewPrice.setText(" " +dataSet.get(listPosition).getPrice());
       // imageView.setImageResource(dataSet.get(listPosition).getImage());
        Log.v("CustomAdapter", "onBindViewHolder "+ nameLbl.getText());
    }

    @Override
    public int getItemCount() {
        Log.v("CustomAdapter", "getItemCount" + dataSet.size());
        return dataSet.size();
    }
}