package com.example.eva3_examen;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer mPlayer = null;
    Intent intento;
    int cancion = 0;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        cancion = intent.getIntExtra("rola",0);
        Toast.makeText(getApplicationContext(),cancion+"",Toast.LENGTH_LONG).show();
        mPlayer = MediaPlayer.create(getApplicationContext(), cancion);
        if (mPlayer != null) {
            mPlayer.start();
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