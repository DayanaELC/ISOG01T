package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class MainActivityDetalleProducto extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    TextView txtNombre, txtPrecio, txtDescripcion;
    EditText cantidad;
    ImageView imgProducto;
    Button btnMas, btnMenos, btnRegresar, btnComprar;
    int stock = 0;
    String img = "";
    Double precio = 0.0;
    Variables variables = new Variables();

    DecimalFormat df = new DecimalFormat("###,###,##0.00");

    RequestQueue rq;
    JsonRequest jrq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detalle_producto);

        txtNombre=(TextView)findViewById(R.id.txtNombre);
        txtPrecio=(TextView)findViewById(R.id.txtPrecio);
        cantidad=(EditText)findViewById(R.id.cantidad);
        txtDescripcion=(TextView) findViewById(R.id.txtDescripcion);
        btnMas=(Button)findViewById(R.id.btnMas);
        btnMenos=(Button)findViewById(R.id.btnMenos);
        btnRegresar=(Button)findViewById(R.id.btnRegresar);
        btnComprar=(Button)findViewById(R.id.btnComprar);
        imgProducto=(ImageView) findViewById(R.id.imgProducto);

        rq = Volley.newRequestQueue(this);

        CargarInfo();

        txtDescripcion.setText("Descripción del producto " + variables.getItemGridProductos());
        txtNombre.setText("Nombre del producto " + variables.getItemGridProductos());

        cantidad.setText("1");

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(cantidad.getText().toString())>1){
                    cantidad.setText(""+(Integer.parseInt(cantidad.getText().toString())-1));
                }
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(cantidad.getText().toString())<stock){
                    cantidad.setText(""+(Integer.parseInt(cantidad.getText().toString())+1));
                }
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(cantidad.getText().toString())<stock){
                    variables.setCodigoProducto(variables.getItemGridProductos());
                    variables.setProducto(txtNombre.getText().toString());
                    variables.setPrecio(precio.toString());
                    variables.setCantidad(cantidad.getText().toString());
                    variables.setImagen(img);
                    Toast.makeText(MainActivityDetalleProducto.this, "Producto agregado", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(MainActivityDetalleProducto.this, "Solo hay disponibles " + stock + " unidades", Toast.LENGTH_SHORT).show();
                    cantidad.setText(""+stock);
                }
            }
        });
    }
    public void CargarInfo(){

        String url ="https://www.pimentelycampos.com/comprale/php/producto.php?id="+variables.getItemGridProductos();
        jrq = new JsonObjectRequest(Request.Method.GET, url, null,this, this);
        rq.add(jrq);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray = response.optJSONArray("detalle");
        JSONObject jsonObject = null;
        try {

            jsonObject = jsonArray.getJSONObject(0);
            txtNombre.setText(jsonObject.getString("producto"));
            precio = jsonObject.getDouble("precio");
            txtPrecio.setText("$"+df.format(precio));
            txtDescripcion.setText(jsonObject.getString("descripcion"));
            stock = jsonObject.getInt("existencia");
            img = jsonObject.getString("imagen");
            Glide.with(imgProducto.getContext()).load(img).into(imgProducto);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}