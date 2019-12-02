package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {
    Intent inRola, intento;
    TextView nombre, autor, txtViewTiempoFin;
    int pos = 0, rola;
    double duracion;
    MainActivity ma = new MainActivity();
    ImageView imgViewPausa, imgViewPlay, album;
    SeekBar seekBarPosition;
    BroadcastReceiver brReceptor;
    boolean rand = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        //intento para el servicio
        inRola = new Intent(this, MyService.class);
        intento = getIntent();

        //receptor
        brReceptor = new miReceptorDifucion();
        IntentFilter filtro = new IntentFilter("mi_servicio");
        registerReceiver(brReceptor,filtro);

        //recuperamos los valores de la lista
        String name = intento.getStringExtra("nombre");
        String aut = intento.getStringExtra("autor");
        rola = intento.getIntExtra("rola", -1);
        pos = intento.getIntExtra("pos", -1);
        duracion = ma.rolas[pos].getDuracion();

        // vinculamos en codigo los elementos graficos
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        seekBarPosition = findViewById(R.id.seekBarPos);
        txtViewTiempoFin = findViewById(R.id.txtViewTiempoFin);

        //cambiamos la informacion de los elementos graficos
        nombre.setText(name);
        autor.setText(aut);
        txtViewTiempoFin.setText(duracion/60000 + "");
        album.setImageResource(ma.rolas[pos].getImagen());
        imgViewPausa = findViewById(R.id.imgViewPausa);
        imgViewPlay = findViewById(R.id.imgViewPlay);
        seekBarPosition.setMax((int)duracion);
        seekbarr(pos);

        //inizialisamos el servicio
        dale();

    }
    //se inicializa el servicio de reproduccion
    public void dale(){
        stopService(inRola);
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

    //se inicializa desde el boton
    public void dale(View v) {
        dale();
    }

    //se pausa el servicio
    public void tate(View v) {
        stopService(inRola);
    }

    //siguiente cancion desde boton
    public void siguiente(View v){
        if(rand){
            aleatorio();
        }else{
            siguientes();
        }

    }

    //siguiente cancion
    public void siguientes() {
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        txtViewTiempoFin = findViewById(R.id.txtViewTiempoFin);
        seekBarPosition = findViewById(R.id.seekBarPos);
        //Toast.makeText(this,pos,Toast.LENGTH_SHORT).show();
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
            txtViewTiempoFin.setText(ma.rolas[pos].getDuracion()/60000 + "");
            seekBarPosition.setMax((int)ma.rolas[pos].getDuracion());
            seekbarr(pos);
            startService(inRola);
        }else{
            Toast.makeText(this,"error " + pos ,Toast.LENGTH_SHORT).show();
        }
    }

    //aleatorio
    public void aleatorio(){
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        txtViewTiempoFin = findViewById(R.id.txtViewTiempoFin);
        int newpos = (int)(Math.random()*(ma.rolas.length));
        while(pos == newpos || pos+1 == newpos){
            newpos = (int)(Math.random()*(ma.rolas.length));
            //Toast.makeText(this,"hehe "+newpos,Toast.LENGTH_LONG).show();
        }
        inRola.putExtra("pos",newpos);
        seekBarPosition = findViewById(R.id.seekBarPos);
        if(pos >= 0 && pos <= ma.rolas.length-1){
            nombre.setText(ma.rolas[newpos].getNombre());
            autor.setText(ma.rolas[newpos].getAutor());
            album.setImageResource(ma.rolas[newpos].getImagen());
            txtViewTiempoFin.setText(ma.rolas[newpos].getDuracion()/60000+"");
            seekBarPosition.setMax((int) ma.rolas[newpos].getDuracion());
            seekbarr(newpos);
            startService(inRola);
            pos = newpos;
        }else{
            Toast.makeText(this,"error " + newpos ,Toast.LENGTH_SHORT).show();
        }
    }

    //aleatorio desde boton
    public void anterior(View v) {
        stopService(inRola);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        album = findViewById(R.id.album);
        txtViewTiempoFin = findViewById(R.id.txtViewTiempoFin);
        seekBarPosition = findViewById(R.id.seekBarPos);
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
            txtViewTiempoFin.setText(ma.rolas[pos].getDuracion()/60000 + "");
            seekBarPosition.setMax((int) ma.rolas[pos].getDuracion());
            seekbarr(pos);
            startService(inRola);
        }else{
            Toast.makeText(this,"error " + pos ,Toast.LENGTH_SHORT).show();
        }
    }

    //comprobamos en que estado se encuentra aleatorio
    public void setAleatorio(View v){
        if(rand){
            rand = false;
        }else{
            rand = true;
        }
        Toast.makeText(this,""+rand, Toast.LENGTH_LONG).show();
    }

    //regresamos a la playlist
    public void lista(View v){
        this.finish();
    }


    //vinculamos la seekbar y sus metodos
    public void seekbarr(int pos){
        stopService(inRola);
        seekBarPosition.setMax((int)ma.rolas[pos].getDuracion());
        seekBarPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stopService(inRola);
                inRola.putExtra("posicion",progress);
                startService(inRola);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                stopService(inRola);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar){
            }
        });
    }
    class miReceptorDifucion extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //tv.append(intent.getStringExtra("mensaje"));
        }
    }
}
