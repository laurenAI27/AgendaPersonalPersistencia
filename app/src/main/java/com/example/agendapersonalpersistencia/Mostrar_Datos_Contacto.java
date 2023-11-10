package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Mostrar_Datos_Contacto extends AppCompatActivity {

    private TextView tvtxt_id, tvtxt_name, tvtxt_phone, tvtxt_mail;
    private Button btn_delete;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Pantalla inicial");

        setContentView(R.layout.activity_mostrar_datos_contacto);
        tvtxt_id = findViewById(R.id.tv_id);
        tvtxt_name = findViewById(R.id.tv_nomb);
        tvtxt_phone = findViewById(R.id.tv_movil);
        tvtxt_mail = findViewById(R.id.tv_mail);
        btn_delete = findViewById(R.id.bt_eliminar);

        Contacto contact = (Contacto) getIntent().getSerializableExtra("DATO_CONTACTO");

        tvtxt_id.setText("   ID: " + contact.getId());
        tvtxt_name.setText("   Nombre: " + contact.getNombre());
        tvtxt_phone.setText("   Movil: " + contact.getMovil());
        tvtxt_mail.setText("   Email: " + contact.getEmail());


        dbHelper = new DatabaseHelper(this);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                
                db.delete(DatabaseHelper.TABLE_CLIENTES, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(contact.getId())});

                Intent intent = new Intent(Mostrar_Datos_Contacto.this, Lista_Contacto.class);
                startActivity(intent);


            }
        });

    }





}