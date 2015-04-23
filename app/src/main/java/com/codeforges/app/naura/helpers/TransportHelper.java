package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import com.codeforges.app.naura.models.NauraData;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TransportHelper {

    public static int sendMail (Activity activity, String mailto , String subject, String body , NauraData model) {

        if(isConnected(activity)) {
            Log.i("Send email", "");

            String[] TO = {mailto};
            Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);
            emailIntent.setPackage("com.google.android.gm");

            Gson gson = new Gson();
            ArrayList<String> paths = gson.fromJson(model.getItemImages() , new ArrayList<>().getClass());

            ArrayList<Uri> uris = new ArrayList<>();
            for(String path : paths ){
                File file = new File(path);
                uris.add(Uri.fromFile(file));
            }

            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

            try {
                activity.startActivity(emailIntent);
                model.setUploaded(1);
                //activity.finish();
                Log.i("naura-i", "Finished sending email...");
            } catch (android.content.ActivityNotFoundException ex) {
                Log.w("naura-warning", "There is no email client installed.");
            }

        }
        return isConnected(activity) ? 1 : 0 ;
    }

    public static boolean isConnected(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnected();
    }
}
