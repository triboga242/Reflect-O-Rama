package com.reflectorama.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import BaseDatos.BaseDatosAndroidReflectorama;
import BaseDatos.BaseDatosHelper;

import static Utiles.BaseDatosReflect.getScoreTablename;


/**
 * Created by Triboga on 28/2/18.
 */

public class MenuInicio extends Activity {

    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;



    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        Intent intent2 = new Intent(this, AudioServiceJuego.class);
        stopService(intent2);

        myPreferences
                = PreferenceManager.getDefaultSharedPreferences(this);

        myEditor= myPreferences.edit();
/*
        boolean existe= myPreferences.getBoolean("baseDatos", false);
        if (!myPreferences.getBoolean("baseDatos", false)){
            BaseDatosAndroidReflectorama baseDatos=new BaseDatosAndroidReflectorama(this);
            baseDatos.crear();
            myEditor.putBoolean("baseDatos", true);
            myEditor.commit();
        }
        boolean existe2= myPreferences.getBoolean("baseDatos", false);
        */

        if (myPreferences.getBoolean("musica", true)) {
            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);


        } else {

        }
        setContentView(R.layout.menuinicio);


    }

    public void juegar(View v) {

        Intent i = new Intent(this, AndroidLauncher.class);

        startActivity(i);

    }

    public void puntuaciones(View v) {

        Intent i = new Intent(this, Puntuaciones.class);
        startActivity(i);
    }

    public void opciones(View v) {

        Intent i = new Intent(this, Opciones.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myPreferences.getBoolean("musica", true)) {

            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myPreferences.getBoolean("musica", true)) {

            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.PAUSE);
            startService(intent);
        }
    }
}