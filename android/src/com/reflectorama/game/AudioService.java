package com.reflectorama.game;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Clase para controlar el sonido de la parte de los menuses
 * Created by Triboga on 2/3/18.
 */

public class AudioService extends Service {

    static final int DECREASE = 1, INCREASE = 2, START = 3, PAUSE = 4;
    Boolean shouldPause = false;
    MediaPlayer loop;

    /**
     * Comienzo del loop de la cancion si es null
     */
    private void startLoop(){
        if(loop == null){
            loop = MediaPlayer.create(this, R.raw.loop);
        }
        if(!loop.isPlaying()){
            loop.setLooping(true);
            loop.start();
        }
    }

    /**
     * Bajar el volumen de la cancion
     */
    private void decrease(){
        loop.setVolume(0.2f, 0.2f);
    }

    /**
     *
     */
    private void increase(){
        loop.setVolume(1.0f, 1.0f);
    }
    private void start(){
        startLoop();
        shouldPause = false;
    }
    private void pause(){
        shouldPause = true;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(shouldPause) {
                            loop.pause();
                        }
                    }
                }, 100);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "Creating service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(getClass().getSimpleName(), "Intent received");

        try {
            int actionDefault = 0;
            int action = actionDefault;

            if(intent != null){
                if(intent.hasExtra("action")){
                    action = intent.getIntExtra("action", actionDefault);
                }
            }

            switch (action) {
                case INCREASE:
                    increase();
                    break;
                case DECREASE:
                    decrease();
                    break;
                case START:
                    start();
                    break;
                case PAUSE:
                    pause();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loop != null) loop.release();
    }

    public void stop(){
        shouldPause=true;
        loop.pause();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
