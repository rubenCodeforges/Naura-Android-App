package com.codeforges.app.naura.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Log;

import com.codeforges.app.naura.models.EntityInterface;
import com.codeforges.app.naura.models.NauraData;

public class TransportHelper {

    public static int sendMail (Activity activity, String mailto , String subject, String body , NauraData model) {

        if(isConnected(activity)) {
            Log.i("Send email", "");

            String[] TO = {mailto};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("application/json");


            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, body);

            try {
                activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                model.setUploaded(1);
                //finish();
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
