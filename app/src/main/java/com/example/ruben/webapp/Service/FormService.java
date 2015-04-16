package com.example.ruben.webapp.Service;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ruben.webapp.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormService {

    public Activity activity;

    public FormService(Activity context){
        this.activity = context;
    }

    public void datePickerAction (final EditText editText, final Activity context) {
        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                editText.setText(sdf.format(calendar.getTime()));
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    /**
     *
     * @param spinner Spinner to populate
     * @param resource The resource id where the population data is stored
     */
    public void populateSpinner (Spinner spinner , int resource ) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
                createFromResource(activity , resource, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }
}
