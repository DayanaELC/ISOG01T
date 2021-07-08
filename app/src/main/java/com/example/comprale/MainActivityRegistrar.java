package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivityRegistrar extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText Nombre, Email, Clave, Confirmacion, Direccion;
    Button btnRegistrar;

    Variables variables=new Variables();

    ArrayList<String> Correos=new ArrayList<>();

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registrar);

        Nombre=(EditText)findViewById(R.id.Nombre);
        Clave=(EditText)findViewById(R.id.Clave);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        Confirmacion=(EditText)findViewById(R.id.ClaveConfirmacion);
        Email=(EditText)findViewById(R.id.Email);
        Direccion=(EditText)findViewById(R.id.direccion);

        rq = Volley.newRequestQueue(this);

        CargarCorreos();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Nombre.getText().toString().isEmpty() || Clave.getText().toString().isEmpty() || Email.getText().toString().isEmpty()||Direccion.getText().toString().isEmpty()){
                    Toast.makeText(MainActivityRegistrar.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if (!Clave.getText().toString().contentEquals(Confirmacion.getText().toString())){
                    Toast.makeText(MainActivityRegistrar.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else if (!Email.getText().toString().contains("@") && !Email.getText().toString().contains(".")){
                    Toast.makeText(MainActivityRegistrar.this, "Digite un correo válido", Toast.LENGTH_SHORT).show();
                }
                else{
                    EnviarDatos("https://www.pimentelycampos.com/comprale/php/registro.php?nombre="+Nombre.getText().toString().replace(" ","%20")+
                            "&email="+Email.getText().toString()+"&clave="+Clave.getText().toString()+"&direccion="+Direccion.getText().toString().replace(" ","%20"));
                }
            }
        });
    }

    public void CargarCorreos(){
        String url ="https://www.pimentelycampos.com/comprale/php/correos.php";
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }


    private void EnviarDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("usuario").contentEquals("1")){
                        Toast.makeText(MainActivityRegistrar.this, "Este usuario ya ha sido registrado", Toast.LENGTH_SHORT).show();
                    }else {
                        variables.setLoginEmail(Email.getText().toString());
                        variables.setLoginUser(Nombre.getText().toString());
                        variables.setLoginPass(Clave.getText().toString());
                        variables.setLoginDir(Direccion.getText().toString());
                        Intent intent = new Intent(MainActivityRegistrar.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivityRegistrar.this, "Gracias por unirte, " + Nombre.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityRegistrar.this, "Error al registrarse\n"+error,Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    @Override
    public void onResponse(JSONObject response) {
        Correos.clear();
        JSONArray jsonArray = response.optJSONArray("correos");
        JSONObject jsonObject = null;
        try {
            for (int i=0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                Correos.add(jsonObject.getString("email"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}