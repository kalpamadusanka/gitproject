package com.example.myapplicationtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class Adapteritem extends RecyclerView.Adapter<Adapteritem.ItemViewHolder> {
    Context context;
    ArrayList<datauser>datauserArrayList;

    public Adapteritem(Context context, ArrayList<datauser> datauserArrayList) {
        this.context = context;
        this.datauserArrayList = datauserArrayList;
    }

    @NonNull
    @Override
    public Adapteritem.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapteritem.ItemViewHolder holder, int position) {
        datauser datauser=datauserArrayList.get(position);
        holder.name.setText(datauser.getName());
        holder.address.setText(datauser.getAddress());
        holder.no.setText(datauser.getMobileno());

    }

    @Override
    public int getItemCount() {
        return datauserArrayList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView name,no,address;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.rname);
            no=itemView.findViewById(R.id.mobile);
            address=itemView.findViewById(R.id.addressitem);


        }

    }
}
