package com.example.myshop.item;

public class PurchasesItem {
    private String textName;
    private String textCost;
    private String quantity;
    private int color; // 0 - white(basic), 1 - green, 2 - red, 3 - yellow, 4 - orange, 5 - violet , 6 - blue, 7- pink

    public PurchasesItem(String textName, String textCost, String quantity, int color) {
        this.textName = textName;
        this.textCost = textCost;
        this.quantity = quantity;
        this.color = color;
    }

    public void setTextName(String text){
        textName = text;
    }

    public void setTextCost(String text){
        textCost = text;
    }

    public int getColor(){return color;}

    public void setColor(int color){this.color = color;}

    public void setQuantity(String quantity){ this.quantity = quantity;}

    public String getQuantity() { return quantity; }

    public String getTextName() {
        return textName;
    }

    public String getTextCost() {
        return textCost;
    }
}
