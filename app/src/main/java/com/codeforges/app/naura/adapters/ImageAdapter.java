package com.codeforges.app.naura.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.codeforges.app.naura.helpers.CameraHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Codeforges on 23.04.15.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        this.mContext = c;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(c)
                .build();
        ImageLoader.getInstance().init(config);
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


        DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheOnDisk(true)
            .build();
        ImageLoader.getInstance().displayImage("file://"+CameraHelper.pathHolder.get(position), imageView, options);

        return imageView;
    }
}
