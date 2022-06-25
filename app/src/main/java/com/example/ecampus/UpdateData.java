package com.example.ecampus;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    DataHelper helper;
    EditText nim, nama, tgl, jk, alamat;
    Button simpan;
    String dataNim, dataNama, dataTgl, dataJk, dataAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        // Back Button in Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initialization
        helper = new DataHelper(this);
        nim = (EditText) findViewById(R.id.inputNim);
        nama = (EditText) findViewById(R.id.inputNama);
        tgl = (EditText) findViewById(R.id.inputDate);
        jk = (EditText) findViewById(R.id.inputGender);
        alamat = (EditText) findViewById(R.id.inputAddress);

        // Set NIM Can't Be Edited
        nim.setEnabled(false);

        // Get Data From Database
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
                    if (helper.updateData(dataNim, dataNama, dataTgl, dataJk, dataAlamat)) {
                        Toast.makeText(UpdateData.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                        Datamahasiswa.dm.populateList();
                    } else {
                        Toast.makeText(UpdateData.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(UpdateData.this, "Data tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
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