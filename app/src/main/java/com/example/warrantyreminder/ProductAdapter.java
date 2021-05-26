package com.example.warrantyreminder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameProduct;
        public TextView nameStore;
        public TextView namePurchaseDate;
        public TextView nameRemainingTime;
        public ImageView productBackdrop;

        public ViewHolder(View itemView) {
            super(itemView);

            this.nameProduct = (TextView) itemView.findViewById(R.id.product_Name);
            this.nameStore = (TextView) itemView.findViewById(R.id.product_Store);
            this.namePurchaseDate = (TextView) itemView.findViewById(R.id.product_PurchaseDate);
            this.nameRemainingTime = (TextView) itemView.findViewById(R.id.product_RemainingTime);
            this.productBackdrop = (ImageView) itemView.findViewById(R.id.product_Backdrop);

            this.productBackdrop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Open Settings of Item
                    System.out.println("HEJJJ");
                }
            });
        }
    }

    private List<WarrantyEntry> productList;

    public ProductAdapter(List<WarrantyEntry> productList) {
        this.productList = productList;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        WarrantyEntry we = productList.get(position);

        viewHolder.nameProduct.setText(we.getProduct());
        viewHolder.nameStore.setText(we.getStore());
        viewHolder.productBackdrop.setColorFilter(Color.parseColor(we.getColor()));

        // Date calculation
        Calendar purchaseDate = Calendar.getInstance();
        Calendar expireDate = Calendar.getInstance();
        purchaseDate.setTimeInMillis(we.getPurchaseDate());
        expireDate.setTimeInMillis(we.getWarrantyExpireDate());
        long diff = TimeUnit.MILLISECONDS.toDays(expireDate.getTime().getTime() - Calendar.getInstance().getTime().getTime());

        viewHolder.namePurchaseDate.setText("Purchase: " + new SimpleDateFormat("dd MMMM yyyy").format(purchaseDate.getTime()));
        viewHolder.nameRemainingTime.setText(String.format("%s", diff >= 0 ? Long.valueOf(diff) + "d" : "Exp"));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
