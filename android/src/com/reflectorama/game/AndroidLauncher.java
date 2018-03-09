package com.reflectorama.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.reflectorama.game.MyGdxGame;

import BaseDatos.BaseDatosAndroidReflectorama;
import Pantallas.NaveEspacio;
import Utiles.BaseDatosReflect;
import Utiles.MyGameCallback;

public class AndroidLauncher extends AndroidApplication implements MyGameCallback {

    private MyGdxGame myGdxGame;
    private SharedPreferences myPreferences;
    Intent i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

       i = new Intent(this, MenuInicio.class);
        super.onCreate(savedInstanceState);
        BaseDatosAndroidReflectorama baseDatos = new BaseDatosAndroidReflectorama(this);
        myPreferences
                = PreferenceManager.getDefaultSharedPreferences(this);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        this.myGdxGame = new MyGdxGame(this, baseDatos);
        initialize(myGdxGame, config);
    }

    @Override
    public void startActivity() {

        startActivity(i);
    }

    @Override
    public void musicaOn() {
        if (myPreferences.getBoolean("musica", true)) {
            Intent intentmus = new Intent(this, AudioServiceJuego.class);
            intentmus.putExtra("action", AudioServiceJuego.START);
            startService(intentmus);
        }
    }

    @Override
    public void musicaOff() {
        if (myPreferences.getBoolean("musica", true)) {
            Intent intentmus = new Intent(this, AudioServiceJuego.class);
            intentmus.putExtra("action", AudioServiceJuego.PAUSE);
            stopService(intentmus);
        }
    }

    @Override
    public boolean getCrazyMode() {
        if (myPreferences.getBoolean("crazy_mode", true)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean getDebugmode() {
        return (myPreferences.getBoolean("debugMode", false));
    }

    @Override
    public void lanzatoast() {
        //Toast.makeText(this, "Pausa juego", Toast.LENGTH_LONG).show();
        Log.d("pausa","del juego");
       //
        // musicaOff();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this, "Hola pausaAndroid", Toast.LENGTH_LONG).show();
        Log.d("pausa","de la actividad");
        //musicaOff();
    }
}
