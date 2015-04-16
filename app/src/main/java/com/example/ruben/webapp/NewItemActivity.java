package com.example.ruben.webapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;

import com.example.ruben.webapp.Service.FormService;
import com.joanzapata.android.iconify.Iconify;


public class NewItemActivity extends ActionBarActivity {
    FormService formService;
    Spinner communitySpinner;
    Spinner koSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_object);
        Iconify.addIcons((Button) findViewById(R.id.btnSaveItem));
        this.communitySpinner = (Spinner) findViewById(R.id.community_input);
        this.koSpinner = (Spinner) findViewById(R.id.ko_input_spinner);

        this.formService = new FormService(this);
        this.formService.datePickerAction((EditText) findViewById(R.id.building_date),this);
        this.formService.populateSpinner(
                this.communitySpinner, R.array.community_list
        );
        this.communitySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String community = "KO_"+parent.getItemAtPosition(pos).toString().replace(" ","_");
                        int community_id = getResources().getIdentifier(community,"array",getPackageName());
                        formService.populateSpinner(koSpinner, community_id);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
        this.initTabs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_object, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initTabs () {
        TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.tab1);
        spec.setIndicator(getString(R.string.owner_info));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getString(R.string.object_info));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }
}
