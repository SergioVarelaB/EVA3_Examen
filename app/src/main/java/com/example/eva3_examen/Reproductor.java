package com.example.eva3_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Reproductor extends AppCompatActivity {
    Intent inRola, intento;
    TextView nombre, autor;
    int pos = 0, rola, rolaSig;
    MainActivity ma = new MainActivity();
    ImageView imgViewPausa, imgViewPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        /*inRola = new Intent(this, MyService.class);
        intento = getIntent();
        String name = intento.getStringExtra("nombre");
        String aut = intento.getStringExtra("autor");
        rola = intento.getIntExtra("rola", -1);
        pos = intento.getIntExtra("pos", -1);
        rolaSig = intento.getIntExtra("rolaSig", -1);
        nombre = findViewById(R.id.tvName);
        autor = findViewById(R.id.tvAutor);
        nombre.setText(name);
        autor.setText(aut);
        //Toast.makeText(this, pos + " ", Toast.LENGTH_LONG).show();
        imgViewPausa = findViewById(R.id.imgViewPausa);
        imgViewPlay = findViewById(R.id.imgViewPlay);*/

    }

    public void dale(View v) {
        /*Intent in = new Intent();
        in.putExtra("rola",R.raw.november_rain);*/
        //inRola.putExtra("pos", pos);
        //startService(inRola);

        if (imgViewPausa.getAlpha() == 0) {
            inRola.putExtra("pos", pos);
            startService(inRola);
            //imgViewPausa.setClickable(true);
            //imgViewPlay.setClickable(false);
            imgViewPlay.animate().alpha(0).setDuration(1000);
            imgViewPausa.animate().alpha(1).setDuration(1000);
        } else if (imgViewPlay.getAlpha() == 0) {
            inRola.putExtra("pos", pos);
            stopService(inRola);
            //imgViewPlay.setClickable(true);
            //imgViewPausa.setClickable(false);
            imgViewPausa.animate().alpha(0).setDuration(1000);
            imgViewPlay.animate().alpha(1).setDuration(1000);

        }
    }

    public void tate(View v) {
        stopService(inRola);
    }

    public void siguiente(View v) {
        stopService(inRola);
        if (pos == ma.rolas.length) {
            pos = 0;
        }
        pos = pos + 1;
        //nombre.setText("hola");
        //autor.setText(ma.rolas[pos].getAutor());
        inRola.putExtra("pos", pos);
        startService(inRola);
    }

    public void anterior(View v) {
        stopService(inRola);
        if (pos == ma.rolas.length) {
            pos = 0;
        }
        pos = pos - 1;
        //nombre.setText("hola");
        //autor.setText(ma.rolas[pos].getAutor());
        inRola.putExtra("pos", pos);
        startService(inRola);
    }
}
