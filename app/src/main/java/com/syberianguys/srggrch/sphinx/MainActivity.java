package com.syberianguys.srggrch.sphinx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_TAG = "MainActivity";
    ListView mList;
    DBHelper DBHelper;
    SQLiteDatabase mDB;
    private boolean dbFlag;

    // имя файла настройки
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_COUNTER = "counter";
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            dbFlag = mSettings.getBoolean(APP_PREFERENCES_COUNTER,false);
        }
        setContentView(R.layout.activity_main);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //=======================================================================================\\

        mList = findViewById(R.id.hList);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicked" + position,
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, FlatListActivity.class);
                intent.putExtra("position", position);
                Log.d(LOG_TAG, "CLICKED");
                startActivity(intent);

            }
        });

        DBHelper = new DBHelper(this, "Sphinx.db", null, 1);
        Log.d(LOG_TAG, "DBHelper");
        mDB = DBHelper.getReadableDatabase();
        Log.d(LOG_TAG, "DB get");
        if(!dbFlag){ dbFlag = true; DBHelper.onDBCreated(mDB);}

        HLAdapter adapter = new HLAdapter(this, makeAddresses());
        mList.setAdapter(adapter);

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_PREFERENCES_COUNTER, dbFlag);
        editor.apply();
    }

    private HouseSTR[] makeAddresses() {
        Cursor c;
        c = mDB.query("addresses", new String[]{com.syberianguys.srggrch.sphinx.DBHelper.ADDRESSES_COLUMN_STREET, com.syberianguys.srggrch.sphinx.DBHelper.ADDRESSES_COLUMN_HOME},
                null, null,
                null, null, null);

        HouseSTR[] house = new HouseSTR[c.getCount()];

        Log.d(LOG_TAG, "mC stared");

        HouseSTR[] arr = new HouseSTR[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < arr.length; i++) {
            HouseSTR Name = new HouseSTR();
            Name.name = c.getString(c.getColumnIndex(com.syberianguys.srggrch.sphinx.DBHelper.ADDRESSES_COLUMN_STREET));
            Name.number = c.getInt(c.getColumnIndex(com.syberianguys.srggrch.sphinx.DBHelper.ADDRESSES_COLUMN_HOME));
            c.moveToNext();
            arr[i] = Name;
        }
        Log.d(LOG_TAG, "List arr done");
        c.close();
        return arr;

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "perf opened");
        if (mSettings.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            dbFlag = mSettings.getBoolean(APP_PREFERENCES_COUNTER,false);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_PREFERENCES_COUNTER, dbFlag);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(APP_PREFERENCES_COUNTER, dbFlag);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_errors) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_refresh) {
            DBHelper.onUpdate(mDB);
            Toast.makeText(this, "DB Updated", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
