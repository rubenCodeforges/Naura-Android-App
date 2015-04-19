package com.example.ruben.webapp.Controllers;

import android.app.Activity;
import android.database.Cursor;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.ruben.webapp.Database.NauraCursor;
import com.example.ruben.webapp.Database.NauraDbHelper;
import com.example.ruben.webapp.Models.NauraData;
import com.example.ruben.webapp.R;

import java.util.ArrayList;

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

    public void addItemAction (ViewGroup layout) {

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);

            if (child instanceof ViewGroup){
                addItemAction((ViewGroup)child);
            } else {

                if (child instanceof EditText) {
                    newItems += "\"data\":{";
                    String value = ((EditText) child).getText().toString();
                    String key = child.getResources().getResourceEntryName(child.getId());
                    String keyValue = "\""+key+ "\":\"" + value + "\"";
                    newItems += keyValue +"}},";
                } else if (child instanceof TextView) {
                    newItems += "{\"title\" : \""+((TextView) child).getText().toString()+"\",";
                }
            }
        }
    }

    public String getNewItemsJson () {
        return "[" + newItems.substring(0,newItems.length()-1) + "]";
    }

}
