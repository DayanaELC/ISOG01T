package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityDetalleProducto extends AppCompatActivity {

    TextView txtNombre, txtPrecio, txtDescripcion;
    EditText cantidad;
    Button btnMas, btnMenos, btnRegresar, btnComprar;
    Variables variables = new Variables();

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
                cantidad.setText(""+(Integer.parseInt(cantidad.getText().toString())+1));
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
                Toast.makeText(MainActivityDetalleProducto.this, "Producto agregado", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}