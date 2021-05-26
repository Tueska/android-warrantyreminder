package com.example.warrantyreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private final View mView;
    private TextView dateDisplay;

    public DatePickerFragment(View view) {
        this.mView = view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar pd = Calendar.getInstance();
        pd.set(year, month, day);
        TextView tv = this.mView.findViewById(R.id.inputPurchaseDate);
        tv.setHint(Long.toString(pd.getTime().getTime()));
        tv.setText(new SimpleDateFormat("dd MMMM yyyy").format(pd.getTime()));
    }
}
