package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {
    Intent inRola,intento;
    TextView nombre, autor;
    int rola, rolaSig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        inRola = new Intent(this, MyService.class);
        intento = getIntent();
        String name = intento.getStringExtra("nombre");
        String aut = intento.getStringExtra("autor");
        rola = intento.getIntExtra("rola",-1);
        rolaSig = intento.getIntExtra("rolaSig",-1);
        TextView nombre = findViewById(R.id.tvName);
        TextView autor = findViewById(R.id.tvAutor);
        nombre.setText(name);
        autor.setText(aut);
        Toast.makeText(this,rola+" ",Toast.LENGTH_LONG).show();
        //inRola.getIntExtra();
    }
    public void dale(View v){
        /*Intent in = new Intent();
        in.putExtra("rola",R.raw.november_rain);*/
        inRola.putExtra("rola",rola);
        startService(inRola);
    }
    public void tate(View v){
        stopService(inRola);
    }
    public void siguiente(View v){
        stopService(inRola);
        inRola.putExtra("rola",rola);
        startService(inRola);
    }
}
