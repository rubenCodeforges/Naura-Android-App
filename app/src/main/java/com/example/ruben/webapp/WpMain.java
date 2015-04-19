package com.example.ruben.webapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ruben.webapp.Controllers.DataController;
import com.joanzapata.android.iconify.Iconify;


public class WpMain extends ActionBarActivity {

    private DataController listController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wp_main);
        Iconify.addIcons((Button) findViewById(R.id.btnAddItem));
        Log.v("naura","app started");
//        listController = new BuildingListController(this);
//        listController.loadListAction();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onNewItem (View v) {
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivity(intent);
    }

}
