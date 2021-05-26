package com.example.warrantyreminder;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.warrantyreminder.databinding.FragmentDeleteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DeleteFragment extends Fragment {

    private FragmentDeleteBinding binding;
    private String product;
    private String store;
    private String color;
    private long purchaseDate;
    private long expireDate;

    private ImageView productColor;
    private ImageView colorPickerColor;
    private EditText textProduct;
    private EditText textStore;
    private TextView textPurchaseDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeleteBinding.inflate(inflater, container, false);
        // DataModel.reset();

        this.product = getArguments().getString("product");
        this.store = getArguments().getString("store");
        this.color = getArguments().getString("color");
        this.purchaseDate = getArguments().getLong("purchaseDate");
        this.expireDate = getArguments().getLong("expireDate");


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.productColor = getView().findViewById(R.id.colorPurchaseDate);
        this.colorPickerColor = getView().findViewById(R.id.colorPicker);
        this.textProduct = getView().findViewById(R.id.inputProductName);
        this.textStore = getView().findViewById(R.id.inputStore);
        this.textPurchaseDate = getView().findViewById(R.id.inputPurchaseDate);

        this.productColor.setColorFilter(Color.parseColor(this.color));
        this.colorPickerColor.setColorFilter(Color.parseColor(this.color));
        this.textProduct.setText(this.product);
        this.textStore.setText(this.store);
        Calendar purchaseDate = Calendar.getInstance();
        purchaseDate.setTimeInMillis(this.purchaseDate);
        this.textPurchaseDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(purchaseDate.getTime()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
