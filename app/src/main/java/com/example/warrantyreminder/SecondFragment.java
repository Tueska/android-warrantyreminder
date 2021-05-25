package com.example.warrantyreminder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.warrantyreminder.databinding.FragmentSecondBinding;
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondFragment extends Fragment implements ColorPickerDialogFragment.ColorPickerDialogListener {

    private FragmentSecondBinding binding;
    private TextView purchaseDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.setDate(new Date());

        binding.fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void setDate(Date date) {
        this.purchaseDate = getView().findViewById(R.id.inputPurchaseDate);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        this.purchaseDate.setText(formatter.format(date));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch(dialogId) {
            case 1:

            case 0:
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {

    }
}