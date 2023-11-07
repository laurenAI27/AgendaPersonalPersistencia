package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    private int id;
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



        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 0;
                if(eliminarContacto(id)){
                 lista();
                }

            }
        });

    }
    @SuppressLint("Range")
    public boolean eliminarContacto (int id) {
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        boolean eliminado = false;
      /*  ArrayList<Contacto> listaContactos = new ArrayList<>();
        Contacto contact = null;
        Cursor cursorContacto = null;*/
        try {
            /**/int rowsDeleted = db.delete(DatabaseHelper.TABLE_CLIENTES, DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
              db.execSQL("DELETE FROM " + DatabaseHelper.TABLE_CLIENTES, new String[]{"WHERE id = " + id + "LIMIT 1"});
            eliminado = true;
            Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            exception.toString();
            eliminado = false;
            Toast.makeText(this, "Contacto no se ha eliminado", Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        /*cursorContacto = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_CLIENTES, null);
        if (cursorContacto.moveToFirst()) {
            do {
                contact = new Contacto();
                contact.setId(cursorContacto.getInt(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                contact.setNombre(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE)));
                contact.setMovil(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_MOVIL)));
                contact.setEmail(cursorContacto.getString(cursorContacto.getColumnIndex(DatabaseHelper.COLUMN_EMAIL)));

                listaContactos.remove(contact);
            } while (cursorContacto.moveToNext());*/
        }
        return eliminado;
    }

    private void lista(){
        Intent intent = new Intent(this, Lista_Contacto.class);
        startActivity(intent);
    }
}