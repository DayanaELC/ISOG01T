package com.example.comprale;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    ArrayList<String> ListaImagenes=new ArrayList<>();

    GridView Productos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_home, container, false);
        Productos = (GridView)vista.findViewById(R.id.gridProductos);
        CargarGrid();
        return vista;
    }

    public void CargarGrid(){
        for (int i=0; i<11; i++){
            ListaImagenes.add("https://static.miweb.padigital.es/var/m_3/3f/3f5/46132/631340-no-disponible.jpg");
        }
        GridAdapter adapter = new GridAdapter(getContext(), ListaImagenes);
        Productos.setAdapter(adapter);
    }

}