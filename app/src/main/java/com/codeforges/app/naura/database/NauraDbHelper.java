package com.codeforges.app.naura.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codeforges.app.naura.models.NauraData;

/**
 * Created by Codeforges on 18.04.15.
 */

public class NauraDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "naura.db";
    public static final String TABLE_DATA = "naura_data";
    private static final String COLUMN_NAMES =
              "_id,"
            + "item_title,"
            + "owner_name,"
            + "owner_phone,"
            + "other_contacts,"
            + "object_form,"
            + "object_structure_form,"
            + "photo_urls";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "+TABLE_DATA+" ("
            + "	_id integer primary key autoincrement,"
            + "item_title TEXT ,"
            + "owner_name TEXT ,"
            + "owner_phone TEXT ,"
            + "other_contacts TEXT ,"
            + "object_form TEXT ,"
            + "object_structure_form TEXT ,"
            + "photo_urls TEXT"
            + ")";

    public NauraDbHelper ( Context context ) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(NauraDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(NauraData data) {
        ContentValues values = new ContentValues();
        values.put("item_title", data.getItemTitle());
        values.put("owner_name", data.getOwnerName());
        values.put("owner_phone", data.getOwnerPhone());
        values.put("other_contacts", data.getOtherContacts());
        values.put("object_form", data.getOtherContacts());
        values.put("object_structure_form", data.getOtherContacts());
        values.put("photo_urls", data.getOtherContacts());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_DATA, null, values);
        db.close();
    }

    // Getting All Contacts
    public Cursor getAllData() {
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;
        Cursor cursor = this.getWritableDatabase().rawQuery(selectQuery,null);
        Log.v("naura",cursor.toString());
        return cursor;
    }
}
