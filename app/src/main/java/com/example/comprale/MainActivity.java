package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    TextView Registrar;
    Button btnEntrar;
    EditText Clave;
    EditText Usuario;

    Variables variables = new Variables();

    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registrar=(TextView)findViewById(R.id.registrar);
        Registrar.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        Clave=(EditText)findViewById(R.id.Clave);
        Usuario=(EditText)findViewById(R.id.Usuario);

        rq = Volley.newRequestQueue(this);

        Usuario.setText(variables.getLoginEmail());
        Clave.setText(variables.getLoginPass());

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarInformacion();
            }
        });

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityRegistrar.class);
                startActivity(intent);
            }
        });

    }

    public void CargarInformacion(){
        String url ="https://www.pimentelycampos.com/comprale/php/inicio.php?email="+Usuario.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this, "Correo y contraseña incorrectos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("usuario");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            variables.setLoginUser(jsonObject.getString("nombre"));
            variables.setLoginPass(jsonObject.getString("clave"));
            variables.setLoginEmail(jsonObject.getString("email"));
            variables.setLoginId(jsonObject.getString("id_usuario"));
            variables.setLoginDir(jsonObject.getString("direccion"));
            //variables.setLoginImagen(jsonObject.getString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        iniciar();
    }

    public void iniciar(){
        if (Clave.getText().toString().contentEquals(variables.getLoginPass())){
            Intent intent = new Intent(MainActivity.this, MainActivityContenedor.class);
            startActivity(intent);
            this.finish();
        }
        else {
            Toast.makeText(MainActivity.this, "Correo y contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}