package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Mostrar_Datos_Contacto extends AppCompatActivity {

    private TextView tvtxt_id, tvtxt_name, tvtxt_phone, tvtxt_mail;
    private Button btn_delete;
    private SQLiteOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_contacto);
        tvtxt_id = findViewById(R.id.tv_id);
        tvtxt_name = findViewById(R.id.tv_nomb);
        tvtxt_phone = findViewById(R.id.tv_movil);
        tvtxt_mail = findViewById(R.id.tv_mail);
        btn_delete = findViewById(R.id.bt_eliminar);

        Contacto contact = (Contacto) getIntent().getSerializableExtra("DATO_CONTACTO");

        tvtxt_id.setText("ID: " + contact.getId());
        tvtxt_name.setText("Nombre: " + contact.getNombre());
        tvtxt_phone.setText("Movil: " + contact.getMovil());
        tvtxt_mail.setText("Email: " + contact.getEmail());




    }
    @SuppressLint("Range")
    public void eliminarContacto () {
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        Contacto contact = null;
        Cursor cursorContacto = null;

        cursorContacto = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CLIENTES, null);
        if (cursorContacto.moveToFirst()) {
            do {
                contact = new Contacto();
                contact.setId(cursorContacto.getInt(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                contact.setNombre(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE)));
                contact.setMovil(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_MOVIL)));
                contact.setEmail(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));

                listaContactos.add(contact);
            } while (cursorContacto.moveToNext());
        }
    }
}