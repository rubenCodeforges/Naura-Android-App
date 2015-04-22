package com.codeforges.app.naura;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codeforges.app.naura.controllers.DataController;
import com.codeforges.app.naura.helpers.TransportHelper;
import com.joanzapata.android.iconify.Iconify;


public class NauraMain extends ActionBarActivity {

    private DataController listController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wp_main);
        Iconify.addIcons((Button) findViewById(R.id.btnAddItem));
        listController = new DataController(this);
        listController.loadListAction();
        Log.v("transport", Boolean.toString(TransportHelper.isConnected(this)));
    }

    @Override
    protected void onResume () {
        super.onResume();
        listController = new DataController(this);
        listController.loadListAction();
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
