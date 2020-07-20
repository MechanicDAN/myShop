package com.example.myshop;

import android.content.Context;


import com.example.myshop.item.ManagerItem;
import com.example.myshop.item.PurchasesItem;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class JSONParser {
    private static final String P_FILE_NAME = "data.txt";
    private static final String L_FILE_NAME = "list.txt";
    private static final String LM_FILE_NAME = "manager.txt";


    public static void save(Context context, ArrayList<PurchasesItem> list, boolean flag) {
        try {
            Gson gson = new Gson();
            String jStr = gson.toJson(list);
            String str;
            if (flag) str = P_FILE_NAME;
            else str = L_FILE_NAME;

            FileOutputStream fos = context.openFileOutput(str, MODE_PRIVATE);
            fos.write(jStr.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<PurchasesItem> load(Context context, boolean flag) {
        String str;
        if (flag) str = P_FILE_NAME;
        else str = L_FILE_NAME;
        ArrayList<PurchasesItem> purchasesItems = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(str)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
           purchasesItems = purchasesItemsFromJson(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return purchasesItems;
    }

    public static void saveManager(Context context, ArrayList<ManagerItem> list) {
        try {
            Gson gson = new Gson();
            String jStr = gson.toJson(list);

            FileOutputStream fos = context.openFileOutput(LM_FILE_NAME, MODE_PRIVATE);
            fos.write(jStr.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ManagerItem> loadManager(Context context){
        ArrayList<ManagerItem> managerItems = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    context.openFileInput(LM_FILE_NAME)));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                ManagerItem item = new ManagerItem("name", new ArrayList<PurchasesItem>());
                item.setName(object.getString("name"));
                item.setList(purchasesItemsFromJson(object.getString("list")));
                managerItems.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return managerItems;
    }

    public static void saveList(Context context,ArrayList<PurchasesItem> list ,int currentManager){
        ArrayList<ManagerItem> managerItems = loadManager(context);
        managerItems.get(currentManager).setList(list);
        saveManager(context,managerItems);
    }

    public static ArrayList<PurchasesItem> purchasesItemsFromJson(String jsonString) throws JSONException {
        ArrayList<PurchasesItem> purchasesItems = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            PurchasesItem item = new PurchasesItem("name", "0", "1", 0);
            item.setTextName(object.getString("textName"));
            item.setTextCost(object.getString("textCost"));
            item.setQuantity(object.getString("quantity"));
            item.setColor(object.getInt("color"));
            purchasesItems.add(item);
        }
        return purchasesItems;
    }
}
