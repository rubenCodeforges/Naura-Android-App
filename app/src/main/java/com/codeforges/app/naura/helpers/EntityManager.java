package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;

import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.models.EntityInterface;
import com.codeforges.app.naura.models.NauraData;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {

    private ArrayList<EntityInterface> modelContainer = new ArrayList<>();
    private Activity activity;
    private NauraDbHelper db;

    public EntityManager (Activity activity) {
        this.db = new NauraDbHelper(activity);
        this.activity = activity;
    }


    public void persist(EntityInterface model) {
          modelContainer.add(model);
    }


    public void flush () {
        for (int i = 0; i < modelContainer.size(); i++) {
            EntityInterface model =  modelContainer.get(i);
            sendData(model);
            db.addProduct(model);
        }
        modelContainer.clear();
    }

    private void sendData (EntityInterface model) {
        if (model instanceof NauraData) {
            String mailTo = "ruben@codeforges.com";
            String subject = "UserId , Naura new Object";
            String body = ((NauraData) model).getItemData();

            ((NauraData) model).setUploaded(
                    TransportHelper.sendMail(
                            activity, mailTo, subject, body, (NauraData) model
                    )
            );
        }
    }
}
