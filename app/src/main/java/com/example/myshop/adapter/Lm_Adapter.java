package com.example.myshop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myshop.item.ManagerItem;
import com.example.myshop.item.PurchasesItem;
import com.example.myshop.R;

import java.util.ArrayList;

public class Lm_Adapter extends RecyclerView.Adapter<Lm_Adapter.ViewHolder> {
    private ArrayList<ManagerItem> lmList;
    private OnItemClickListener listener;

    @Override
    public Lm_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lm_item,parent,false);
        ViewHolder evh = new Lm_Adapter.ViewHolder(v, listener);
        return evh;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public Lm_Adapter(ArrayList<ManagerItem> list) {
        lmList = list;
    }


    @Override
    public void onBindViewHolder(Lm_Adapter.ViewHolder holder, int position) {
        ManagerItem currentItem = lmList.get(position);

        holder.TextName.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return lmList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView TextName;
        public ImageView editImage;
        public ImageView DeleteImage;

        public ViewHolder(View itemView, final Lm_Adapter.OnItemClickListener listener) {
            super(itemView);
            TextName = itemView.findViewById(R.id.lmTextView);
            editImage = itemView.findViewById(R.id.lmEditImage);
            DeleteImage = itemView.findViewById(R.id.lmImage_delete);

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
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
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
