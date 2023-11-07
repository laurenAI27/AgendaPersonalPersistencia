package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar_Contacto extends AppCompatActivity {

   private EditText etxt_name, etxt_mobile, etxt_mail;
   private Button bt_insert;
    private SQLiteOpenHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_contacto);

        //Se les asocia el id de los objetos de la aplicacion
        etxt_name = findViewById(R.id.etname);
        etxt_mobile = findViewById(R.id.etmobile);
        etxt_mail = findViewById(R.id.etmail);
        bt_insert = findViewById(R.id.btinsert);


        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insertar();
            }
        });

    }

    //Método para insertar los contactos mediante la base de datos
    public void Insertar(){

         dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Declaracion de las variables para recuperar la información
        String nombre = etxt_name.getText().toString();
        String movil = etxt_mobile.getText().toString();
        String email = etxt_mail.getText().toString();

        //Condicion para los campos para rellenar los editText
        if(!nombre.isEmpty() && !movil.isEmpty()){
            ContentValues insertado = new ContentValues();
            //Se guarda en la base de datos los nombres proporcinados por el usuario
            insertado.put(DatabaseHelper.COLUMN_NOMBRE, nombre);
            insertado.put(DatabaseHelper.COLUMN_MOVIL, movil);
            insertado.put(DatabaseHelper.COLUMN_EMAIL, email);
            db.insert(DatabaseHelper.TABLE_CLIENTES, null, insertado);
            db.close();

            etxt_name.setText("");
            etxt_mobile.setText("");
            etxt_mail.setText("");
            Toast.makeText(this, "Contacto insertado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No ha sido insertado", Toast.LENGTH_SHORT).show();
        }

    }

    public void Buscar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

        String nombre = etxt_name.getText().toString();

        if(!nombre.isEmpty()){
            Cursor fila = baseDeDatos.rawQuery("select movil, email from tabla_clientes where nombre =" + nombre, null);
                if(fila.moveToFirst()){
                    etxt_mobile.setText(fila.getString(0));
                    etxt_mail.setText(fila.getString(1));
                    baseDeDatos.close();
                } else {
                    Toast.makeText(this, "No existe el contacto", Toast.LENGTH_SHORT).show();
                    baseDeDatos.close();
                }
        } else {
            Toast.makeText(this, "Tiene que introducir el nombre", Toast.LENGTH_SHORT).show();
        }
    }
}