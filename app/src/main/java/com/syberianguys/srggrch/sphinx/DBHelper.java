package com.syberianguys.srggrch.sphinx;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
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
            + DATABASE_TABLE_ADDRESSES + " (" + BaseColumns._ID + " integer PRIMARY KEY, "
            + ADDRESSES_COLUMN_CITY + " text not null, "
            + ADDRESSES_COLUMN_STREET + " text not null,"
            + ADDRESSES_COLUMN_HOME + " INTEGER NOT NULL, "
            + ADDRESSES_COLUMN_TIMESTAMP + " TIMESTAMP NOT NULL);";

    private static final String DATABASE_CREATE_FLATS = "CREATE TABLE IF NOT EXISTS " +
            DATABASE_TABLE_FLATS + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + FLATS_COLUMN_HOMEID + " INTEGER NOT NULL, "
            + FLATS_COLUMN_FLAT + " INTEGER NOT NULL, "
            + FLATS_COLUMN_SECURITY + " TEXT NOT NULL, "
            + FLATS_COLUMN_FIREALARM + " TEXT NOT NULL, "
            + FLATS_COLUMN_LEAK + " TEXT NOT NULL, "
            + FLATS_COLUMN_MAGNETFIELD + " TEXT NOT NULL, "
            + FLATS_COLUMN_TIMESTAMP + "TIMESTAMP NOT NULL);";

    private static final String DATABASE_CREATE_HISTORY = "CREATE TABLE IF NOT EXISTS " +
            "history" + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, "
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
        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FLATS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ADDRESSES);


        // Создаём новые таблицы
        onCreate(db);
        //заполняем таблицы
        onDBCreated(db);
    }

    public  void onUpdate(SQLiteDatabase db){
        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_FLATS);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ADDRESSES);


        // Создаём новые таблицы
        onCreate(db);
        //заполняем таблицы
        onDBCreated(db);
    }


    public void onDBCreated(SQLiteDatabase mDB){

        mDB.execSQL("INSERT INTO `addresses` VALUES " +
                "(1, 'Новосибирск', 'Ленина', 1, '2018-05-12 20:02:27')");
        mDB.execSQL("INSERT INTO `addresses` VALUES " +
                "(2, 'Новосибирск', 'Богаткова', 201, '2018-05-12 20:22:15');");

        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(1, 1, 1, '1', '1', '1', '1', '2018-05-12 20:48:26')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(2, 1, 2, '1', '1', '0', '0', '2018-05-12 20:49:27')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(3, 2, 1, '1', '0', '1', '1', '2018-05-12 20:49:48')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(4, 2, 2, '0', '0', '0', '0', '2018-05-13 05:33:13')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(5, 2, 3, '1', '1', '1', '1', '2018-05-13 05:33:45')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(6, 2, 4, '1', '0', '1', '1', '2018-05-13 05:36:32')");
        mDB. execSQL("INSERT INTO `flats` VALUES " +
                "(7, 1, 3, '0', '1', '1', '1', '2018-05-13 05:44:14')");

//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 1, '1', '1', '1', '1', '2018-05-12 22:38:05')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 1, '1', '1', '0', '1', '2018-05-12 22:38:26')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 1, '1', '1', '1', '1', '2018-05-12 22:38:33')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 1, '0', '1', '1', '1', '2018-05-12 22:38:47')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 1, '1', '1', '1', '1', '2018-05-12 22:38:56')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 2, '1', '1', '1', '1', '2018-05-12 22:39:19')" );
//        mDB.execSQL("INSERT INTO `history` VALUES " +
//                "(null, 3, '1', '1', '1', '1', '2018-05-12 22:39:28')" );
//        mDB. execSQL("INSERT INTO `history` VALUES " +
//                "(null, 3, '1', '0', '0', '0', '2018-05-12 22:39:42')" );
//        mDB. execSQL("INSERT INTO `history` VALUES " +
//                "(null, 3, '1', '1', '1', '0', '2018-05-12 22:39:55')");
//        mDB. execSQL("INSERT INTO `history` VALUES " +
//                "(null, 3, '1', '1', '1', '1', '2018-05-12 22:40:09');");

    }
}
