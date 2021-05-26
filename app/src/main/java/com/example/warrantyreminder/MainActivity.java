package com.example.warrantyreminder;

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
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showColorPickerDialog(View view) {
        ColorPicker cp = new ColorPicker(this);
        cp.setRoundColorButton(true);
        cp.setTitle("Choose a nice color");
        cp.setColors(this.colorList);

        ArrayList<String> al = this.colorList;

        cp.show();
        cp.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
            @Override
            public void onChooseColor(int position, int color) {
                ImageView iv = DataModel.secondFragment.getView().findViewById(R.id.colorPurchaseDate);
                iv.setColorFilter(Color.parseColor(al.get(position)));
                iv = DataModel.secondFragment.getView().findViewById(R.id.colorPicker);
                iv.setColorFilter(Color.parseColor(al.get(position)));

                DataModel.color = al.get(position);
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
    }
}