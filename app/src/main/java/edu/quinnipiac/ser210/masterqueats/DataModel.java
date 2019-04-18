package edu.quinnipiac.ser210.masterqueats;

public class DataModel {

    String name;
    double price;
    int id_;
    int image;

    public DataModel(String name) {
        this.name = name;
      /*  this.price = price;
        this.id_ = id_;
        this.image=image;
        */
    }

    public String getName() {
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
