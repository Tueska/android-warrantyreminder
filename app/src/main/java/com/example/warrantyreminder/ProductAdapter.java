package com.example.warrantyreminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>  {

    private Fragment parentFragment;

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
        }
    }

    private List<WarrantyEntry> productList;

    public ProductAdapter(List<WarrantyEntry> productList, Fragment fragment) {
        this.productList = productList;
        this.parentFragment = fragment;
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
        viewHolder.productBackdrop.setBackgroundTintList(ColorStateList.valueOf(we.getColor()));

        // Date calculation
        Calendar purchaseDate = Calendar.getInstance();
        Calendar expireDate = Calendar.getInstance();
        purchaseDate.setTimeInMillis(we.getPurchaseDate());
        expireDate.setTimeInMillis(we.getWarrantyExpireDate());
        long diff = TimeUnit.MILLISECONDS.toDays(expireDate.getTime().getTime() - Calendar.getInstance().getTime().getTime());

        viewHolder.namePurchaseDate.setText("Purchase: " + new SimpleDateFormat("dd MMMM yyyy").format(purchaseDate.getTime()));
        viewHolder.nameRemainingTime.setText(String.format("%s", diff >= 0 ? Long.valueOf(diff) + "d" : "Exp"));


        viewHolder.productBackdrop.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                System.out.println(we);
                new AlertDialog.Builder(v.getContext()).setTitle("Remove").setMessage("Delete warranty entry?").setIcon(R.drawable.ic_delete).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.sql.deleteProduct(we);
                        parentFragment.getParentFragmentManager().beginTransaction().detach(parentFragment).attach(parentFragment).commit();
                    }
                }).setNegativeButton("No", null).show();
                return false;
            }
        });

        viewHolder.productBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", we.getId());
                bundle.putString("product", we.getProduct());
                bundle.putString("store", we.getStore());
                bundle.putInt("color", we.getColor());
                bundle.putLong("purchaseDate", we.getPurchaseDate());
                bundle.putLong("expireDate", we.getWarrantyExpireDate());
                bundle.putInt("warrantyLength", we.getWarrantyLength());
                bundle.putInt("warrantyLengthType", we.getWarrantyTypeLength());
                bundle.putBoolean("delete", true);
                NavHostFragment.findNavController(parentFragment)
                        .navigate(R.id.action_productListFragment_to_modifyFragment, bundle);
            }
        });
    }



    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return productList.size();
    }
}
