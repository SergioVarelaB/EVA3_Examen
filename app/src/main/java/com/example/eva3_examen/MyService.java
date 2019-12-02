package com.example.eva3_examen;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer mPlayer = null;
    Intent intento;
    TextView nombre, autor;
    SeekBar seekBarPosition;
    int posicion = -1;
    int cancion = 0;
    MainActivity mn = new MainActivity();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        cancion = intent.getIntExtra("pos", -1);
        posicion = intent.getIntExtra("posicion", -1);
        /*if (cancion == mn.rolas.length) {
            cancion = 0;
        }*/
        mPlayer = MediaPlayer.create(getApplicationContext(), mn.rolas[cancion].getRola());
        if (mPlayer != null) {
            double dura = mPlayer.getDuration();
            intent.putExtra("dura", dura);
            if(posicion != -1) {
                mPlayer.seekTo(posicion);
            }
            mPlayer.start();
            //Toast.makeText(this,dura+"",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
}