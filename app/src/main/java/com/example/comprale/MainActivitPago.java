package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivitPago extends AppCompatActivity {

    Button btnPago;
    TextView txtDireccion, txtCompra, txtTotal, txtTotalCompra;
    Variables variables = new Variables();
    DecimalFormat df = new DecimalFormat("###,###,##0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activit_pago);
        btnPago = (Button)findViewById(R.id.btnPagar);
        txtDireccion = (TextView)findViewById(R.id.txtDireccion);
        txtCompra = (TextView)findViewById(R.id.txtCompra);
        txtTotal = (TextView)findViewById(R.id.txtTotal);
        txtTotalCompra = (TextView)findViewById(R.id.txtTotalCompra);

        txtDireccion.setText(variables.getLoginDir());
        txtCompra.setText("$"+df.format(variables.getTotalPagar()));
        txtTotal.setText("$"+df.format(variables.getTotalPagar()));
        txtTotalCompra.setText("$"+df.format(variables.getTotalPagar()));

        btnPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                variables.setTotalPagar(0.0);
                variables.getListaImagen().clear();
                variables.getListaCodigoProducto().clear();
                variables.getListaNombreProducto().clear();
                variables.getListaPrecioProducto().clear();
                variables.getListaCantidadProducto().clear();

                Toast.makeText(MainActivitPago.this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivitPago.this, MainActivityContenedor.class);
                startActivity(intent);
                MainActivitPago.this.finish();
            }
        });
    }
}