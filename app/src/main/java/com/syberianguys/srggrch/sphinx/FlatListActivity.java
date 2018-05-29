package com.syberianguys.srggrch.sphinx;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);
        pos = getIntent().getIntExtra("position", 0) + 1;
        DBHelper = new DBHelper(this, "Sphinx.db", null, 1);
        mDB = DBHelper.getReadableDatabase();

        fList = findViewById(R.id.fList);

        FLAdapter adapter = new FLAdapter(this, makeFlats());
        fList.setAdapter(adapter);

    }
    private FlatSTR[] makeFlats() {
        Cursor c;
        c = mDB.query("flats", new String[]{DBHelper.FLATS_COLUMN_FLAT, DBHelper.FLATS_COLUMN_FIREALARM, DBHelper.FLATS_COLUMN_MAGNETFIELD, DBHelper.FLATS_COLUMN_LEAK, DBHelper.FLATS_COLUMN_SECURITY, DBHelper.FLATS_COLUMN_HOMEID},
                null, null,
                null, null, null);
        Cursor cr;
        cr = mDB.query("addresses", new String[]{DBHelper.ADDRESSES_COLUMN_HOME},
                null, null,
                null, null, null);

        FlatSTR[] arr = new FlatSTR[c.getCount()];
        c.moveToFirst();
        cr.moveToPosition(pos);
//        int homeid[] = new int[cr.getCount()];
        for (int i = 0; i < arr.length; i++) {
            if (c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_HOMEID)) == cr.getInt(cr.getColumnIndex(DBHelper._ID))) {
                FlatSTR Name = new FlatSTR();
                Name.flat = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_FLAT));
                Name.fire_alarm = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_FIREALARM));
                Name.security = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_SECURITY));
                Name.leak = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_LEAK));
                Name.magnet_field = c.getInt(c.getColumnIndex(DBHelper.FLATS_COLUMN_MAGNETFIELD));

                c.moveToNext();
                arr[i] = Name;
            }
        }
//        for (int i = 0; i < homeid.length; i++){
//            homeid[i] = cr.getInt(cr.getColumnIndex(DBHelper.ADDRESSES_COLUMN_HOME));;
//            cr.moveToNext();
//        }
//
//        for (int i = 0;i<;i++){
//
//        }

            c.close();
            return arr;

    }
}

