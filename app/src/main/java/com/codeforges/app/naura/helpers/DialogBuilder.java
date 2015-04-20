package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import com.codeforges.app.naura.R;

import java.util.ArrayList;


public class DialogBuilder {

    private String[] availableItems = {};
    private ArrayList<Integer> selectedItems = new ArrayList<>();

    /**
     *  Returns the dialog builder
     * @param activity
     * @return Dialog builder
     */
    private AlertDialog.Builder getDialogBuilder( final Activity activity,final int dataStorageId ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( activity );
        // Add the buttons
        builder.setPositiveButton( R.string.ok_btn, new DialogInterface.OnClickListener() {

            public void onClick( DialogInterface dialog, int id ) {
                EditText dataStorage = ( EditText ) activity.findViewById( dataStorageId );
                String text = "";
                for( int i = 0; i<selectedItems.size(); i++) {
                    text += availableItems[ selectedItems.get( i ) ] + ",";
                }
                dataStorage.setText(text);
            }

        });
        builder.setNegativeButton( R.string.cancel_btn, new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int id ) {
                // User cancelled the dialog
            }
        });
        // Set other dialog properties

        return builder;
    }

    public AlertDialog getDialog( final Activity activity, int titleId, int dataSetId,final int dataStorageId ) {

        AlertDialog.Builder dialog = getDialogBuilder( activity,dataStorageId );
        dialog.setTitle( activity.getString( titleId ) );
        this.availableItems = activity.getResources().getStringArray( dataSetId );

        dialog.setMultiChoiceItems(dataSetId, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which, boolean isChecked ) {
                if ( isChecked ) {
                    selectedItems.add( which );
                } else if ( selectedItems.contains( which ) ) {
                    selectedItems.remove( Integer.valueOf( which ) );
                }
            }
        });

        return dialog.create();
    }
}
