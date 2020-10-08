package com.example.permissionlocationmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ContactosActivity extends AppCompatActivity {

   static final int READ_CONTACTS_ID = 0;
   TextView estadoPermiso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        estadoPermiso = findViewById(R.id.estadoPermiso);

        solicitarPermiso(this, Manifest.permission.READ_CONTACTS, "Necesito leer los contactos porfi", READ_CONTACTS_ID);
        usarPermiso();
    }

    private void usarPermiso(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) {
            //Aqui va el codigo con riesgo
            estadoPermiso.setText("Permiso concedido");
            estadoPermiso.setTextColor(Color.GREEN);
        } else {
            estadoPermiso.setText("Permiso rechazaco");
            estadoPermiso.setTextColor(Color.RED);
        }
    }

    private void solicitarPermiso(Activity context, String permiso, String justificacion, int idPermiso){
        if (ContextCompat.checkSelfPermission(context ,permiso)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, permiso)) {
                Toast.makeText(this, justificacion, Toast.LENGTH_LONG).show();
            }

            ActivityCompat.requestPermissions(context, new String[]{permiso}, idPermiso);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int [] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case READ_CONTACTS_ID:
            {
                usarPermiso();
            }
        }
    }

}