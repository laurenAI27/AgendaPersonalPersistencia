package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista_Contacto extends AppCompatActivity {

    private SQLiteOpenHelper dbHelper;
    private LinearLayout listaContenedor;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contacto);

        listaContenedor = findViewById(R.id.llout_listado);


        mostrarContactos();


        for (int i = 0; i < mostrarContactos().size(); i++){
            Contacto contact = mostrarContactos().get(i);
            Button botonContacto = new Button(this);
            botonContacto.setText(contact.getNombre());
            botonContacto.setTextSize(30);

            Contacto finalContact = contact;
            botonContacto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pantallaDatoContacto = new Intent(Lista_Contacto.this, Mostrar_Datos_Contacto.class);
                    pantallaDatoContacto.putExtra("DATO_CONTACTO",  finalContact);
                    startActivity(pantallaDatoContacto);
                }
            });
            listaContenedor.addView(botonContacto);
        }


    }

    //Metodo para consultar un contacto
    @SuppressLint("Range")
    public ArrayList<Contacto> mostrarContactos(){
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Contacto> listaContactos = new ArrayList<>();
        Contacto contact = null;
        Cursor cursorContacto = null;

        cursorContacto = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CLIENTES, null);
        if(cursorContacto.moveToFirst()){
            do{
                contact = new Contacto();
                contact.setId(cursorContacto.getInt(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                contact.setNombre(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE)));
                contact.setMovil(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_MOVIL)));
                contact.setEmail(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));

                listaContactos.add(contact);
            }while (cursorContacto.moveToNext());
        }

        cursorContacto.close();
        return listaContactos;
    }
}