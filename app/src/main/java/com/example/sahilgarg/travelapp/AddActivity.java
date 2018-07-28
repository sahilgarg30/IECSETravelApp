package com.example.sahilgarg.travelapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private MyHelper mMyHelper;
    private SQLiteDatabase mDb;
    private EditText mNameEt;
    private EditText mLocEt;
    private EditText mTimeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mMyHelper = new MyHelper(AddActivity.this,"DB",null,1);
        mDb = mMyHelper.getWritableDatabase();
        mNameEt = (EditText) findViewById(R.id.nameTrip_add_et);
        mLocEt = (EditText) findViewById(R.id.locTrip_add_et);
        mTimeEt =(EditText) findViewById(R.id.date_add_et);
    }

    public void addTrip(View view) {
        ContentValues cv = new ContentValues();
        cv.put("name",mNameEt.getText().toString());
        cv.put("location",mLocEt.getText().toString());
        cv.put("date",mTimeEt.getText().toString());
        mDb.insert("dt",null,cv);
        Toast.makeText(this, "Trip added successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
