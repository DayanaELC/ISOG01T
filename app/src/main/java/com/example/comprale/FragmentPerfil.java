package com.example.comprale;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

public class FragmentPerfil extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    Button btnClave, btnGuardar;
    EditText Nombre, Email, Clave, Direccion;

    Variables variables = new Variables();

    RequestQueue rq;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vista =  inflater.inflate(R.layout.fragment_perfil, container, false);

        rq = Volley.newRequestQueue(getContext());

        btnClave=(Button)vista.findViewById(R.id.btnCambiarClave);
        btnGuardar=(Button)vista.findViewById(R.id.btnGuardar);

        Nombre=(EditText)vista.findViewById(R.id.Nombre);
        Email=(EditText)vista.findViewById(R.id.Email);
        Clave=(EditText)vista.findViewById(R.id.Clave);
        Direccion=(EditText)vista.findViewById(R.id.Dirección);

        Clave.setEnabled(false);

        Nombre.setText(variables.getLoginUser());
        Clave.setText(variables.getLoginPass());
        Direccion.setText(variables.getLoginDir());
        Email.setText(variables.getLoginEmail());

        btnClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Clave.isEnabled()){
                    Clave.setEnabled(false);
                } else {
                    Clave.setEnabled(true);
                }
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getText().toString().isEmpty()||Clave.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Debe tener correo y contraseña", Toast.LENGTH_SHORT).show();
                } else if (!Email.getText().toString().contains("@") && !Email.getText().toString().contains(".")){
                    Toast.makeText(getContext(), "Digite un correo válido", Toast.LENGTH_SHORT).show();
                } else {
                    EnviarDatos("https://www.pimentelycampos.com/comprale/php/editar.php?user="+variables.getLoginId()+"&clave="+Clave.getText().toString()+
                            "&direccion="+Direccion.getText().toString().replace(" ", "%20")+"&email="+Email.getText().toString()+"&nombre="+Nombre.getText().toString().replace(" ", "%20"));
                }
            }
        });

        return vista;
    }

    private void EnviarDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                variables.setLoginPass(Clave.getText().toString());
                variables.setLoginUser(Nombre.getText().toString());
                variables.setLoginEmail(Email.getText().toString());
                variables.setLoginDir(Direccion.getText().toString());
                Toast.makeText(getContext(), "Perfil editado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error al editar\n"+error,Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {


    }
}