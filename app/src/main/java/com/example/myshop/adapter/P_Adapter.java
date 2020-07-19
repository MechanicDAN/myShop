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

public class P_Adapter extends RecyclerView.Adapter<P_Adapter.ViewHolder> {
    private ArrayList<PurchasesItem> purchasesList;
    private OnItemClickListener Listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        Listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchases_item,parent,false);
        ViewHolder evh = new ViewHolder(v, Listener);
        return evh;
    }

    public P_Adapter(ArrayList<PurchasesItem> list) {
        purchasesList = list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PurchasesItem currentItem = purchasesList.get(position);

        holder.TextName.setText(currentItem.getTextName());
        holder.TextCost.setText(currentItem.getTextCost());
        holder.TextQuantity.setText(currentItem.getQuantity());
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
        return purchasesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextName;
        public TextView TextCost;
        public TextView TextQuantity;
        public ImageView DeleteImage;
        public TextView colorView;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            TextName = itemView.findViewById(R.id.textView);
            TextCost = itemView.findViewById(R.id.textView2);
            TextQuantity = itemView.findViewById(R.id.textQuantity);
            DeleteImage = itemView.findViewById(R.id.image_delete);
            colorView = itemView.findViewById(R.id.colorView);

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
