package com.example.warrantyreminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

    // Daten in den ViewHolder
    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        WarrantyEntry we = productList.get(position);

//        TextView product = viewHolder.nameProduct;
        viewHolder.nameProduct.setText(we.getProduct());
        viewHolder.nameStore.setText(we.getStore());
        viewHolder.namePurchaseDate.setText(Long.toString(we.getPurchaseDate()));
        viewHolder.nameRemainingTime.setText(Long.toString(we.getWarrantyExpireDate()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
