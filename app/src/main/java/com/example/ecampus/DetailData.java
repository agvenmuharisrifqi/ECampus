package com.example.ecampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {

    DataHelper helper;
    TextView nim, nama, tgl, jk, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        // Back Button in Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initialization
        helper = new DataHelper(this);
        nim = (TextView) findViewById(R.id.textNim);
        nama = (TextView) findViewById(R.id.textNama);
        tgl = (TextView) findViewById(R.id.textTgl);
        jk = (TextView) findViewById(R.id.textGender);
        alamat = (TextView) findViewById(R.id.textAlamat);

        // Get data from database with key nim
        Cursor data = helper.getOneData(getIntent().getStringExtra("nim"));
        data.moveToFirst();
        if (data.getCount() > 0){
            data.moveToPosition(0);

            // Set Value Text View With Data From Database
            nim.setText(data.getString(0).toString());
            nama.setText(data.getString(1).toString());
            tgl.setText(data.getString(2).toString());
            jk.setText(data.getString(3).toString());
            alamat.setText(data.getString(4).toString());
        }
    }

    // Function For Back Button in Action Bar
    @Override
    public  boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}