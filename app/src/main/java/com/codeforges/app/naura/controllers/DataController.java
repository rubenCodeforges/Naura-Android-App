package com.codeforges.app.naura.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsSpinner;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.codeforges.app.naura.database.NauraCursor;
import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.models.NauraData;
import com.codeforges.app.naura.R;

import java.util.ArrayList;
import java.util.Iterator;

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
        //TODO: issue #13 use entityManager as a layer
        Cursor data = databaseManager.getAllData();
        NauraCursor cursor = new NauraCursor(this.mainActivity,data);
        itemsList.setAdapter(cursor);
    }
}
