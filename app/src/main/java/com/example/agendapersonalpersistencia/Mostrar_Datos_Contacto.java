package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mostrar_Datos_Contacto extends AppCompatActivity {

    private EditText etxt_id, etxt_name, etxt_phone, etxt_mail;
    private Button btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos_contacto);
        etxt_id = findViewById(R.id.et_id);
        etxt_name = findViewById(R.id.et_nomb);
        etxt_phone = findViewById(R.id.et_movil);
        etxt_mail = findViewById(R.id.et_mail);
        btn_delete = findViewById(R.id.bt_eliminar);

        Contacto contact = (Contacto) getIntent().getSerializableExtra("CONTACTO");

        etxt_id.setText(contact.getId());
        etxt_name.setText( contact.getNombre());
        etxt_phone.setText( contact.getMovil());
        etxt_mail.setText( contact.getEmail());
    }
}