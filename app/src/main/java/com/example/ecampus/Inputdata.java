package com.example.ecampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Inputdata extends AppCompatActivity {

    Button simpan;
    EditText nim, nama, tgl, jk, alamat;
    String dataNim, dataNama, dataTgl, dataJk, dataAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        final DataHelper helper = new DataHelper(this);

        // Get Element Input From View
        nim = (EditText) findViewById(R.id.inputNim);
        nama = (EditText) findViewById(R.id.inputNama);
        tgl = (EditText) findViewById(R.id.inputDate);
        jk = (EditText) findViewById(R.id.inputGender);
        alamat = (EditText) findViewById(R.id.inputAddress);

        // Get Button Save From View
        simpan = (Button)findViewById(R.id.btnSaveData);
        // Create Button Save On Click Listener
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Data From Element Input
                dataNim = nim.getText().toString();
                dataNama = nama.getText().toString();
                dataTgl = tgl.getText().toString();
                dataJk = jk.getText().toString();
                dataAlamat = alamat.getText().toString();
                if (!dataNim.isEmpty() && !dataNama.isEmpty() && !dataTgl.isEmpty() && !dataJk.isEmpty() && !dataAlamat.isEmpty()) {
                    if (helper.insertData(dataNim, dataNama, dataTgl, dataJk, dataAlamat)) {
                        Toast.makeText(Inputdata.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                        nim.setText("");
                        nama.setText("");
                        tgl.setText("");
                        jk.setText("");
                        alamat.setText("");
                        Datamahasiswa.dm.populateList();
                    } else {
                        Toast.makeText(Inputdata.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Inputdata.this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Back Button in Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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