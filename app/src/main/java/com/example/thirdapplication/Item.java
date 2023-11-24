package com.example.thirdapplication;

// Item.java
// Item.java
public class Item {
    private String itemName;
    private String itemDescription;
    private int itemImageResourceId;
    private String itemPrice;
    private int quantity;
    private boolean isIconAdded;

    public Item(String itemName, String itemDescription, int itemImageResourceId , String itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImageResourceId = itemImageResourceId;
        this.itemPrice = itemPrice;
        this.quantity = 0;
        this.isIconAdded = false;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemImageResourceId() {
        return itemImageResourceId;
    }

    public String getItemPrice() { return itemPrice; }

    public void setIconAdded(boolean iconAdded) {
        isIconAdded = iconAdded;
    }
    public boolean isIconAdded() {
        return isIconAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
