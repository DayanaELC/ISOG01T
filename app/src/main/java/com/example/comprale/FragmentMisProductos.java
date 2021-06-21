package com.example.comprale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentMisProductos extends Fragment {

    ListView ListaProductos;
    FloatingActionButton NuevoProducto;
    ArrayList<String> ListaImagen=new ArrayList<>();
    ArrayList<String> ListaNombres=new ArrayList<>();
    ArrayList<Double> ListaPrecios=new ArrayList<>();
    ArrayList<String> ListaInfo=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_mis_productos, container, false);

        ListaProductos = (ListView) vista.findViewById(R.id.listaProductos);
        NuevoProducto=(FloatingActionButton)vista.findViewById(R.id.btnNuevoProducto);

        NuevoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivityNuevoProducto.class);
                startActivity(intent);
            }
        });
        CargarLista();

        return vista;
    }

    public void CargarLista(){
        for (int i=0; i<5; i++){
            ListaImagen.add("https://static.miweb.padigital.es/var/m_3/3f/3f5/46132/631340-no-disponible.jpg");
            ListaNombres.add("Nombre del producto " + (i+1));
            ListaPrecios.add(35.56+i);
            ListaInfo.add(i*2 + " solicitudes");
        }
        ListaComprasAdapter adapter = new ListaComprasAdapter(getContext(), ListaImagen, ListaNombres, ListaPrecios, ListaInfo);
        ListaProductos.setAdapter(adapter);
    }
}