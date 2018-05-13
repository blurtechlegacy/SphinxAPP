package com.syberianguys.srggrch.sphinx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_list);
        int pos = getIntent().getIntExtra("position", 0);



    }
}
