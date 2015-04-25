package com.codeforges.app.naura.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.codeforges.app.naura.helpers.CameraHelper;

/**
 * Created by Codeforges on 23.04.15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        this.mContext = c;
    }

    public int getCount() {
        return CameraHelper.imageUriHolder.size();
    }

    public Object getItem(int position) {
        return CameraHelper.imageUriHolder.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        Log.v("image", CameraHelper.imageUriHolder.get(position).toString());
        if (convertView == null) {

            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));


        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageURI(CameraHelper.imageUriHolder.get(position));

        return imageView;
    }
}
