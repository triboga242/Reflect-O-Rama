package com.reflectorama.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        Intent i = new Intent(this, MenuInicio.class);
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
            startService(intentmus);
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
}
