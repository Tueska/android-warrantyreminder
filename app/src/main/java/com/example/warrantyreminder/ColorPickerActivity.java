package com.example.warrantyreminder;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.github.danielnilsson9.colorpickerview.view.ColorPanelView;
import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;

public class ColorPickerActivity extends Activity implements ColorPickerView.OnColorChangedListener, View.OnClickListener {
    private ColorPickerView mColorPickerView;
    private ColorPanelView mOldColorPanelView;
    private ColorPanelView mNewColorPanelView;

    private Button mOkButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.activity_color_picker);

        init();
    }

    private void init() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initicalColor = prefs.getInt("color_3", 0xFF000000);

        this.mColorPickerView = findViewById(R.id.colorpickerview__color_picker_view);
        this.mOldColorPanelView = findViewById(R.id.colorpickerview__color_panel_old);
        this.mNewColorPanelView = findViewById(R.id.colorpickerview__color_panel_new);

        this.mOkButton = findViewById(R.id.okButton);
        this.mCancelButton = findViewById(R.id.cancelButton);

        this.mColorPickerView.setOnColorChangedListener(this);
        this.mColorPickerView.setColor(initicalColor, true);
        this.mOldColorPanelView.setColor(initicalColor);

        this.mOkButton.setOnClickListener(this);
        this.mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.okButton:
                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
                edit.putInt("color_3", this.mColorPickerView.getColor());
                edit.commit();

                finish();
                break;
            case R.id.cancelButton:
                finish();
                break;
        }
    }

    @Override
    public void onColorChanged(int newColor) {
        this.mNewColorPanelView.setColor(this.mColorPickerView.getColor());
    }
}
