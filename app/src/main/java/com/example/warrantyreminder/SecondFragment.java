package com.example.warrantyreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.warrantyreminder.databinding.FragmentSecondBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentSecondBinding binding;
    private TextView purchaseDate;
    private EditText product;
    private EditText store;
    private EditText warrantyTime;
    private Spinner warrantyLengthType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        // DataModel.reset();

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.purchaseDate = getView().findViewById(R.id.inputPurchaseDate);
        this.product = getView().findViewById(R.id.inputProductName);
        this.store = getView().findViewById(R.id.inputStore);
        this.warrantyTime = getView().findViewById(R.id.inputWarrantyNumber);
        this.warrantyLengthType = getView().findViewById(R.id.inputWarrantySpinner);
        this.warrantyLengthType.setOnItemSelectedListener(this);
        Date currentDate = new Date();
        DataModel.dateYear = currentDate.getYear() + 1900;
        DataModel.dateMonth = currentDate.getMonth() + 1;
        DataModel.dateDayOfMonth = currentDate.getDate();

        this.setDate(currentDate);
        DataModel.secondFragment = this;

        binding.fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: HIER SPEICHERN ALLA
//                if(DataModel.secondFragment.product.getText().toString().matches("")) {
//                    Snackbar.make(view, "No information entered!", Snackbar.LENGTH_SHORT).show();
//                    return;
//                }
                DataModel.warrantyLength = Integer.valueOf(DataModel.secondFragment.warrantyTime.getText().toString());
                dumpDB();

                DataModel.reset();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }

            private void dumpDB() {
                WarrantyEntry we = new WarrantyEntry();
                we.setProduct(DataModel.secondFragment.product.getText().toString());
                we.setStore(DataModel.secondFragment.store.getText().toString());
                Date purchaseDate = new Date(DataModel.dateYear - 1900, DataModel.dateMonth - 1, DataModel.dateDayOfMonth);
                we.setPurchaseDate(purchaseDate.getTime());

                Calendar cal = Calendar.getInstance();
                cal.setTime(purchaseDate);
                switch (DataModel.warrantyLengthType) {
                    case DataModel.WARRANTY_YEARS:
                        cal.add(Calendar.YEAR, DataModel.warrantyLength);
                        break;
                    case DataModel.WARRANTY_MONTHS:
                        cal.add(Calendar.MONTH, DataModel.warrantyLength);
                        break;
                    case DataModel.WARRANTY_DAYS:
                        cal.add(Calendar.DAY_OF_MONTH, DataModel.warrantyLength);
                        break;
                }
                we.setWarrantyExpireDate(cal.getTime().getTime());

                System.out.println("PRODUCT:\t" + we.getProduct() + "\nSTORE:\t" + we.getStore() + "\nPURCHASE DATE:\t" + we.getPurchaseDate() + "\nEXPIRE DATE:\t" + we.getWarrantyExpireDate());
            }
        });

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel.reset();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    protected void setDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        this.purchaseDate.setText(formatter.format(date));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        DataModel.warrantyLengthType = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void update() {
        this.purchaseDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(new Date(DataModel.dateYear - 1900, DataModel.dateMonth - 1, DataModel.dateDayOfMonth)));
    }
}