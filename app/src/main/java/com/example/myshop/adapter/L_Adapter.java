package com.example.myshop.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshop.item.PurchasesItem;
import com.example.myshop.R;

import java.util.ArrayList;

public class L_Adapter extends RecyclerView.Adapter<L_Adapter.ViewHolder> {
    private ArrayList<PurchasesItem> ListArrayList;
    private OnItemClickListener Listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder evh = new ViewHolder(v, Listener);
        return evh;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        Listener = listener;
    }

    public L_Adapter(ArrayList<PurchasesItem> list) {
        ListArrayList = list;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PurchasesItem currentItem = ListArrayList.get(position);

        holder.TextName.setText(currentItem.getTextName());
        switch (currentItem.getColor()){
            case 0: holder.colorView.setBackgroundColor(Color.parseColor("#aaaaaa")); break;
            case 1: holder.colorView.setBackgroundColor(Color.parseColor("#ff669900")); break;
            case 2: holder.colorView.setBackgroundColor(Color.parseColor("#ffcc0000")); break;
            case 3: holder.colorView.setBackgroundColor(Color.parseColor("#ffffbb33")); break;
            case 4: holder.colorView.setBackgroundColor(Color.parseColor("#ffff8800")); break;
            case 5: holder.colorView.setBackgroundColor(Color.parseColor("#fff092b0")); break;
            case 6: holder.colorView.setBackgroundColor(Color.parseColor("#ff0099cc")); break;
            case 7: holder.colorView.setBackgroundColor(Color.parseColor("#ffaa66cc")); break;
        }
    }

    @Override
    public int getItemCount() {
        return ListArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextName;
        public ImageView DeleteImage;
        public TextView colorView;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            colorView = itemView.findViewById(R.id.ListcolorView);
            TextName = itemView.findViewById(R.id.ListTextView);
            DeleteImage = itemView.findViewById(R.id.listImage_delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            DeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
