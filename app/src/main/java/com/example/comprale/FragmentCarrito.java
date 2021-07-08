package com.example.comprale;

import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FragmentCarrito extends Fragment {

    ListView ListaCompas;
    Button btnPagar;
    TextView txtTotal;

    ArrayList<String> ListaImagen=new ArrayList<>();

    Double total=0.0;

    ListaComprasAdapter adapter = null;

    Variables variables = new Variables();

    DecimalFormat df = new DecimalFormat("###,###,##0.00");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_carrito, container, false);
        ListaCompas=(ListView)vista.findViewById(R.id.listaCompra);
        btnPagar = (Button)vista.findViewById(R.id.btnPagar);
        txtTotal = (TextView)vista.findViewById(R.id.txtTotal);

        CargarLista();

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variables.setTotalPagar(total);
                Intent intent = new Intent(getContext(), MainActivitPago.class);
                startActivity(intent);
            }
        });
        return vista;
    }

    public void CargarLista(){
        ListaImagen.clear();
        if (variables.getListaCodigoProducto().isEmpty()){
            Toast.makeText(getContext(), "No hay productos agregados", Toast.LENGTH_SHORT).show();
            btnPagar.setEnabled(false);
        }
        else {
            for (int i=0; i<variables.getListaCodigoProducto().size(); i++){
                ListaImagen.add(variables.getListaImagen().get(i));
                total = total + (Double.parseDouble(variables.getListaPrecioProducto().get(i))*Integer.parseInt(variables.getListaCantidadProducto().get(i)));
            }
            txtTotal.setText("$"+df.format(total));
            adapter = new ListaComprasAdapter(getContext(), ListaImagen);
            ListaCompas.setAdapter(adapter);
        }
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
            Glide.with(imgProducto.getContext()).load(ListaImagenes.get(i)).into(imgProducto);
            txtNombre.setText(variables.getListaNombreProducto().get(i)+"\n\n$"+df.format(Double.parseDouble(variables.getListaPrecioProducto().get(i))));
            txtPrecio.setText("$"+(df.format(Double.parseDouble(variables.getListaPrecioProducto().get(i))*Double.parseDouble(variables.getListaCantidadProducto().get(i)))));
            //txtPrecio.setText("$"+Double.parseDouble(variables.getListaPrecioProducto().get(i))*Double.parseDouble(variables.getListaCantidadProducto().get(i)));
            txtCantidad.setText(variables.getListaCantidadProducto().get(i));

            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ListaImagenes.remove(i);
                    total = total - Double.parseDouble(variables.getListaPrecioProducto().get(i))*Double.parseDouble(variables.getListaCantidadProducto().get(i));
                    variables.setTotalPagar(total);
                    txtTotal.setText("$"+df.format(total));
                    ListaImagen.remove(i);
                    variables.getListaImagen().remove(i);
                    variables.getListaCodigoProducto().remove(i);
                    variables.getListaNombreProducto().remove(i);
                    variables.getListaPrecioProducto().remove(i);
                    variables.getListaCantidadProducto().remove(i);
                    adapter.notifyDataSetChanged();
                }
            });

            return vista;
        }
    }
}