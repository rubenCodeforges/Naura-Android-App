package com.codeforges.app.naura;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codeforges.app.naura.controllers.DataController;
import com.codeforges.app.naura.helpers.EntityManager;
import com.codeforges.app.naura.models.User;
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
        final EntityManager entityManager = new EntityManager(this);

        if (!entityManager.hasUser()) {
            LayoutInflater inflater = getLayoutInflater();
            final View newUserDialog = inflater.inflate(R.layout.new_user, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.new_user_dialog_title)
                    .setView(newUserDialog)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //TODO: Needs validation
                            EditText name = (EditText) newUserDialog.findViewById(R.id.user_name);
                            EditText id = (EditText) newUserDialog.findViewById(R.id.user_id);
                            User user = new User();
                            user.setName(name.getText().toString());
                            user.setSpecialId(Integer.valueOf(id.getText().toString()));
                            entityManager.persist(user);
                            entityManager.flush();
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    @Override
    protected void onResume() {
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

    public void onNewItem(View v) {
        Intent intent = new Intent(this, NewItemActivity.class);
        startActivity(intent);
    }

    public void resendItem(View v) {
        //TODO: implement resend action
    }
}
