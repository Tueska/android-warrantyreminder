package com.example.warrantyreminder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import com.example.warrantyreminder.databinding.FragmentDeleteBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeleteFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentDeleteBinding binding;
    private String product;
    private String store;
    private int color;
    private long purchaseDate;
    private long expireDate;
    private int warrantyLength;
    private int warrantyLengthType;
    private int id;

    private ImageView productColor;
    private ImageView colorPickerColor;
    private ImageView backButton;
    private EditText textProduct;
    private EditText textStore;
    private TextView textPurchaseDate;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDeleteBinding.inflate(inflater, container, false);
        // DataModel.reset();

        this.product = getArguments().getString("product");
        this.store = getArguments().getString("store");
        this.color = getArguments().getInt("color");
        this.purchaseDate = getArguments().getLong("purchaseDate");
        this.expireDate = getArguments().getLong("expireDate");
        this.id = getArguments().getInt("id");
        this.warrantyLength = getArguments().getInt("warrantyLength");
        this.warrantyLengthType = getArguments().getInt("warrantyLengthType");


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.productColor = getView().findViewById(R.id.colorPurchaseDate);
        this.colorPickerColor = getView().findViewById(R.id.colorPicker);
        this.textProduct = getView().findViewById(R.id.inputProductName);
        this.textStore = getView().findViewById(R.id.inputStore);
        this.textPurchaseDate = getView().findViewById(R.id.inputPurchaseDate);
        this.backButton = getView().findViewById(R.id.buttonReturn);

        this.productColor.setBackgroundTintList(ColorStateList.valueOf(this.color));
        this.colorPickerColor.setBackgroundTintList(ColorStateList.valueOf(this.color));
        this.textProduct.setText(this.product);
        this.textStore.setText(this.store);
        this.textPurchaseDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(purchaseDate));

        binding.fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WarrantyEntry we = new WarrantyEntry();

                we.setId(id);
                we.setProduct(textProduct.getText().toString());
                we.setStore(textStore.getText().toString());
                we.setColor(color);
                we.setWarrantyTypeLength(warrantyLengthType);
                we.setWarrantyLength(warrantyLength);



                we.setPurchaseDate(purchaseDate);
                we.setWarrantyExpireDate(expireDate);



                System.out.println(we);

                NavHostFragment.findNavController(DeleteFragment.this)
                        .navigate(R.id.action_DeleteFragment_to_FirstFragment);
            }
        });

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(DeleteFragment.this)
                        .navigate(R.id.action_DeleteFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
