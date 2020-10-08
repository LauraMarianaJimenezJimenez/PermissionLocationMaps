package com.example.permissionlocationmaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton imagebtnCamara, imagebtnContactos, imagebtnMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagebtnCamara = findViewById(R.id.imagebtnCamara);
        imagebtnContactos = findViewById(R.id.imagebtnContacts);
        imagebtnMapa = findViewById(R.id.imagebtnMapa);

        imagebtnContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ContactosActivity.class);
                startActivity(intent);
            }
        });

    }
}