package com.codeforges.app.naura.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.codeforges.app.naura.R;

/**
 * Created by Codeforges on 19.04.15.
 */
public class NauraCursor extends CursorAdapter {

    public NauraCursor(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
    }


    public void bindView(View view, Context context, Cursor cursor) {

        TextView nauraItem = (TextView) view.findViewById(R.id.itemTitle);

        String title = cursor.getString(cursor.getColumnIndexOrThrow("item_title"));

        nauraItem.setText(title);
    }
}