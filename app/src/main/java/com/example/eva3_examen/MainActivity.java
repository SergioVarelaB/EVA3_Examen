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
            new Rolas(R.drawable.unkown,"Guns and Roses","november rain",R.raw.november_rain),
            new Rolas(R.drawable.unkown,"pink floyd","i wish you were here",R.raw.wish_you_were_here),
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
        int rola = rolas[position].getRola();
        int rolaSig = rolas[position+1].getRola();
        iReproductor = new Intent(this,Reproductor.class);
        iReproductor.putExtra("autor", autor);
        iReproductor.putExtra("nombre", nombre);
        iReproductor.putExtra("rola", rola);
        iReproductor.putExtra("rolaSig", rolaSig);
        startActivity(iReproductor);
    }
}
