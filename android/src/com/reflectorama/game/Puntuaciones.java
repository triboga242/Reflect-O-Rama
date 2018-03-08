package com.reflectorama.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;

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



    public void checkPuntos(int puntos){


       int puntosAhora = myPreferences.getInt("jugador", 0);

       if (puntos>puntosAhora){
           myEditor.putInt("bender", puntosAhora + 1);
           myEditor.putInt("jugador", puntosAhora);
       }



    }

    public void setPuntos(int puntos){

        bender=findViewById(R.id.bender);
        jugador=findViewById(R.id.jugador);




        if (myPreferences.getInt("jugador", 0)>0) {

        }else{
            myEditor.putInt("bender", 1);
            myEditor.putInt("jugador", 0);
            myEditor.commit();
        }
        bender.setText(String.valueOf(myPreferences.getInt("bender", 0)));
        jugador.setText(String.valueOf(myPreferences.getInt("jugador", 0)));
    }


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
