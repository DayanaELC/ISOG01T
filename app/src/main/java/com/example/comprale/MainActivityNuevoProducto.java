package com.example.comprale;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class MainActivityNuevoProducto extends AppCompatActivity {

    EditText Nombre, Precio, Descripcion, Existencia;
    Button btnCancelar, btnAgregar;
    ImageView imgProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo_producto);

        Nombre = (EditText)findViewById(R.id.Nombre);
        Precio = (EditText)findViewById(R.id.Precio);
        Existencia = (EditText)findViewById(R.id.Existencias);
        Descripcion = (EditText)findViewById(R.id.Descripcion);

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);

        imgProducto = (ImageView)findViewById(R.id.imgProducto);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Nombre.getText().toString().isEmpty() || Precio.getText().toString().isEmpty() || Descripcion.getText().toString().isEmpty() || Existencia.getText().toString().isEmpty()){
                    Toast.makeText(MainActivityNuevoProducto.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivityNuevoProducto.this, "Producto registrado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });
    }
}