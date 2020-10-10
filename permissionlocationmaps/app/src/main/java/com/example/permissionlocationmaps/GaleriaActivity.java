package com.example.permissionlocationmaps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class GaleriaActivity extends AppCompatActivity {

    static final int IMAGE_PICKER_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 2;
    static final int MY_PERMISSION_REQUEST_READ_STORAGE = 0;

    Button btnGaleria, btnCamara;
    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        btnGaleria = findViewById(R.id.btnGaleria);
        btnCamara = findViewById(R.id.btnCamara);
        imgFoto = findViewById(R.id.imgFoto);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                MY_PERMISSION_REQUEST_READ_STORAGE);

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getBaseContext() , Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    Intent pickImage = new Intent(Intent.ACTION_PICK);
                    pickImage.setType("image/*");
                    startActivityForResult(pickImage, IMAGE_PICKER_REQUEST);
                }
            }
        });


        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getBaseContext() , Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                takePicture();
            }
        });
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == IMAGE_PICKER_REQUEST && resultCode == RESULT_OK){
                   try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgFoto.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgFoto.setImageBitmap(imageBitmap);
        }
    }
}