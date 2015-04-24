package com.codeforges.app.naura.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codeforges.app.naura.models.EntityInterface;
import com.codeforges.app.naura.models.NauraData;

/**
 * Created by Codeforges on 18.04.15.
 */

public class NauraDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "naura.db";
    private static final String TABLE_DATA = "naura_data";
    private static final String COLUMN_NAMES =
              "_id,"
            + "item_title,"
            + "item_data,"
            + "item_images,"
            + "uploaded";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "+TABLE_DATA+" ("
            + "	_id integer primary key autoincrement,"
            + "item_title TEXT ,"
            + "item_data TEXT ,"
            + "item_images TEXT,"
            + "uploaded integer"
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

    public void addProduct(EntityInterface data) {
        ContentValues values = new ContentValues();
        if(data instanceof NauraData) {
            values.put("item_title", ((NauraData) data).getItemTitle());
            values.put("item_data", ((NauraData) data).getItemData());
            values.put("item_images", ((NauraData) data).getItemImages());
            values.put("uploaded", ((NauraData) data).getUploaded());
        }
        flushDb(values);
    }

    // Getting All Contacts
    // TODO : Issue #13
    public Cursor getAllData() {
        String selectQuery = "SELECT  * FROM " + TABLE_DATA;
        Cursor cursor = this.getWritableDatabase().rawQuery(selectQuery,null);
        return cursor;
    }

    private void flushDb (ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_DATA, null, values);
        db.close();
    }
}
