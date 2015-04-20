package com.codeforges.app.naura.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codeforges.app.naura.database.NauraCursor;
import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.models.NauraData;
import com.codeforges.app.naura.R;

/**
 * Created by Codeforges
 */
public class DataController {
    private Activity mainActivity;
    private NauraDbHelper databaseManager;

    private String newItems="";

    public DataController(Activity mainActivity) {
        this.mainActivity = mainActivity;
        this.databaseManager = new NauraDbHelper(mainActivity);
    }

    public void loadListAction(){
        ListView itemsList = (ListView) this.mainActivity.findViewById(R.id.buildingList);
        Cursor data = databaseManager.getAllData();
        NauraCursor cursor = new NauraCursor(this.mainActivity,data);
        itemsList.setAdapter(cursor);
    }

    public void persistFormAction(ViewGroup layout) {

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if (child instanceof ViewGroup){
                persistFormAction((ViewGroup) child);
            } else {

                if (child instanceof EditText) {
                    // newItems += "\"data\":{";
                    String value = ((EditText) child).getText().toString();
                    //String key = child.getResources().getResourceEntryName(child.getId());
                    //String keyValue = "\""+key+ "\":\"" + value + "\"";
                    newItems += "\"" +value +"\",";
                } else if (child instanceof TextView) {
                    //newItems += "{\"title\" : \""+((TextView) child).getText().toString()+"\",";
                    newItems += "\""+((TextView) child).getText().toString()+"\":";
                }
            }
        }
    }

    public String getNewItemsJson () {
        return "{" + newItems.substring(0,newItems.length()-1) + "}";
    }

    public void storeDataAction () throws Exception {
        if (!newItems.isEmpty()) {
            NauraData data = new NauraData();

            data.setItemTitle( "Object owner: "
                    + ((EditText)mainActivity.findViewById(R.id.ownerName)).getText().toString()
            );

            data.setOwnerName(((EditText)mainActivity.findViewById(R.id.ownerName)).getText().toString());
            data.setOwnerPhone(((EditText)mainActivity.findViewById(R.id.ownerPhone)).getText().toString());
            data.setOtherContacts(((EditText)mainActivity.findViewById(R.id.ownerOther)).getText().toString());

            databaseManager.addProduct(data);
        }else {
            throw new Exception("item data should be persisted first");
        }
    }
}
