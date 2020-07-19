package com.example.myshop.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myshop.JSONParser;
import com.example.myshop.dialog.PurchasesDialog;
import com.example.myshop.item.PurchasesItem;
import com.example.myshop.R;
import com.example.myshop.adapter.P_Adapter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;

public class PurchasesActivity extends AppCompatActivity implements PurchasesDialog.ExampleDialogListener {
    private ArrayList<PurchasesItem> purchasesList;
    private RecyclerView pRecyclerView;
    private P_Adapter pAdaptor;
    private RecyclerView.LayoutManager pLayoutManager;
    private ImageButton buttonInsert;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchases);

        getSupportActionBar().setTitle("Корзина");

        createPurchasesList();
        buildRecyclerView();
        setCurrentSum();

        insertButtons();
    }
    @Override
    protected void onPause() {
        try{
            JSONParser.save(this, purchasesList,true);
        }catch (Exception ignored){}
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public void insertItem(String name, String cost, String quantity, int color){
        purchasesList.add(new PurchasesItem(name,cost,quantity, color));
        pAdaptor.notifyItemInserted(purchasesList.size());
        setCurrentSum();
    }

    public void removeItem(int position){
        purchasesList.remove(position);
        pAdaptor.notifyItemRemoved(position);
        setCurrentSum();
    }

    public void changeItem(int position,String name, String cost, String quantity, int color){
        purchasesList.get(position).setTextName(name);
        purchasesList.get(position).setTextCost(cost);
        purchasesList.get(position).setQuantity(quantity);
        purchasesList.get(position).setColor(color);
        pAdaptor.notifyItemChanged(position);
        setCurrentSum();
    }


    public void createPurchasesList(){
        try{
            purchasesList = JSONParser.load(this,true);
        }catch (Exception e){
            purchasesList = new ArrayList<>();
        }
    }

    public void buildRecyclerView(){
        pRecyclerView = findViewById(R.id.purchasesRecyclerView);
        pRecyclerView.setHasFixedSize(true);
        pLayoutManager = new LinearLayoutManager(this);
        pAdaptor = new P_Adapter(purchasesList);

        pRecyclerView.setLayoutManager(pLayoutManager);
        pRecyclerView.setAdapter(pAdaptor);

        pAdaptor.setOnItemClickListener(new P_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               openEditDialog(position);
            }

            @Override
            public void onDeleteClick(int position){
                removeItem(position);
            }
        });
    }

    public void insertButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDialog();
            }
        });
    }

    public void openCreateDialog(){
        PurchasesDialog purchasesDialog = new PurchasesDialog();
        purchasesDialog.setTitle("Create");
        purchasesDialog.setFlag(0);
        purchasesDialog.show(getSupportFragmentManager(), "create dialog");
    }

    public void openEditDialog(int position){
        this.position = position;
        PurchasesDialog purchasesDialog = new PurchasesDialog();
        purchasesDialog.setTitle("Edit");
        purchasesDialog.setFlag(1);
        purchasesDialog.setName(purchasesList.get(position).getTextName());
        purchasesDialog.setCost(purchasesList.get(position).getTextCost());
        purchasesDialog.setQuantity(purchasesList.get(position).getQuantity());
        purchasesDialog.show(getSupportFragmentManager(), "edit dialog");
    }

    @Override
    public void applyCreateTexts(String name, String cost, String quantity, int color){
        if(name.length() == 0) name = " ";
        if(cost.length() == 0) cost = "0";
        if(quantity.length() == 0) quantity = "1";
        BigDecimal bd = new BigDecimal(cost);
        insertItem(name, bd.setScale(2, RoundingMode.HALF_UP).toString(), quantity, color);
    }

    @Override
    public void applyEditTexts(String name, String cost, String quantity, int color){
        if(name.length() == 0) name = " ";
        if(cost.length() == 0) cost = "0";
        if(quantity.length() == 0) quantity = "1";
        BigDecimal bd = new BigDecimal(cost);
        changeItem(this.position, name, bd.setScale(2, RoundingMode.HALF_UP).toString(), quantity, color);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      purchasesList.clear();
      buildRecyclerView();
        return super.onOptionsItemSelected(item);
    }

    public void setCurrentSum(){
        try {
        TextView totalTextView = findViewById(R.id.totalTextView);
        double total = 0;
        for(PurchasesItem item: purchasesList)
            total += Double.parseDouble(item.getTextCost()) * Double.parseDouble(item.getQuantity());
        BigDecimal bd = new BigDecimal(Double.toString(total));
        totalTextView.setText(bd.setScale(2, RoundingMode.HALF_UP).toString());
        }catch (Exception e ){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

}
