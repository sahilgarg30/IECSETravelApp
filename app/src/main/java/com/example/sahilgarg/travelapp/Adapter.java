package com.example.sahilgarg.travelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by sahilgarg on 02/04/18.
 */

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Trip> trips;

    public Adapter(Context context, ArrayList<Trip> trips) {
        this.context = context;
        this.trips = trips;
    }

    @Override
    public int getCount() {
        return trips.size();
    }

    @Override
    public Object getItem(int position) {
        return trips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_item, parent, false);
        }
        TextView mNameTv = (TextView) convertView.findViewById(R.id.name_tv);
        TextView mLocationTv = (TextView) convertView.findViewById(R.id.location_tv);
        TextView mDateTv = (TextView)convertView.findViewById(R.id.date_tv);
        mNameTv.setText(trips.get(position).getName());
        mLocationTv.setText(trips.get(position).getLocation());
        mDateTv.setText(trips.get(position).getDate());
        return convertView;
    }
}

