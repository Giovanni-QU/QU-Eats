package edu.quinnipiac.ser210.masterqueats;

public class DataModel {

    String name;
    String price;
    int id_;
    int image;

    public DataModel(String name, String price) {
        this.name = name;
       this.price = price;
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
}
