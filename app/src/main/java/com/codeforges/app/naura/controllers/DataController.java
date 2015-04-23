package com.codeforges.app.naura.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import com.codeforges.app.naura.adapters.NauraCursor;
import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.R;

/**
 * Created by Codeforges
 */
public class DataController {
    private Activity mainActivity;
    private NauraDbHelper databaseManager;

    private String newItems="";

    public DataController(Activity mainActivity) {
        //TODO: activity can be remove
        this.mainActivity = mainActivity;
        this.databaseManager = new NauraDbHelper(mainActivity);
    }

    // TODO: change structure , handle view as arg instead of geting it from activitiy
    public void loadListAction(){
        ListView itemsList = (ListView) this.mainActivity.findViewById(R.id.buildingList);
        //TODO: issue #13 use entityManager as a layer
        Cursor data = databaseManager.getAllData();
        NauraCursor cursor = new NauraCursor(this.mainActivity,data);
        itemsList.setAdapter(cursor);
    }

    public void populateList (View v , String listType) {

    }

}
