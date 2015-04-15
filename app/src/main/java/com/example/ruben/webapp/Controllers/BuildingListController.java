package com.example.ruben.webapp.Controllers;

import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ruben.webapp.R;

import java.util.ArrayList;

/**
 * Created by Codeforges
 */
public class BuildingListController {
    private Activity mainActivity;
    private ArrayAdapter itemsAdapter;
    private ArrayList items;

    public BuildingListController(Activity mainActivity) {
        this.mainActivity = mainActivity;
        this.items = new ArrayList<String>();
        this.itemsAdapter = new ArrayAdapter<String>(this.mainActivity,android.R.layout.simple_list_item_1, items);
    }

    public void loadListAction(){
        ListView lvItems = (ListView) this.mainActivity.findViewById(R.id.buildingList);
        lvItems.setAdapter(this.itemsAdapter);
        this.items.add("First Item");
        this.items.add("Second Item");
    }

    /**
     *
     * @param v Event view
     * @param itemTextInput EditText Input for New item
     */
    public void addItemAction (View v, EditText itemTextInput) {
        String itemText = itemTextInput.getText().toString();
        this.itemsAdapter.add(itemText);
        itemTextInput.setText("");
    }

}
