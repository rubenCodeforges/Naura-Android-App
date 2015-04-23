package com.codeforges.app.naura.helpers;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableRow;

import com.codeforges.app.naura.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FormHelper {

    private Activity activity;
    private String formData = "";
    public  FormHelper(Activity context){
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
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if(focus){
                    new DatePickerDialog(context, date, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
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

    public String getFormData() {
        return "{\n" + formData + "}";
    }

    public void submitFormData (ViewGroup layout) {
            storeFormData(layout);
            formData += getSpinnerData();
    }

    private void storeFormData(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            String key;
            String value;

            if (child instanceof ViewGroup){
                storeFormData((ViewGroup) child);
            } else {

                if (child instanceof EditText) {
                    String stringName = activity.getResources().getResourceEntryName(child.getId());
                    int stringId = activity.getResources().getIdentifier(stringName,"string",activity.getPackageName());

                    key = activity.getResources().getString(stringId);
                    value = ((EditText) child).getText().toString();

                    formData += "\"" + key + "\":"+ "\"" + value + "\",\n";
                }

                if (child instanceof RadioButton && ((RadioButton)child).isChecked()) {
                    View parent = (View) child.getParent();
                    String stringName = activity.getResources().getResourceEntryName(parent.getId());
                    int stringId = activity.getResources().getIdentifier(stringName,"string",activity.getPackageName());

                    key = activity.getResources().getString(stringId);
                    value = ((RadioButton) child).getText().toString();

                    formData += "\"" + key + "\":"+ "\"" + value + "\",\n";
                    Log.v("form-data", formData);
                }

            }
        }
    }
    private String getSpinnerData() {
        String communityVal = ((Spinner) activity.findViewById(R.id.community)).getSelectedItem().toString();
        String koVal = ((Spinner) activity.findViewById(R.id.ko)).getSelectedItem().toString();

        return "\"community\" : " + " \"" + communityVal + "\" ," + " \"koValue\" : " + " \"" + koVal + "\"\n";
    }

}
