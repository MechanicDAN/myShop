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
import com.example.myshop.dialog.ListDialog;
import com.example.myshop.item.PurchasesItem;
import com.example.myshop.R;
import com.example.myshop.adapter.L_Adapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements ListDialog.ExampleDialogListener {
    private ArrayList<PurchasesItem> listArrayList;
    private RecyclerView lRecyclerView;
    private L_Adapter lAdaptor;
    private RecyclerView.LayoutManager lLayoutManager;
    private ImageButton buttonInsert;
    private int position;
    private int currentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("List");

        setContentView(R.layout.list);
        Bundle arguments = getIntent().getExtras();
        currentManager = arguments.getInt("current");

        createListArrayList();
        buildRecyclerView();

        insertButtons();
    }

    @Override
    protected void onPause() {
        try{
            JSONParser.saveList(this, listArrayList, currentManager);
        }catch (Exception ignored){}
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        listArrayList.clear();
        buildRecyclerView();
        return super.onOptionsItemSelected(item);
    }

    public void createListArrayList(){
        try{
            listArrayList = JSONParser.load(this, false);
        }catch (Exception e){
            listArrayList = new ArrayList<>();
        }
    }

    public void buildRecyclerView(){
        lRecyclerView = findViewById(R.id.listRecyclerView);
        lRecyclerView.setHasFixedSize(true);
        lLayoutManager = new LinearLayoutManager(this);
        lAdaptor = new L_Adapter(listArrayList);

        lRecyclerView.setLayoutManager(lLayoutManager);
        lRecyclerView.setAdapter(lAdaptor);

        lAdaptor.setOnItemClickListener(new L_Adapter.OnItemClickListener() {
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

    public void insertItem(String name, int color){
        int position = 0;
        if(listArrayList.size() != 0) {
            while ((position < listArrayList.size()) && (listArrayList.get(position).getColor() != color)) {
                position++;
            }
        }
        listArrayList.add(position, new PurchasesItem(name,"0","1", color));
        lAdaptor.notifyItemInserted(position);
    }

    public void removeItem(int position){
        listArrayList.remove(position);
        lAdaptor.notifyItemRemoved(position);
    }

    public void changeItem(int position,String name, int color){
        listArrayList.get(position).setTextName(name);
        listArrayList.get(position).setColor(color);
        lAdaptor.notifyItemChanged(position);
    }

    public void insertButtons() {
        buttonInsert = findViewById(R.id.listButton_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateDialog();
            }
        });
    }

    public void openCreateDialog(){
        ListDialog listDialog = new ListDialog();
        listDialog.setTitle("Create");
        listDialog.setFlag(0);
        listDialog.setColor(0);
        listDialog.show(getSupportFragmentManager(), "create dialog");
    }

    public void openEditDialog(int position){
        this.position = position;
        ListDialog listDialog = new ListDialog();
        listDialog.setTitle("Edit");
        listDialog.setFlag(1);
        listDialog.setColor(listArrayList.get(position).getColor());
        listDialog.setName(listArrayList.get(position).getTextName());
        listDialog.show(getSupportFragmentManager(), "edit dialog");
    }

    @Override
    public void applyCreateTexts(String name,  int color) {
        if(name.length() == 0) name = " ";
        insertItem(name,color);
    }

    @Override
    public void applyEditTexts(String name, int color) {
        if(name.length() == 0) name = " ";
        changeItem(this.position,name, color);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, PurchasesActivity.class);
        JSONParser.save(this,listArrayList,true);
        startActivity(intent);
    }
}

