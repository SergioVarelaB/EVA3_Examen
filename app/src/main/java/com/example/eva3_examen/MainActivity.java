package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Rolas[] rolas = {
            new Rolas(R.drawable.garafd,"Guns and Roses","November rain",R.raw.november_rain,556848.0),
            new Rolas(R.drawable.iwywh,"Pink Floyd","I wish you were here",R.raw.wish_you_were_here,293208.0),
            new Rolas(R.drawable.tdfm,"Queen","You don't fool me",R.raw.you_dont_fool_me,322752.0),
            new Rolas(R.drawable.beatlesrevolver,"The Beatles","Eleanor Rigby",R.raw.eleanor,131352.0),
    };
    Intent iReproductor;
    ListView listaClima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaClima = findViewById(R.id.lvRolitas);
        listaClima.setAdapter(new Rolas_adapter(this, R.layout.layout_rolas, rolas));
        listaClima.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,rolas[position].getNombre(),Toast.LENGTH_SHORT).show();
        String autor = rolas[position].getAutor();
        String nombre = rolas[position].getNombre();
        iReproductor = new Intent(this,Reproductor.class);
        iReproductor.putExtra("autor", autor);
        iReproductor.putExtra("nombre", nombre);
        iReproductor.putExtra("pos", position);
        startActivity(iReproductor);
    }
}
