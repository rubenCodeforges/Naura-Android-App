package com.codeforges.app.naura;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

import com.codeforges.app.naura.adapters.ImageAdapter;
import com.codeforges.app.naura.controllers.DataController;
import com.codeforges.app.naura.helpers.CameraHelper;
import com.codeforges.app.naura.helpers.DialogBuilder;
import com.codeforges.app.naura.helpers.EntityManager;
import com.codeforges.app.naura.helpers.FormHelper;
import com.codeforges.app.naura.helpers.TransportHelper;
import com.codeforges.app.naura.models.NauraData;
import com.codeforges.app.naura.models.User;
import com.google.gson.Gson;
import com.joanzapata.android.iconify.Iconify;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class NewItemActivity extends ActionBarActivity {

    CameraHelper cameraHelper;
    private FormHelper formHelper;
    private Spinner koSpinner;
    private AlertDialog buildingMaterialDialog, fasadDialog, roofDialog,
            windowDialog, floorDialog, heatTypeDialog, terraceDialog, noConnectionDialog;
    private View.OnFocusChangeListener openDialogOnFocus;
    private DataController dataController;
    private EntityManager entityManager;
    private GridView imageGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_object);
        Iconify.addIcons((Button) findViewById(R.id.btnSaveItem));

        Spinner communitySpinner = (Spinner) findViewById(R.id.community);

        this.koSpinner = (Spinner) findViewById(R.id.ko);
        this.dataController = new DataController(this);
        this.entityManager = new EntityManager(this);

        this.formHelper = new FormHelper(this);
        this.formHelper.datePickerAction((EditText) findViewById(R.id.building_date), this);
        this.formHelper.populateSpinner(communitySpinner, R.array.community_list);
        this.cameraHelper = new CameraHelper(this);
        this.imageGrid = (GridView) findViewById(R.id.imageGrid);

        this.initDialogs();
        this.initTabs();

        communitySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        String community = "KO_" + parent.getItemAtPosition(pos).toString().replace(" ", "_");
                        int community_id = getResources().getIdentifier(community, "array", getPackageName());
                        formHelper.populateSpinner(koSpinner, community_id);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                }
        );
        this.openDialogOnFocus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                if (focus) {
                    openDialog(view);
                }
            }
        };

        if (!TransportHelper.isConnected(this)) {
            this.noConnectionDialog.show();
        }

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
        switch (v.getId()) {
            case R.id.object_material_type:
                buildingMaterialDialog.show();
                break;
            case R.id.fasad_type:
                fasadDialog.show();
                break;
            case R.id.roof:
                roofDialog.show();
                break;
            case R.id.windows:
                windowDialog.show();
                break;
            case R.id.floor:
                floorDialog.show();
                break;
            case R.id.heat_type:
                heatTypeDialog.show();
                break;
            case R.id.terrace_fence:
                terraceDialog.show();
                break;
        }
    }

    public void initTabs() {
        TabHost tabs = (TabHost) findViewById(R.id.tabHost);
        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

//        spec.setContent(R.id.tab1);
//        spec.setIndicator(getString(R.string.owner_info));
//        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator(getString(R.string.object_info));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator(getString(R.string.object_structure));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag4");
        spec.setContent(R.id.tab4);
        spec.setIndicator(getString(R.string.object_photos));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }

    public void onNewItem(View v) {

        // TODO: Only one validation , should be more implement user hints
        if (CameraHelper.pathHolder.size() != 0 ) {
            NauraData item = new NauraData();
            Gson gson = new Gson();

            formHelper.submitFormData((ViewGroup) findViewById(R.id.tabHost));

            item.setItemTitle(((TextView) findViewById(R.id.owner_name)).getText().toString());
            item.setItemData(formHelper.getFormData());
            item.setItemImages(gson.toJson(CameraHelper.pathHolder));
            entityManager.persist(item);
            entityManager.flush();
            this.finish();
        }

    }

    public void onDestroy() {
        super.onDestroy();
        CameraHelper.imageUriHolder.clear();
        CameraHelper.pathHolder.clear();
    }

    public void initDialogs() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        this.buildingMaterialDialog = new DialogBuilder().getDialog(
                this, R.string.object_material_type, R.array.object_material, R.id.object_material_type
        );
        findViewById(R.id.object_material_type).setOnFocusChangeListener(this.openDialogOnFocus);

        this.fasadDialog = new DialogBuilder().getDialog(
                this, R.string.fasad_type, R.array.fasad_type, R.id.fasad_type
        );
        findViewById(R.id.fasad_type).setOnFocusChangeListener(this.openDialogOnFocus);

        this.roofDialog = new DialogBuilder().getDialog(
                this, R.string.roof, R.array.roof_type, R.id.roof
        );
        findViewById(R.id.roof).setOnFocusChangeListener(this.openDialogOnFocus);

        this.windowDialog = new DialogBuilder().getDialog(
                this, R.string.windows, R.array.window_type, R.id.windows
        );
        findViewById(R.id.windows).setOnFocusChangeListener(this.openDialogOnFocus);

        this.floorDialog = new DialogBuilder().getDialog(
                this, R.string.floor, R.array.floor_type, R.id.floor
        );
        findViewById(R.id.floor).setOnFocusChangeListener(this.openDialogOnFocus);

        this.heatTypeDialog = new DialogBuilder().getDialog(
                this, R.string.heat_type, R.array.heat_type, R.id.heat_type
        );
        findViewById(R.id.heat_type).setOnFocusChangeListener(this.openDialogOnFocus);

        this.terraceDialog = new DialogBuilder().getDialog(
                this, R.string.terrace_fence, R.array.terrace_fence, R.id.terrace_fence
        );
        findViewById(R.id.terrace_fence).setOnFocusChangeListener(this.openDialogOnFocus);

        this.noConnectionDialog = builder
                .setTitle(R.string.no_connection_title)
                .setMessage(R.string.no_connection_message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();
    }

    public void makePhoto(View v) {
        cameraHelper.onLaunchCamera();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        cameraHelper.processResult(requestCode, resultCode, data);
        this.imageGrid.setAdapter(new ImageAdapter(this));
    }
}
