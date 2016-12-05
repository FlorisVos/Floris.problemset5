package com.example.floris.florisproblemset5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Floris on 12/3/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "INDEX_LIST";
    public static final String TABLE_NAME2 = "ITEM_LIST";

    // Table columns
    public static final String _ID = "_id";
    public static final String _ID2 = "_id2";
    public static final String NAME = "name";
    public static final String ITEM = "item";
    public static final String STATUS = "status";
//    public static final String DESC = "description";

    // Database Information
    static final String DB_NAME = "ToDoList.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT);";

    private static final String CREATE_TABLE2 = "create table if not exists " + TABLE_NAME2 + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + _ID2
            + " INTEGER, " + ITEM + " TEXT, " + STATUS + " INTEGER);";

//    + " TEXT NOT NULL, " + DESC - was previously between ITEM and "TEXT);"

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // onCreate creates a new table

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE2); db.execSQL(CREATE_TABLE);
    }

    // onUpgrade will add new information to table

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

}