package com.example.comprale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentPerfil extends Fragment {

    Button btnClave;
    EditText Nombre, Email, Clave, Direccion;

    Variables variables = new Variables();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vista =  inflater.inflate(R.layout.fragment_perfil, container, false);

        btnClave=(Button)vista.findViewById(R.id.btnCambiarClave);
        Nombre=(EditText)vista.findViewById(R.id.Nombre);
        Email=(EditText)vista.findViewById(R.id.Email);
        Clave=(EditText)vista.findViewById(R.id.Clave);
        Direccion=(EditText)vista.findViewById(R.id.Dirección);

        Direccion.setText("Las Margaritas. Calle 1567, psj J, Casa #1, Soyapango, San Salvador");
        Nombre.setText(variables.getLoginUser());
        Clave.setText(variables.getLoginPass());
        Email.setText("estudiante@udb.com.edu.sv");

        btnClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Contraseña cambiada", Toast.LENGTH_SHORT).show();
                variables.setLoginPass(Clave.getText().toString());
                variables.setLoginUser(Nombre.getText().toString());
            }
        });

        return vista;
    }
}