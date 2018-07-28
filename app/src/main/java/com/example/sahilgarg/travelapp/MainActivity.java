package com.example.sahilgarg.travelapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyHelper mMyHelper;
    private SQLiteDatabase mDb;
    private ArrayList<Trip> mArray;
    private ListView mListView;
    private Adapter tripAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyHelper = new MyHelper(MainActivity.this,"DB",null,1);
        mDb = mMyHelper.getWritableDatabase();
        mListView = (ListView) findViewById(R.id.list1);

        mArray = new ArrayList<Trip>();

        Cursor c = mDb.query("dt",null,null,null,null,null,null);
        while (c.moveToNext()){
            Trip t = new Trip();
            t.setName(c.getString(1));
            t.setLocation(c.getString(2));
            t.setDate(c.getString(3));
            mArray.add(t);
        }

        tripAdapter = new Adapter(MainActivity.this,mArray);
        mListView.setAdapter(tripAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Light_Dialog_NoActionBar);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Remove Trip")
                        .setMessage("Click OK to clear this trip off your list.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mDb.delete("dt","_id = "+i,null);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_delete)
                        .show();
            }
        });
    }

    public void startAddActivity(View view) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
        finish();
    }
}
