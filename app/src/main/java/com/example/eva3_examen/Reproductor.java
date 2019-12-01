package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {
    Intent inRola,intento;
    TextView nombre, autor;
    ImageView album;
    int pos = 0, rola, rolaSig;
    MainActivity ma = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        inRola = new Intent(this, MyService.class);
        intento = getIntent();
        String name = intento.getStringExtra("nombre");
        String aut = intento.getStringExtra("autor");
        rola = intento.getIntExtra("rola",-1);
        pos = intento.getIntExtra("pos",-1);
        rolaSig = intento.getIntExtra("rolaSig",-1);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        nombre.setText(name);
        autor.setText(aut);
        album.setImageResource(ma.rolas[pos].getImagen());
        //Toast.makeText(this,pos+" ",Toast.LENGTH_SHORT).show();
    }
    public void dale(View v){
        /*Intent in = new Intent();
        in.putExtra("rola",R.raw.november_rain);*/
        inRola.putExtra("pos",pos);
        startService(inRola);
    }
    public void tate(View v){
        stopService(inRola);
    }
    public void siguiente(View v){
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        if(pos == ma.rolas.length-1){
            pos = 0;
        }else{
            pos = pos+1;
        }
        inRola.putExtra("pos",pos);
        if(pos >= 0 && pos <= ma.rolas.length-1){
            nombre.setText(ma.rolas[pos].getNombre());
            autor.setText(ma.rolas[pos].getAutor());
            album.setImageResource(ma.rolas[pos].getImagen());
            startService(inRola);
        }else{
            Toast.makeText(this,"error " + pos ,Toast.LENGTH_SHORT).show();
        }

    }
    public void interfaz(){
        Toast.makeText(this,pos+"", Toast.LENGTH_SHORT).show();

        if(pos == ma.rolas.length-1){
            pos = 0;
        }else{

        }

    }
}
