package edu.quinnipiac.ser210.masterqueats;

import java.io.Serializable;
//Created by Giovanni Greco
public class DataModel implements Serializable {

    String name;
    String price;
    int id_;
    int image;
    Boolean selected;

    public DataModel(String name, String price) {
        this.name = name;
       this.price = price;
       selected = false;
       // this.id_ = id_;
       // this.image=image;

    }

    public String getName() {
        return name;
    }

    public String getPrice(){
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }

    public Boolean getSelected(){
        return selected;
    }
    public void setSelected(Boolean b){
        selected = b;
    }
}
