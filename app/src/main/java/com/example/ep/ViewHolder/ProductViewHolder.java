package com.example.ep.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ep.Interface.ItemClickListener;
import com.example.ep.R;

public class ProductViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductDescription, txtProductContact;
    public ImageView imageView;
    public ItemClickListener listner;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.pdt_image);
        txtProductName = (TextView) itemView.findViewById(R.id.pdt_name);
        txtProductDescription = (TextView) itemView.findViewById(R.id.pdt_description);
        txtProductContact = (TextView) itemView.findViewById(R.id.pdt_contacts);

    }

    public void setItemClickListener(ItemClickListener listner){
        this.listner = listner;
    }

    @Override
    public void onClick(View view) {

        listner.onClick(view, getAdapterPosition(), false);

    }
}
