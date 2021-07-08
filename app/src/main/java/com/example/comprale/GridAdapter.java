package com.example.comprale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    Context contexto;
    List<String> ListaImagenes;
    List<Integer> ListaId;

    Variables variables = new Variables();

    public GridAdapter(Context contexto, List<String> listaImagenes, List<Integer> listaId) {
        this.contexto = contexto;
        ListaImagenes = listaImagenes;
        ListaId = listaId;
    }

    @Override
    public int getCount() {
        return ListaImagenes.size();
    }

    @Override
    public Object getItem(int i) {
        return ListaImagenes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View vista = view;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.grid_item,null);

        ImageView Item = (ImageView) vista.findViewById(R.id.item);
        Glide.with(Item.getContext()).load(ListaImagenes.get(i)).into(Item);

        Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variables.setItemGridProductos(ListaId.get(i));
                Intent intent = new Intent(contexto, MainActivityDetalleProducto.class);
                contexto.startActivity(intent);
            }
        });
        return vista;
    }
}
