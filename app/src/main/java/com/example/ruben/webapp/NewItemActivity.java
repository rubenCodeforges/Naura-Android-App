package com.example.ruben.webapp;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.example.ruben.webapp.Service.DialogBuilder;
import com.example.ruben.webapp.Service.FormService;
import com.joanzapata.android.iconify.Iconify;


public class NewItemActivity extends ActionBarActivity {
    FormService formService;
    Spinner communitySpinner,koSpinner;
    AlertDialog buildingMaterialDialog,fasadDialog,roofDialog,windowDialog,floorDialog,heatTypeDialog,terraceDialog;

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
                        String community = "KO_" + parent.getItemAtPosition(pos).toString().replace(" ", "_");
                        int community_id = getResources().getIdentifier(community, "array", getPackageName());
                        formService.populateSpinner(koSpinner, community_id);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
        this.initDialogs();
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

    public void openDialog(View v) {
        switch (v.getId()){
            case R.id.object_material_type :
                buildingMaterialDialog.show();
                break;
            case R.id.fasad_type :
                fasadDialog.show();
                break;
            case R.id.roof_type :
                roofDialog.show();
                break;
            case R.id.windows :
                windowDialog.show();
                break;
            case R.id.floor :
                floorDialog.show();
                break;
            case R.id.heat_type :
                heatTypeDialog.show();
                break;
            case R.id.terrace_fence :
                terraceDialog.show();
                break;
        }
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

        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator(getString(R.string.object_structure));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }

    public void initDialogs () {
        this.buildingMaterialDialog = new DialogBuilder().getDialog(
                this, R.string.object_material_type, R.array.object_material, R.id.object_material_type
        );
        this.fasadDialog = new DialogBuilder().getDialog(
                this, R.string.fasad_type, R.array.fasad_type, R.id.fasad_type
        );
        this.roofDialog = new DialogBuilder().getDialog(
                this, R.string.roof, R.array.roof_type, R.id.roof_type
        );
        this.windowDialog = new DialogBuilder().getDialog(
                this, R.string.windows, R.array.window_type, R.id.windows
        );
        this.floorDialog = new DialogBuilder().getDialog(
                this, R.string.floor, R.array.floor_type, R.id.floor
        );
        this.heatTypeDialog = new DialogBuilder().getDialog(
                this, R.string.heat_type, R.array.heat_type, R.id.heat_type
        );
        this.terraceDialog = new DialogBuilder().getDialog(
                this, R.string.terrace_fence, R.array.terrace, R.id.terrace_fence
        );
    }
}
