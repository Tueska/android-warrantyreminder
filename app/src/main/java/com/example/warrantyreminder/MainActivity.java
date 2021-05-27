package com.example.warrantyreminder;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.example.warrantyreminder.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> colorList;
    public static SQLiteHelper sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.fillColors();
        this.sql = new SQLiteHelper(this);

        setSupportActionBar(binding.toolbar);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment(view);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showColorPickerDialog(View view) {
        ColorPicker cp = new ColorPicker(this);
        cp.setRoundColorButton(true);
        cp.setTitle("");
        cp.setColors(this.colorList);
        cp.getDialogViewLayout().setBackgroundColor(getResources().getColor(R.color.black, getTheme()));

        ArrayList<String> colorList = this.colorList;

        cp.show();
        cp.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                ImageView colors = view.getRootView().findViewById(R.id.colorPurchaseDate);
                colors.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorList.get(position))));
                colors = view.getRootView().findViewById(R.id.colorPicker);
                colors.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(colorList.get(position))));
            }

            @Override
            public void onCancel() {
                return;
            }
        });
    }

    private void fillColors() {
        this.colorList = new ArrayList<>();
        this.colorList.add("#2A2A2A");
        this.colorList.add("#38A18B");
        this.colorList.add("#CB7C7A");
        this.colorList.add("#CDA35F");
        this.colorList.add("#266678");
        this.colorList.add("#062E5F");
        this.colorList.add("#FBA700");
        this.colorList.add("#3DDC84");
        this.colorList.add("#BE82CA");
        this.colorList.add("#E20639");
        this.colorList.add("#585481");
        this.colorList.add("#568EA5");
        this.colorList.add("#4B3B47");
        this.colorList.add("#36151E");
        this.colorList.add("#F08C8E");
    }
}