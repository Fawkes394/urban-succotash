package com.example.ep.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ep.Interface.ItemClickListener;
import com.example.ep.R;


public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtProductName, txtProductContact;
    private ItemClickListener itemClickListener;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = itemView.findViewById(R.id.cart_pdt_name);
        txtProductContact = itemView.findViewById(R.id.cart_contact);
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view, getAdapterPosition(), false );

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {

    }
}

