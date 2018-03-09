package com.reflectorama.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;


/**
 * Created by Triboga on 28/2/18.
 */

public  class MenuInicio extends Activity {

    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private int contador;



    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        contador=0;
        Intent intent2 = new Intent(this, AudioServiceJuego.class);
        stopService(intent2);

        myPreferences
                = PreferenceManager.getDefaultSharedPreferences(this);

        myEditor= myPreferences.edit();

        if (myPreferences.getBoolean("musica", true)) {
            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);


        }

        myEditor.putBoolean("debugMode", false);
        myEditor.commit();
        setContentView(R.layout.menuinicio);

    }

    public void juegar(View v) {

        Intent i = new Intent(this, AndroidLauncher.class);

        startActivity(i);

    }

    //=====================================================//
    //         ALERT DIALOG DE LOS REQUISITOS              //
    //=====================================================//
    /**
     * Al pulsar 5 veces en mordisquitos se activara el debug mode para la siguiente pantalla
     * empieza con los 4 escudos activos y se activan la vista de los poligonos.
     * @param v
     */
    public void debugMode(View v){
       contador++;
       if (contador==5){
           myEditor = myPreferences.edit();
           myEditor.putBoolean("debugMode", true);
           myEditor.commit();
           AlertDialog.Builder builder = new AlertDialog.Builder(this);

           builder.setMessage("Modo debug activado");
            builder.setNeutralButton(
                    "OK",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }
            );
           AlertDialog alertDialog = builder.create();

           alertDialog.show();
       }
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
        myEditor.putBoolean("debugMode", false);
        myEditor.commit();
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onStop() {
        super.onStop();
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