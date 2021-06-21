package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivitPago extends AppCompatActivity {

    Button btnPago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activit_pago);
        btnPago = (Button)findViewById(R.id.btnPagar);

        btnPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivitPago.this, "Gracias por su compra", Toast.LENGTH_SHORT).show();
                MainActivitPago.this.finish();
            }
        });
    }
}