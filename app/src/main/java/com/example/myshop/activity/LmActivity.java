package com.example.myshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.myshop.JSONParser;
import com.example.myshop.adapter.Lm_Adapter;

import com.example.myshop.dialog.ManagerDialog;
import com.example.myshop.item.ManagerItem;
import com.example.myshop.item.PurchasesItem;
import com.example.myshop.R;

import java.util.ArrayList;

public class LmActivity extends AppCompatActivity implements ManagerDialog.ExampleDialogListener {
    private ArrayList<ManagerItem> lmList;
    private RecyclerView lmRecyclerView;
    private Lm_Adapter lmAdaptor;
    private RecyclerView.LayoutManager lmLayoutManager;
    private ImageButton buttonInsert;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_manager);

        getSupportActionBar().setTitle("Manager");

        createLmList();
        buildRecyclerView();

        insertButtons();
    }

    @Override
    protected void onPause() {
        try{
            JSONParser.saveManager(this,lmList);
        }catch (Exception ignored){}
        super.onPause();
        super.onStop();
    }
    @Override
    protected void onRestart() {
        createLmList();
        buildRecyclerView();
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        lmList.clear();
        buildRecyclerView();
        return super.onOptionsItemSelected(item);
    }

    public void createLmList(){
        try{
            lmList = JSONParser.loadManager(this);
        }
        catch (Exception e){
                lmList = new ArrayList<>();
            }
        }

    public void buildRecyclerView(){
        lmRecyclerView = findViewById(R.id.lmRecyclerView);
        lmRecyclerView.setHasFixedSize(true);
        lmLayoutManager = new LinearLayoutManager(this);
        lmAdaptor = new Lm_Adapter(lmList);

        lmRecyclerView.setLayoutManager(lmLayoutManager);
        lmRecyclerView.setAdapter(lmAdaptor);

        lmAdaptor.setOnItemClickListener(new Lm_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openList(position);
            }

            @Override
            public void onEditClick(int position) {
                openEditDialog(position);
            }
            @Override
            public void onDeleteClick(int position){
                removeItem(position);
            }
        });

    }

    public void insertItem(String name){
        lmList.add(new ManagerItem(name,new ArrayList<PurchasesItem>()));
        lmAdaptor.notifyItemInserted(lmList.size());
    }

    public void removeItem(int position){
        lmList.remove(position);
        lmAdaptor.notifyItemRemoved(position);
    }

    public void changeItem(int position,String name){
        lmList.get(position).setName(name);
        lmAdaptor.notifyItemChanged(position);
    }

    public void insertButtons() {
        buttonInsert = findViewById(R.id.lmButton_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDialog();
            }
        });
    }

    public void openCreateDialog(){
        ManagerDialog listDialog = new ManagerDialog();
        listDialog.setTitle("Create");
        listDialog.setFlag(0);
        listDialog.show(getSupportFragmentManager(), "create dialog");
    }

    public void openEditDialog(int position){
        this.position = position;
        ManagerDialog listDialog = new ManagerDialog();
        listDialog.setTitle("Edit");
        listDialog.setFlag(1);
        listDialog.setName(lmList.get(position).getName());
        listDialog.show(getSupportFragmentManager(), "edit dialog");
    }


    @Override
    public void applyCreateTexts(String name) {
        if(name.length() == 0) name = " ";
        insertItem(name);
    }

    @Override
    public void applyEditTexts(String name) {
        if(name.length() == 0) name = " ";
        changeItem(this.position,name);
    }

    public void openList(int position){
        JSONParser.save(this,lmList.get(position).getList(),false);
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("current", position);
        startActivity(intent);
    }
}

