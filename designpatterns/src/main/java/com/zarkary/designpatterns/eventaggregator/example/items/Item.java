package com.zarkary.designpatterns.eventaggregator.example.items;

public class Item {

    private final int itemNumber;

    public Item(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }
}
