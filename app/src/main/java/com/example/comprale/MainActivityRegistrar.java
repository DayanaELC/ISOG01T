package com.example.comprale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityRegistrar extends AppCompatActivity {

    EditText Nombre, Email, Clave, Confirmacion;
    Button btnRegistrar;
    CheckBox Ofertas, Terminos;

    Variables variables=new Variables();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registrar);

        Nombre=(EditText)findViewById(R.id.Nombre);
        Clave=(EditText)findViewById(R.id.Clave);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        Confirmacion=(EditText)findViewById(R.id.ClaveConfirmacion);
        Email=(EditText)findViewById(R.id.Email);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Nombre.getText().toString().isEmpty() || Clave.getText().toString().isEmpty()){
                    Toast.makeText(MainActivityRegistrar.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if (!Clave.getText().toString().contentEquals(Confirmacion.getText().toString())){
                    Toast.makeText(MainActivityRegistrar.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else {
                    variables.setLoginUser(Nombre.getText().toString());
                    variables.setLoginPass(Clave.getText().toString());
                    Intent intent = new Intent(MainActivityRegistrar.this, MainActivityContenedor.class);
                    startActivity(intent);
                    Toast.makeText(MainActivityRegistrar.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}