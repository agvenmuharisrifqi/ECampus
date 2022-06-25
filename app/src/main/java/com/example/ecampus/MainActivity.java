package com.example.ecampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create instance Button
        Button btnRead = (Button) findViewById(R.id.btnRead); // Get Button Read From Layout XML
        Button btnInput = (Button) findViewById(R.id.btnInput); // Get Button Input From Layout XML
        Button btnInfo = (Button) findViewById(R.id.btnInfo); // Get Button Info From Layout XML

        // Create OnClick Listener
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainActivity.this, Datamahasiswa.class);
                startActivity(inte);
            }
        }); // OnClick For Button Read

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainActivity.this, Inputdata.class);
                startActivity(inte);
            }
        }); // OnClick For Button Read

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = new Intent(MainActivity.this, Informasi.class);
                startActivity(inte);
            }
        }); // OnClick For Button Read
    }
}