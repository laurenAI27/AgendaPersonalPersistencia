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
public class MainActivity extends AppCompatActivity {

    private Button botonInsertar;
    private Button botonListado;
    private Spinner spinner;
    private LinearLayout layout;
    private SharedPreferences lastColour;
    private SharedPreferences.Editor controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Pantalla inicial");

        lastColour = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        controlador = lastColour.edit();
        final  int escogido = lastColour.getInt("Selecionado", 0);
         layout = findViewById(R.id.llout);
         botonInsertar =(Button) findViewById(R.id.btinsertar);
         botonListado = (Button) findViewById(R.id.btlistado);
         spinner = (Spinner) findViewById(R.id.spinnercolor);


        //Se crea un adaptador de array para poder usar el spinner con su layout
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_color, android.R.layout.simple_spinner_item);
        //Se especifica con el objeto arrayAdapter la lista porporcionada para el Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Se implementa el adaptador al Spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(escogido);


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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //Método para la llamada del Spinner y seleccionar la opción que va a ser elegida
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                controlador.putInt("Selecionado", position).commit();
                switch (position){
                    case 0:
                        layout.setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        layout.setBackgroundColor(Color.WHITE);
                        break;
                    case 2:
                        layout.setBackgroundColor(Color.parseColor("#33f0ff"));
                        break;
                    case 3:
                        layout.setBackgroundColor(Color.parseColor("#ffec33"));
                        break;
                    case 4:
                        layout.setBackgroundColor(Color.parseColor("#93ff33"));
                        break;
                    case 5:
                        layout.setBackgroundColor(Color.parseColor("#bc80e3"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





}