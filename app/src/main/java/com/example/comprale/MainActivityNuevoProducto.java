package com.example.comprale;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.HashMap;
import java.util.Map;

public class MainActivityNuevoProducto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private static final int COD_SELECCIONA = 10;
    EditText Nombre, Precio, Descripcion, Existencia;
    Button btnCancelar, btnAgregar;
    ImageView imgProducto;

    RequestQueue rq;
    JsonRequest jrq;

    Bitmap bitmap;
    String nombreImg="";

    StringRequest stringRequest;

    Variables variables = new Variables();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nuevo_producto);

        Nombre = (EditText)findViewById(R.id.Nombre);
        Precio = (EditText)findViewById(R.id.Precio);
        Existencia = (EditText)findViewById(R.id.Existencias);
        Descripcion = (EditText)findViewById(R.id.Descripcion);

        btnAgregar = (Button)findViewById(R.id.btnAgregar);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);

        imgProducto = (ImageView)findViewById(R.id.imgProducto);

        rq = Volley.newRequestQueue(this);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Nombre.getText().toString().isEmpty() || Precio.getText().toString().isEmpty() || Descripcion.getText().toString().isEmpty() || Existencia.getText().toString().isEmpty()){
                    Toast.makeText(MainActivityNuevoProducto.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                } else{
                    SubirImagen();
                }
            }
        });

        imgProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarImagenGaleria();
            }
        });
    }

    public void CargarImagenGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri miPath=data.getData();
        imgProducto.setImageURI(miPath);

        try {
            bitmap=MediaStore.Images.Media.getBitmap(MainActivityNuevoProducto.this.getContentResolver(),miPath);
            imgProducto.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SubirImagen(){
        String url = "https://www.pimentelycampos.com/comprale/php/subirimagen.php?";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                EnviarDatos("https://www.pimentelycampos.com/comprale/php/nuevoproducto.php?user="+variables.getLoginId()+
                        "&producto="+Nombre.getText().toString().replace(" ","%20")+"&imagen=https://www.pimentelycampos.com/comprale/php/img/"+nombreImg+".jpg&precio="+Precio.getText().toString()+
                        "&existencia="+Existencia.getText().toString()+"&descripcion="+Descripcion.getText().toString().replace(" ","%20"));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityNuevoProducto.this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String img=convertirImg(bitmap);
                nombreImg = ObtenerFecha();
                Map<String, String>parametros = new HashMap<>();
                parametros.put("imagen",img);
                parametros.put("nombre",nombreImg);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String convertirImg(Bitmap bitmap){
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,array);
        byte[] imagenByte = array.toByteArray();
        String imagenString = Base64.encodeToString(imagenByte,Base64.DEFAULT);

        return imagenString;
    }

    public String ObtenerFecha(){
        String datetime = "";
        Time today= new Time(Time.getCurrentTimezone());
        today.setToNow();
        int dia=today.monthDay;
        int mes=today.month;
        int year=today.year;
        int hora = today.hour;
        int minuto = today.minute;
        int seg = today.second;
        mes = mes+1;
        datetime = year+"-"+mes+"-"+dia+"-"+hora+"-"+minuto+"-"+seg+"-"+variables.getLoginId();
        return datetime;
    }

    private void EnviarDatos(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Intent intent = new Intent(MainActivityNuevoProducto.this, MainActivityContenedor.class);
                startActivity(intent);
                Toast.makeText(MainActivityNuevoProducto.this, "Producto registrado", Toast.LENGTH_SHORT).show();
                MainActivityNuevoProducto.this.finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityNuevoProducto.this, "Error al guardar\n"+error,Toast.LENGTH_SHORT).show();
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

    }
}