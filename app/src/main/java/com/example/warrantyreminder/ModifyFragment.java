package com.example.warrantyreminder;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.warrantyreminder.databinding.FragmentModifyBinding;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;

public class ModifyFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentModifyBinding binding;

    private String valueProduct;
    private String valueStore;
    private int valueColor;
    private long valuePurchaseDate;
    private long valueExpireDate;
    private int valueWarrantyLength;
    private int valueWarrantyLengthType;
    private int valueId;
    private boolean valueModify;

    private ImageView viewColor;
    private ImageView viewPickerColor;
    private ImageView viewBackButton;
    private ImageView viewDeleteButton;
    private EditText viewProduct;
    private EditText viewStore;
    private TextView viewPurchaseDate;
    private Spinner viewWarrantyLengthType;
    private EditText viewWarrantyLength;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentModifyBinding.inflate(inflater, container, false);

        this.valueProduct = getArguments().getString("product");
        this.valueStore = getArguments().getString("store");
        this.valueColor = getArguments().getInt("color");
        this.valuePurchaseDate = getArguments().getLong("purchaseDate");
        this.valueExpireDate = getArguments().getLong("expireDate");
        this.valueId = getArguments().getInt("id");
        this.valueWarrantyLength = getArguments().getInt("warrantyLength");
        this.valueWarrantyLengthType = getArguments().getInt("warrantyLengthType");
        this.valueModify = getArguments().getBoolean("delete");

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewColor = getView().findViewById(R.id.colorPurchaseDate);
        this.viewPickerColor = getView().findViewById(R.id.colorPicker);
        this.viewProduct = getView().findViewById(R.id.inputProductName);
        this.viewStore = getView().findViewById(R.id.inputStore);
        this.viewPurchaseDate = getView().findViewById(R.id.inputPurchaseDate);
        this.viewBackButton = getView().findViewById(R.id.buttonReturn);
        this.viewWarrantyLengthType = getView().findViewById(R.id.inputWarrantySpinner);
        this.viewWarrantyLength = getView().findViewById(R.id.inputWarrantyNumber);
        this.viewBackButton = getView().findViewById(R.id.buttonReturn);

        this.viewDeleteButton = getView().findViewById(R.id.deleteButton);
        this.viewDeleteButton.setVisibility(this.valueModify ? View.VISIBLE : View.INVISIBLE);

        getActivity().setTitle(this.valueModify ? "Modify warranty entry" : "Create warranty entry");

        this.viewColor.setBackgroundTintList(ColorStateList.valueOf(this.valueColor));
        this.viewPickerColor.setBackgroundTintList(ColorStateList.valueOf(this.valueColor));
        this.viewProduct.setText(this.valueProduct);
        this.viewStore.setText(this.valueStore);
        this.viewPurchaseDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(valuePurchaseDate));
        this.viewWarrantyLengthType.setOnItemSelectedListener(this);
        this.viewWarrantyLengthType.setSelection(this.valueWarrantyLengthType);
        this.viewWarrantyLength.setText(Integer.toString(this.valueWarrantyLength));


        WarrantyEntry we = new WarrantyEntry();


        binding.fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueWarrantyLength = Integer.valueOf(viewWarrantyLength.getText().toString());

                we.setId(valueId);
                we.setProduct(viewProduct.getText().toString());
                we.setStore(viewStore.getText().toString());
                we.setColor(viewColor.getBackgroundTintList().getDefaultColor());
                we.setWarrantyTypeLength(valueWarrantyLengthType);
                we.setWarrantyLength(valueWarrantyLength);

                DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("dd MMMM yyyy").toFormatter();
                LocalDate date = LocalDate.parse(viewPurchaseDate.getText().toString(), formatter);
                Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
                we.setPurchaseDate(instant.toEpochMilli());

                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(we.getPurchaseDate());
                switch (valueWarrantyLengthType) {
                    case 0:
                        cal.add(Calendar.YEAR, valueWarrantyLength);
                        break;
                    case 1:
                        cal.add(Calendar.MONTH, valueWarrantyLength);
                        break;
                    case 2:
                        cal.add(Calendar.DAY_OF_MONTH, valueWarrantyLength);
                        break;
                }
                we.setWarrantyExpireDate(cal.getTime().getTime());

                if(!valueModify && we.getProduct().isEmpty()) {
                    Toast.makeText(binding.getRoot().getContext(), "No Product given!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(valueModify) {
                    MainActivity.sql.updateProduct(we);
                } else {
                    MainActivity.sql.addProduct(we);
                }
                getActivity().onBackPressed();
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                we.setId(valueId);
                MainActivity.sql.deleteProduct(we);
                getActivity().onBackPressed();
            }
        });

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
        valueWarrantyLengthType = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
