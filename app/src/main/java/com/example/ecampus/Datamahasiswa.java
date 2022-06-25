package com.example.ecampus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Datamahasiswa extends AppCompatActivity {

    private ArrayList<Model> biodataList;
    ListView listView;
    ListViewAdapter adapter;
    DataHelper helper;
    public static Datamahasiswa dm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamahasiswa);

        // Back Button in Action Bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Initialization Button Input in List Data
        Button btnInputData = (Button) findViewById(R.id.btnInputData);

        // Button OnClick Listener
        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(Datamahasiswa.this, Inputdata.class);
                startActivity(inte);
            }
        });
        dm = this;
        populateList();
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

    // Function to initialize data from database to model class and insert it into biodataList
    public void populateList(){
        biodataList = new ArrayList<Model>();
        helper = new DataHelper(this);
        adapter = new ListViewAdapter(this, biodataList);
        Model bio;
        Cursor data = helper.getData();
        data.moveToFirst();
        for (int dt = 0; dt < data.getCount(); dt++){
            data.moveToPosition(dt);
            bio = new Model(data.getString(0).toString(), data.getString(1).toString());
            biodataList.add(bio);
        }
        listView = (ListView) findViewById(R.id.list_view); // Get List View
        listView.setAdapter(adapter);
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data nim from user click
                final String selection = ((TextView)view.findViewById(R.id.mNim)).getText().toString();

                // Create dialog item when user click data in lists
                final CharSequence[] dialogitem = {"Lihat Data", "Update Data", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Datamahasiswa.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent detail = new Intent(getApplicationContext(), DetailData.class);
                                detail.putExtra("nim", selection);
                                startActivity(detail);
                                break;
                            case 1:
                                Intent update = new Intent(getApplicationContext(), UpdateData.class);
                                update.putExtra("nim", selection);
                                startActivity(update);
                                break;
                            case 2:
                                helper.deleteOneData(selection);
                                Toast.makeText(Datamahasiswa.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                                populateList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ListViewAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}