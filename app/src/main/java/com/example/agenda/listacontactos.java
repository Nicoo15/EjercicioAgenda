package com.example.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listacontactos extends AppCompatActivity {

    ArrayList<Contacto> lista = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listacontactos);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://agenda-8b2dc-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Contactos");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> datos = dataSnapshot.getChildren();
                for (DataSnapshot d : datos) {
                    Contacto contactos = dataSnapshot.getValue(Contacto.class);
                    Log.d("DATOS", ":"  + contactos);
                    lista.add(contactos);
                    mostrar();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("ERROR", databaseError.getMessage());
            }
        });

    }

    private void mostrar() {
        RecyclerView rec = findViewById(R.id.mi_reycler);
        RecyclerView.LayoutManager gestor = new LinearLayoutManager(this);
        MiAdaptador adaptador = new MiAdaptador(lista);
        rec.setLayoutManager(gestor);
        rec.setAdapter(adaptador);
    }
}


   /* RecyclerView rec = findViewById(R.id.mi_reycler);
    RecyclerView.LayoutManager gestor= new LinearLayoutManager(this);
    MiAdaptador adaptador= new MiAdaptador(lista);
    rec.setLayoutManager(gestor);
    rec.setAdapter(adaptador);*/
