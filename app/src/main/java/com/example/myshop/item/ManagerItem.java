package com.example.myshop.item;

import java.util.ArrayList;

public class ManagerItem {
    private String name;
    private ArrayList<PurchasesItem> list;

    public ManagerItem(String name, ArrayList<PurchasesItem> list){
        this.name = name;
        this.list = list;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PurchasesItem> getList() {
        return list;
    }
    public void setList(ArrayList<PurchasesItem> list) {
        this.list = list;
    }
}
