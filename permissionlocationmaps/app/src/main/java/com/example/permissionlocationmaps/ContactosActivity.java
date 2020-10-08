package com.example.permissionlocationmaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.Toast;

public class ContactosActivity extends AppCompatActivity {

    String[] columnas;
    Cursor cursor;
    ContactosAdapter adaptador;
    ListView listaContactos;

   static final int READ_CONTACTS_ID = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        listaContactos = findViewById(R.id.listaContactos);
        columnas = new String[]
                {ContactsContract.Profile._ID,
                 ContactsContract.Profile.DISPLAY_NAME_PRIMARY};
        adaptador = new ContactosAdapter(this, null, 0);
        listaContactos.setAdapter(adaptador);
        solicitarPermiso(this, Manifest.permission.READ_CONTACTS, "Necesito los contactos porfi", READ_CONTACTS_ID);
        usarPermiso();
    }

    private void usarPermiso(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED) {
            cursor=getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    columnas, null, null, null);

            adaptador.changeCursor(cursor);
        }
    }

    private void solicitarPermiso(Activity context, String permiso, String justificacion, int idPermiso){
        if (ContextCompat.checkSelfPermission(context ,permiso)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(context, permiso)) {
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