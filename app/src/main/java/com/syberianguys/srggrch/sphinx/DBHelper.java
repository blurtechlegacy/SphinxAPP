package com.syberianguys.srggrch.sphinx;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;



public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    // названия столбцов
    public boolean flag = false;
    //public static final String NAME_COLUMN_NAME = "Name";

    //************************
    static final String DATABASE_TABLE_ADDRESSES = "addresses";

    public static final String ADDRESSES_COLUMN_CITY = "city";
    public static final String ADDRESSES_COLUMN_STREET = "street";
    public static final String ADDRESSES_COLUMN_HOME = "home";
//    public static final String ADDRESSES_COLUMN_NUMBER = "number";
    public static final String ADDRESSES_COLUMN_TIMESTAMP = "timestamp";
    //************************
    static final String DATABASE_TABLE_FLATS = "flats";

    public static final String FLATS_COLUMN_ID = "_id";
    public static final String FLATS_COLUMN_HOMEID = "home_id";
    public static final String FLATS_COLUMN_FLAT = "flat";
    public static final String FLATS_COLUMN_SECURITY = "security";
    public static final String FLATS_COLUMN_FIREALARM = "fire_alarm";
    public static final String FLATS_COLUMN_LEAK = "leak";
    public static final String FLATS_COLUMN_MAGNETFIELD = "magnet_field";
    public static final String FLATS_COLUMN_TIMESTAMP = "timestamp";
    //************************
    static final String DATABASE_TABLE_HISTORY = "history";

    public static final String HISTORY_COLUMN_FLATID = "flat_id";
    public static final String HISTORY_COLUMN_SECURITY = "security";
    public static final String HISTORY_COLUMN_FIREALARM = "fire_alarm";
    public static final String HISTORY_COLUMN_LEAK = "leak";
    public static final String HISTORY_COLUMN_MAGNETFIELD = "magnet_field";
    public static final String HISTORY_COLUMN_TIMESTAMP = "timestamp";




    // имя базы данных
    private static final String DATABASE_NAME = "Sphinx.db";
    // версия базы данных
    private static final int DATABASE_VERSION = 1;
    // имя таблицы
//    private static final String DATABASE_TABLE_NAME = "name";

    // создание базы данных
    private static final String DATABASE_CREATE_ADDRESSES = "CREATE TABLE IF NOT EXISTS "
            + DATABASE_TABLE_ADDRESSES + " (" + BaseColumns._ID + " integer primary key autoincrement, "
            + ADDRESSES_COLUMN_CITY + " text not null, "
            + ADDRESSES_COLUMN_STREET + " text not null,"
            + ADDRESSES_COLUMN_HOME + " INTEGER NOT NULL, "
            + ADDRESSES_COLUMN_TIMESTAMP + " TIMESTAMP NOT NULL);";

    private static final String DATABASE_CREATE_FLATS = "CREATE TABLE IF NOT EXISTS " +
            DATABASE_TABLE_FLATS + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + FLATS_COLUMN_HOMEID + " INTEGER NOT NULL, "
            + FLATS_COLUMN_FLAT + " INTEGER NOT NULL, "
            + FLATS_COLUMN_SECURITY + " TEXT NOT NULL, "
            + FLATS_COLUMN_FIREALARM + " TEXT NOT NULL, "
            + FLATS_COLUMN_LEAK + " TEXT NOT NULL, "
            + FLATS_COLUMN_MAGNETFIELD + " TEXT NOT NULL, "
            + FLATS_COLUMN_TIMESTAMP + "TIMESTAMP NOT NULL);";

    private static final String DATABASE_CREATE_HISTORY = "CREATE TABLE IF NOT EXISTS " +
            DATABASE_TABLE_FLATS + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HISTORY_COLUMN_FLATID + " INTEGER NOT NULL, "
            + HISTORY_COLUMN_SECURITY + " TEXT NOT NULL, "
            + HISTORY_COLUMN_FIREALARM + " TEXT NOT NULL, "
            + HISTORY_COLUMN_LEAK + " TEXT NOT NULL, "
            + HISTORY_COLUMN_MAGNETFIELD + " TEXT, "
            + HISTORY_COLUMN_TIMESTAMP + "TIMESTAMP NOT NULL);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_ADDRESSES);
        db.execSQL(DATABASE_CREATE_FLATS);
        db.execSQL(DATABASE_CREATE_HISTORY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
