package com.codeforges.app.naura.helpers;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.codeforges.app.naura.NewItemActivity;
import com.codeforges.app.naura.R;
import com.codeforges.app.naura.adapters.ImageAdapter;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CameraHelper {
    public final String APP_TAG = "NauraObjects";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    //TODO: Move agent id to settings
    public final static String agentId = "111";
    public String photoFileName;
    // TODO: Uris should be store somewhere else
    public static ArrayList <Uri> imageUriHolder = new ArrayList<>();
    public static ArrayList <String> pathHolder = new ArrayList<>();
    private Activity activity;

    public CameraHelper (Activity activity) {
        this.activity = activity;
    }
    public void onLaunchCamera() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-MM-ss");
        photoFileName = agentId +"_" + dateFormat.format(new Date()) + ".jpg";
        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name
        // Start the image capture intent to take photo
        activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    // Returns the Uri for a photo stored on disk given the fileName
    public Uri getPhotoFileUri(String fileName) {
        // Get safe storage directory for photos
        File mediaStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), APP_TAG);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
            Log.d(APP_TAG, "failed to create directory");
        }

        // Return the file target for the photo based on filename
        return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
    }

    public void proccessResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                CameraHelper.imageUriHolder.add(takenPhotoUri);
                CameraHelper.pathHolder.add(takenPhotoUri.getPath());

            } else { // Result was a failure
                Toast.makeText(activity, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
