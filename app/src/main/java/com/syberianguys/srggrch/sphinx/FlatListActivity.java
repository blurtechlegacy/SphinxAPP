package com.syberianguys.srggrch.sphinx;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FlatListActivity extends AppCompatActivity {

    DBHelper DBHelper;
    SQLiteDatabase mDB;
    ListView fList;
    int pos = 1;
    String LOG_TAG = "FlatListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);
        pos = getIntent().getIntExtra("position", 0);
        DBHelper = new DBHelper(this, "Sphinx.db", null, 1);
        mDB = DBHelper.getReadableDatabase();

        fList = findViewById(R.id.fList);

        FLAdapter adapter = new FLAdapter(this, makeFlats(pos));
        fList.setAdapter(adapter);

    }
    private FlatSTR[] makeFlats(int position) {
        Cursor c;
        c = mDB.query("flats", new String[]{DBHelper.FLATS_COLUMN_FLAT, DBHelper.FLATS_COLUMN_FIREALARM, DBHelper.FLATS_COLUMN_MAGNETFIELD, DBHelper.FLATS_COLUMN_LEAK, DBHelper.FLATS_COLUMN_SECURITY, DBHelper.FLATS_COLUMN_HOMEID},
                null, null,
                null, null, null);
        Cursor cA;
        cA = mDB.query("addresses", new String[]{BaseColumns._ID},
                null, null,
                null, null, null);


        c.moveToFirst();
        cA.moveToPosition(position);
        int size = 0;
        int x = c.getCount();
        for (int i = 0; i < c.getCount(); i++) {

            if (c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_HOMEID)) == cA.getInt(cA.getColumnIndex(BaseColumns._ID))) {
                size++;
            }
            c.moveToNext();
        }
        FlatSTR[] arr = new FlatSTR[size];
        int j = 0;
        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {

            if (c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_HOMEID)) == cA.getInt(cA.getColumnIndex(BaseColumns._ID))) {
                FlatSTR Name = new FlatSTR();
                Name.flat = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_FLAT));
                Name.fire_alarm = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_FIREALARM));
                Name.security = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_SECURITY));
                Name.leak = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_LEAK));
                Name.magnet_field = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_MAGNETFIELD));
                arr[j++] = Name;
            }
            c.moveToNext();
        }

            c.close();
            return arr;

    }
}

