package com.example.comprale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListaComprasAdapter extends BaseAdapter {
    Context contexto;
    List<String> ListaImagenes;
    List<String> ListaNombres;
    List<Double> ListaPrecios;
    List<String> ListaInfo;

    Variables variables = new Variables();

    public ListaComprasAdapter(Context contexto, List<String> listaImagenes, List<String> listaNombres, List<Double> listaPrecios, List<String> listaInfo) {
        this.contexto = contexto;
        ListaImagenes = listaImagenes;
        ListaNombres = listaNombres;
        ListaPrecios = listaPrecios;
        ListaInfo = listaInfo;
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
        vista = inflate.inflate(R.layout.lista_productos,null);

        ImageView imgProducto = (ImageView) vista.findViewById(R.id.imgProducto);
        TextView txtNombre = (TextView)vista.findViewById(R.id.txtNombre);
        TextView txtPrecio = (TextView)vista.findViewById(R.id.txtPrecio);
        TextView txtInfo = (TextView)vista.findViewById(R.id.txtInfo);
        Glide.with(imgProducto.getContext()).load(ListaImagenes.get(i)).into(imgProducto);
        txtNombre.setText(ListaNombres.get(i));
        txtPrecio.setText("$"+ListaPrecios.get(i));
        txtInfo.setText(ListaInfo.get(i));

        return vista;
    }
}
