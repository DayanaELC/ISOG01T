package com.example.comprale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FragmentCarrito extends Fragment {

    ListView ListaCompas;
    Button btnPagar;

    ArrayList<String> ListaImagen=new ArrayList<>();
    ArrayList<String> ListaNombres=new ArrayList<>();
    ArrayList<Double> ListaPrecios=new ArrayList<>();
    ArrayList<Integer> ListaCantidad=new ArrayList<>();

    ListaComprasAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_carrito, container, false);
        ListaCompas=(ListView)vista.findViewById(R.id.listaCompra);
        btnPagar = (Button)vista.findViewById(R.id.btnPagar);

        CargarLista();

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivitPago.class);
                startActivity(intent);
            }
        });
        return vista;
    }

    public void CargarLista(){
        ListaImagen.clear();
        ListaCantidad.clear();
        ListaPrecios.clear();
        ListaNombres.clear();
        for (int i=0; i<3; i++){
            ListaImagen.add("https://static.miweb.padigital.es/var/m_3/3f/3f5/46132/631340-no-disponible.jpg");
            ListaNombres.add("Nombre del producto " + (i+1));
            ListaPrecios.add(35.56+i);
            ListaCantidad.add(50-i*2);
        }
        adapter = new ListaComprasAdapter(getContext(), ListaImagen);
        ListaCompas.setAdapter(adapter);
    }

    public class ListaComprasAdapter extends BaseAdapter {
        Context contexto;
        List<String> ListaImagenes;

        public ListaComprasAdapter(Context contexto, List<String> listaImagenes) {
            this.contexto = contexto;
            ListaImagenes = listaImagenes;
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
            vista = inflate.inflate(R.layout.lista_compras,null);

            ImageView imgProducto = (ImageView) vista.findViewById(R.id.imgProducto);
            TextView txtNombre = (TextView)vista.findViewById(R.id.txtNombre);
            TextView txtPrecio = (TextView)vista.findViewById(R.id.txtPrecio);
            final TextView txtCantidad = (TextView)vista.findViewById(R.id.cantidad);
            TextView btnEliminar = (TextView)vista.findViewById(R.id.btnEliminar);
            Button btnMas=(Button)vista.findViewById(R.id.btnMas);
            Button btnMenos=(Button)vista.findViewById(R.id.btnMenos);
            Glide.with(imgProducto.getContext()).load(ListaImagenes.get(i)).into(imgProducto);
            txtNombre.setText(ListaNombres.get(i));
            txtPrecio.setText("$"+ListaPrecios.get(i).toString());
            txtCantidad.setText(ListaCantidad.get(i).toString());

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ListaImagenes.remove(i);
                    ListaImagen.remove(i);
                    ListaNombres.remove(i);
                    ListaPrecios.remove(i);
                    ListaCantidad.remove(i);
                    adapter.notifyDataSetChanged();
                }
            });

            btnMenos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Integer.parseInt(txtCantidad.getText().toString())>1){
                        txtCantidad.setText(""+(Integer.parseInt(txtCantidad.getText().toString())-1));
                    }
                }
            });
            btnMas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    txtCantidad.setText(""+(Integer.parseInt(txtCantidad.getText().toString())+1));
                }
            });

            return vista;
        }
    }
}