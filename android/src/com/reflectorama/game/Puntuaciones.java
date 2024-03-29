package com.reflectorama.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import BaseDatos.BaseDatosAndroidReflectorama;

/**
 * Created by Triboga on 2/3/18.
 */

public class Puntuaciones extends Activity {


    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private TextView bender;
    private TextView jugador;



    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.puntuaciones);
        myPreferences
                = PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = myPreferences.edit();

        if (myPreferences.getBoolean("musica", true)) {

            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);
        }

        setPuntos2();
    }

    /**
     * Setear los puntos en la base de datos
     */

    public void setPuntos2(){

        bender=findViewById(R.id.bender);
        jugador=findViewById(R.id.jugador);

        BaseDatosAndroidReflectorama baseDatos=new BaseDatosAndroidReflectorama(this);
        int puntosMaximos = baseDatos.getScore();


        bender.setText(String.valueOf(puntosMaximos+1));
        jugador.setText(String.valueOf(puntosMaximos));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myPreferences.getBoolean("musica", true)) {

            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);
        }
        setPuntos2();
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
