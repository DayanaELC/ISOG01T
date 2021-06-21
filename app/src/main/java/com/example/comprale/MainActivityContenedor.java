package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityContenedor extends AppCompatActivity {

    Fragment fragmentHome, fragmentMisProductos, fragmentCarrito, fragmentPerfil;
    Button btnHome;
    Button btnProductos;
    Button btnCarrito;
    Button btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contenedor);

        btnHome=(Button)findViewById(R.id.btnHome);
        btnProductos=(Button)findViewById(R.id.btnProductos);
        btnCarrito=(Button)findViewById(R.id.btnCarrito);
        btnPerfil=(Button)findViewById(R.id.btnPerfil);

        fragmentHome = new FragmentHome();
        fragmentMisProductos = new FragmentMisProductos();
        fragmentCarrito = new FragmentCarrito();
        fragmentPerfil = new FragmentPerfil();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,fragmentHome).commit();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentHome).commit();
            }
        });

        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentCarrito).commit();
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentPerfil).commit();
            }
        });

        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,fragmentMisProductos).commit();
            }
        });
    }
}