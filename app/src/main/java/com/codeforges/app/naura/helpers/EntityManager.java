package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;

import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.models.EntityInterface;
import com.codeforges.app.naura.models.NauraData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
        CameraHelper.imageUriHolder.clear();
        CameraHelper.pathHolder.clear();

    }

    // TODO: Should move to other class
    private void sendData (EntityInterface model) {
        if (model instanceof NauraData) {
            String mailTo = "ruben@codeforges.com";
            String subject = "UserId , Naura new Object";
            String body = "";

            Gson gson = new Gson();
            Type mapOfStringObjectType = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> map = gson.fromJson(((NauraData) model).getItemData(), mapOfStringObjectType);

            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                body +=pair.getKey() + ": " + pair.getValue() + "\n";
                it.remove();
            }

            ((NauraData) model).setUploaded(
                    TransportHelper.sendMail(
                            activity, mailTo, subject, body, (NauraData) model
                    )
            );
        }
    }
}
