package com.example.comprale;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    Button btnBuscar;
    Button btnTodo;
    EditText txtBuscar;

    RequestQueue rq;
    JsonRequest jrq;

    GridView Productos;
    Variables variables = new Variables();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista =  inflater.inflate(R.layout.fragment_home, container, false);
        Productos = (GridView)vista.findViewById(R.id.gridProductos);
        btnBuscar = (Button)vista.findViewById(R.id.btnBuscar);
        btnTodo = (Button)vista.findViewById(R.id.btnTodo);
        txtBuscar = (EditText)vista.findViewById(R.id.txtBuscar);
        rq = Volley.newRequestQueue(getContext());

        CargarGrid();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarGrid();
            }
        });
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtBuscar.setText("");
                CargarGrid();
            }
        });

        return vista;

    }

    public void CargarGrid(){
        variables.getImagenesHome().clear();
        variables.getImagenesIdHome().clear();
        String url ="https://www.pimentelycampos.com/comprale/php/productos.php?producto="+txtBuscar.getText().toString().replace(" ", "%20");
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("productos");
        JSONObject jsonObject = null;
        try {
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                variables.getImagenesIdHome().add(jsonObject.getInt("id_producto"));
                variables.getImagenesHome().add(jsonObject.getString("imagen"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GridAdapter adapter = new GridAdapter(getContext(), variables.getImagenesHome(), variables.getImagenesIdHome());
        Productos.setAdapter(adapter);

    }

}