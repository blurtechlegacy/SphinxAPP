package com.syberianguys.srggrch.sphinx;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HLAdapter extends ArrayAdapter<HLNames> {
    public HLAdapter( Context context, HLNames[] resource) {
        super(context, R.layout.adapter_house, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final HLNames name = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_house, null);
        }

        // Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.houseName)).setText(name.name);
        return convertView;

    }
}
