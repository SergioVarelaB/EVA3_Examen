package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {
    Intent inRola, intento;
    TextView nombre, autor;
    int pos = 0, rola, rolaSig;
    MainActivity ma = new MainActivity();
    ImageView imgViewPausa, imgViewPlay, album;
    boolean rand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        inRola = new Intent(this, MyService.class);
        intento = getIntent();
        String name = intento.getStringExtra("nombre");
        String aut = intento.getStringExtra("autor");
        rola = intento.getIntExtra("rola", -1);
        pos = intento.getIntExtra("pos", -1);
        rolaSig = intento.getIntExtra("rolaSig", -1);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        nombre.setText(name);
        autor.setText(aut);
        album.setImageResource(ma.rolas[pos].getImagen());
        //Toast.makeText(this, pos + " ", Toast.LENGTH_LONG).show();
        imgViewPausa = findViewById(R.id.imgViewPausa);
        imgViewPlay = findViewById(R.id.imgViewPlay);

    }

    public void dale(View v) {
        if (imgViewPausa.getAlpha() == 0) {
            inRola.putExtra("pos", pos);
            startService(inRola);
            imgViewPlay.animate().alpha(0).setDuration(500);
            imgViewPausa.animate().alpha(1).setDuration(500);
        } else if (imgViewPlay.getAlpha() == 0) {
            inRola.putExtra("pos", pos);
            stopService(inRola);
            //imgViewPlay.setClickable(true);
            //imgViewPausa.setClickable(false);
            imgViewPausa.animate().alpha(0).setDuration(500);
            imgViewPlay.animate().alpha(1).setDuration(500);

        }
    }
    public void tate(View v) {
        stopService(inRola);
    }

    public void siguiente(View v){
        if(rand){
            aleatorio();
        }else{
            siguientes();
        }

    }

   public void siguientes() {
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
    public void aleatorio(){
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        int newpos = (int)(Math.random()*(ma.rolas.length));
        while(pos == newpos || pos+1 == newpos){
            newpos = (int)(Math.random()*(ma.rolas.length));
            //Toast.makeText(this,"hehe "+newpos,Toast.LENGTH_LONG).show();
        }
        inRola.putExtra("pos",newpos);
        if(pos >= 0 && pos <= ma.rolas.length-1){
            nombre.setText(ma.rolas[newpos].getNombre());
            autor.setText(ma.rolas[newpos].getAutor());
            album.setImageResource(ma.rolas[newpos].getImagen());
            startService(inRola);
            pos = newpos;
        }else{
            Toast.makeText(this,"error " + newpos ,Toast.LENGTH_SHORT).show();
        }
    }
    public void anterior(View v) {
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        if(pos == 0){
            pos = ma.rolas.length-1;
        }else{
            pos = pos-1;
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
    public void setAleatorio(View v){
        if(rand){
            rand = false;
        }else{
            rand = true;
        }
        Toast.makeText(this,""+rand, Toast.LENGTH_LONG).show();
    }
}
