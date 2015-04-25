package com.codeforges.app.naura.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.codeforges.app.naura.models.EntityInterface;
import com.codeforges.app.naura.models.NauraData;
import com.codeforges.app.naura.models.User;

/**
 * Created by Codeforges on 18.04.15.
 */

public class NauraDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "naura.db";
    private static final String TABLE_DATA = "naura_data";
    private static final String TABLE_USER = "naura_user";
    // Database creation sql statement
    private static final String DATABASE_CREATE_DATA = "CREATE TABLE " + TABLE_DATA + " ("
            + "	_id integer primary key autoincrement,"
            + "item_title TEXT ,"
            + "item_data TEXT ,"
            + "item_images TEXT,"
            + "uploaded integer"
            + ")";

    private static final String DATABASE_CREATE_USER = "CREATE TABLE " + TABLE_USER + " ("
            + "	_id integer primary key autoincrement,"
            + "name TEXT ,"
            + "special_id TEXT ,"
            + ")";


    public NauraDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_DATA);
        sqLiteDatabase.execSQL(DATABASE_CREATE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(NauraDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(EntityInterface data) {
        ContentValues values = new ContentValues();
        if (data instanceof NauraData) {
            values.put("item_title", ((NauraData) data).getItemTitle());
            values.put("item_data", ((NauraData) data).getItemData());
            values.put("item_images", ((NauraData) data).getItemImages());
            values.put("uploaded", ((NauraData) data).getUploaded());
        }
        if (data instanceof User) {
            values.put("name", ((User) data).getName());
            values.put("special_id", ((User) data).getSpecialId());
        }
        flushDb(values,data.getClass().toString());
    }



    public Cursor findAll(String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + (table == ("user") ? TABLE_USER : TABLE_DATA);
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    private void flushDb(ContentValues values, String table) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(table.equals(User.class.toString()) ? TABLE_USER : TABLE_DATA, null, values);
        db.close();
    }
}
