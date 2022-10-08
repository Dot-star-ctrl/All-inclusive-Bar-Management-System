package system.barManagement.service.model;

import java.util.Objects;

public class Item {
    private int id;
    private String itemName;
    private double price;

    public Item(String itemName, double price){
        this.itemName = itemName;
        this.price = price;
    }

    public Item() {
    }

    public int getItemId(){return id;}

    public void setItemId(int id){this.id = id;}

    public String getItemName(){return itemName;}

    public void setItemName(String itemName){this.itemName = itemName;}

    public double getPrice(){return price;}

    public void setPrice(double price){this.price = price;}
}
