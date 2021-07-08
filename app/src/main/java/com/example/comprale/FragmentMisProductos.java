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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentMisProductos extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    ListView ListaProductos;
    FloatingActionButton NuevoProducto;

    Variables variables = new Variables();

    ListaComprasAdapter adapter = null;

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_mis_productos, container, false);

        rq = Volley.newRequestQueue(getContext());

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
        variables.getListaImagenNuevo().clear();
        variables.getListaNombresNuevo().clear();
        variables.getListaPreciosNuevo().clear();
        variables.getListaInfoNuevo().clear();
        String url ="https://www.pimentelycampos.com/comprale/php/misproductos.php?user="+variables.getLoginId();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), "No hay productos para mostrar", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("misproductos");
        JSONObject jsonObject = null;
        try {
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                variables.getListaImagenNuevo().add(jsonObject.getString("imagen"));
                variables.getListaNombresNuevo().add(jsonObject.getString("producto"));
                variables.getListaPreciosNuevo().add(jsonObject.getDouble("precio"));
                variables.getListaInfoNuevo().add("0 solicitudes");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new ListaComprasAdapter(getContext(), variables.getListaImagenNuevo(), variables.getListaNombresNuevo(), variables.getListaPreciosNuevo(), variables.getListaInfoNuevo());
        ListaProductos.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

}