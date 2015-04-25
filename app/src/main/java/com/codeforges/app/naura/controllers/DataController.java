package com.codeforges.app.naura.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.widget.ListView;

import com.codeforges.app.naura.R;
import com.codeforges.app.naura.adapters.NauraCursor;
import com.codeforges.app.naura.helpers.EntityManager;

/**
 * Created by Codeforges
 */
public class DataController {
    private Activity mainActivity;

    public DataController(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    // TODO: change structure , handle view as arg instead of getting it from activitiy
    public void loadListAction() {
        ListView itemsList = (ListView) this.mainActivity.findViewById(R.id.buildingList);
        //TODO: issue #13 use entityManager as a layer
        Cursor data = new EntityManager(mainActivity).findAll("data");
        NauraCursor cursor = new NauraCursor(this.mainActivity, data);
        itemsList.setAdapter(cursor);
    }

}
