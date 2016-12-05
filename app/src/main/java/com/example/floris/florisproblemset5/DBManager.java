package com.example.floris.florisproblemset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.example.floris.florisproblemset5.DataBaseHelper.ITEM;
import static com.example.floris.florisproblemset5.DataBaseHelper.NAME;
import static com.example.floris.florisproblemset5.DataBaseHelper.STATUS;
import static com.example.floris.florisproblemset5.DataBaseHelper.TABLE_NAME;
import static com.example.floris.florisproblemset5.DataBaseHelper.TABLE_NAME2;
import static com.example.floris.florisproblemset5.DataBaseHelper._ID;
import static com.example.floris.florisproblemset5.DataBaseHelper._ID2;

/**
 * Created by Floris on 12/3/2016.
 */

public class DBManager {

    private DataBaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(NAME, name);
        database.insert(TABLE_NAME, null, contentValue);
    }

    public void insert2(String name2, String id) {
        Log.d("INSERT2:", name2 + id);
        ContentValues contentValue = new ContentValues();
        contentValue.put(DataBaseHelper.ITEM, name2);
        contentValue.put(DataBaseHelper._ID2, id);
        database.insert(DataBaseHelper.TABLE_NAME2, null, contentValue);

    }


    // previously this formula had databasehelper.desc in it
    public Cursor fetch() {
        String[] columns = new String[] { DataBaseHelper._ID, NAME };
        Cursor cursor = database.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetch2(String listname) {
        String vg = listname + "'";
        String q="SELECT * FROM " + DataBaseHelper.TABLE_NAME2 + " WHERE " + DataBaseHelper._ID2 + " ='" + vg;

        Cursor  cursor = database.rawQuery(q,null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

//    public Cursor fetch2() {
//        String queryString = "SELECT *" + " FROM " + DataBaseHelper.TABLE_NAME2 + " WHERE " + "'" + DataBaseHelper._ID2 + "'" + " = ?";
//        String[] whereArgs = new String[] {
//                listname
//        };
//        Cursor cursor = database.rawQuery(queryString, whereArgs);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }


//     previously this formula had databasehelper.desc in it
    public int update(long _id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        int i = database.update(TABLE_NAME, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    }

    public int update2(long _id, String item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM, item);
        int i = database.update(TABLE_NAME2, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    }

    public int updateStatus(long _id, int status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(STATUS, status);
        int i = database.update(TABLE_NAME2, contentValues, DataBaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(TABLE_NAME, DataBaseHelper._ID + "=" + _id, null);
    }

    public void delete2(long _id2) {
        database.delete(TABLE_NAME2, DataBaseHelper._ID + "=" + _id2, null);
    }

}