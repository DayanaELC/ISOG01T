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

public class MainActivity extends AppCompatActivity {

    TextView Registrar;
    Button btnEntrar;
    EditText Clave;
    EditText Usuario;
    Variables variables = new Variables();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registrar=(TextView)findViewById(R.id.registrar);
        Registrar.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        Clave=(EditText)findViewById(R.id.Clave);
        Usuario=(EditText)findViewById(R.id.Usuario);

        /*if (variables.getLoginUser().isEmpty()){

        }else{
            Usuario.setText(variables.getLoginUser());
            Clave.setText(variables.getLoginPass());
        }*/
        variables.setLoginUser("Estudiante");
        variables.setLoginPass("iso2021");

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Clave.getText().toString().contentEquals(variables.getLoginPass())){
                    Intent intent = new Intent(MainActivity.this, MainActivityContenedor.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
                //Intent intent = new Intent(MainActivity.this, MainActivityContenedor.class);
                //startActivity(intent);
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
}