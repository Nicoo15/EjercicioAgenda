package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

Context tha = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://agenda-8b2dc-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Contactos");

        Button guardar = (Button)findViewById(R.id.guardar);
        Button verlista = (Button)findViewById(R.id.verlista);
        ArrayList<Contacto> lista= new ArrayList<>();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombre= (EditText)findViewById(R.id.tv_nombre);
                EditText email= (EditText)findViewById(R.id.tv_email);

                String name= nombre.getText().toString();
                String correo= email.getText().toString();

                Contacto c = new Contacto(name,correo);
                myRef.push().setValue(c);



            }
        });


        verlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.agenda.MainActivity.this,listacontactos.class);
                startActivity(intent);
            }

        });



        /*
        ArrayList<Contacto> lista= new ArrayList<>();
        lista.add(c);
*/

       /* RecyclerView rec = findViewById(R.id.mi_reycler);
        RecyclerView.LayoutManager gestor= new LinearLayoutManager(this);
        MiAdaptador adaptador= new MiAdaptador(lista);
        rec.setLayoutManager(gestor);
        rec.setAdapter(adaptador);*/
    }
}

