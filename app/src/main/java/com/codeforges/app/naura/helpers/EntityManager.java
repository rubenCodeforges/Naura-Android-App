package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.database.Cursor;

import com.codeforges.app.naura.database.NauraDbHelper;
import com.codeforges.app.naura.models.NauraData;
import com.codeforges.app.naura.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class EntityManager {

    private ArrayList<Object> modelContainer = new ArrayList<>();
    private Activity activity;
    private NauraDbHelper db;

    public EntityManager(Activity activity) {
        this.db = new NauraDbHelper(activity);
        this.activity = activity;
    }


    public void persist(Object model) {
        modelContainer.add(model);
    }


    public void flush() {
        for (int i = 0; i < modelContainer.size(); i++) {
            Object model = modelContainer.get(i);
            if (model instanceof NauraData) {
                sendData(model);
            }
            db.addProduct(model);
        }
        CameraHelper.imageUriHolder.clear();
        CameraHelper.pathHolder.clear();
        modelContainer.clear();
    }

    public boolean hasUser() {
        return db.findAll("user").getCount() > 0;
    }

    public Cursor findAll(String table) {
        return db.findAll(table);
    }

    public Object findById(String table, String id) {
        Cursor cursor = db.findById(table, id);
        Object model = new Object();
        if(table.equals("user")){
            if (cursor.moveToFirst()) {
                do {
                    model = new User();
                    ((User)model).setName(cursor.getString(1));
                    ((User)model).setSpecialId(Integer.parseInt(cursor.getString(2)));
                } while (cursor.moveToNext());
            }
        }
        return model;
    }
    // TODO: Should move to other class
    private void sendData(Object model) {
        if (model instanceof NauraData) {
            User user = (User)this.findById("user","1");
            String mailTo = "ruben@codeforges.com";
            String subject = "Agent id: " +Integer.toString(user.getSpecialId())+" , Naura new Object";
            String body = "";

            Gson gson = new Gson();
            Type mapOfStringObjectType = new TypeToken<Map<String, String>>() {
            }.getType();
            Map<String, String> map = gson.fromJson(((NauraData) model).getItemData(), mapOfStringObjectType);

            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                body += pair.getKey() + ": " + pair.getValue() + "\n";
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
