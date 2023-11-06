package com.example.agendapersonalpersistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.Serializable;

//Se implementa el evento de la selección del Spinner
public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    private Button botonInsertar;
    private Button botonListado;
    private Spinner spinner;

    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         layout = findViewById(R.id.llout);
         botonInsertar =(Button) findViewById(R.id.btinsertar);
         botonListado = (Button) findViewById(R.id.btlistado);
         spinner = (Spinner) findViewById(R.id.spinnercolor);
        String [] colores = {"Azul claro", "Amarillo", "Verde", "Violeta"};

        //Se crea un adaptador de array para poder usar el spinner con su layout
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_color, android.R.layout.simple_spinner_item);
        //Se especifica con el objeto arrayAdapter la lista porporcionada para el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Se implementa el adaptador al Spinner
        spinner.setAdapter(adapter);


        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaCrearContacto = new Intent(MainActivity.this, Insertar_Contacto.class);
               startActivity(pantallaCrearContacto);
            }
        });

        botonListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallaListado = new Intent(MainActivity.this, Lista_Contacto.class);
                startActivity(pantallaListado);
            }
        });

    }

    //Método para la llamada del Spinner y seleccionar la opción que va a ser elegida
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        SharedPreferences preferencias = getSharedPreferences("colores_spinner", Context.MODE_PRIVATE);
        int colorPrincipal = preferencias.getInt("color_proporcionado", 0);
        //int obtenerColores ;
        //obtenerColores = (int) jspinner.getSelectedItem();
        switch (position) {
            case 0:
                layout.setBackgroundColor(Color.WHITE);
                colorPrincipal = preferencias.getInt("color_proporcionado", position);
                break;
            case 1:
                spinner.getSelectedItem().equals("Azul claro");
                layout.setBackgroundColor(Color.parseColor("#00FFFF"));
                break;
            case 2:
                spinner.getSelectedItem().equals("Amarillo");
                layout.setBackgroundColor(Color.parseColor("#EBFF33"));
                break;
            case 3:
                spinner.getSelectedItem().equals("Verde");
                layout.setBackgroundColor(Color.parseColor("#33FF84"));
                break;
            case 4:
                spinner.getSelectedItem().equals("Violeta");
                layout.setBackgroundColor(Color.parseColor("#EE82EE"));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}