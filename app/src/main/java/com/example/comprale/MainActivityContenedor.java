package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivityContenedor extends AppCompatActivity {

    Fragment fragmentHome, fragmentMisProductos, fragmentCarrito, fragmentPerfil;
    Button btnHome;
    Button btnProductos;
    Button btnCarrito;
    Button btnPerfil;

    Variables variables = new Variables();

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea salir de la aplicación?");
        builder.setCancelable(false).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivityContenedor.this.finish();
                Intent intent = new Intent(MainActivityContenedor.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}