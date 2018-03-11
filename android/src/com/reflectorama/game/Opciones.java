package com.reflectorama.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

/**
 * Created by Triboga on 28/2/18.
 */

public class Opciones extends Activity implements View.OnClickListener {

    private SharedPreferences myPreferences;
    private SharedPreferences.Editor myEditor;
    private Button tb;
    private Button tb2;
    private int cont;
    private int cont2;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.opciones);
        tb2 = findViewById(R.id.toggleButton2);
        tb = findViewById(R.id.toggleButton);

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = myPreferences.edit();

        if (myPreferences.getBoolean("musica", true)) {

            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);
            cont = 1;
            tb.setText("ON");

        } else {
            cont = 0;
            tb.setText("OFF");
        }

        if (myPreferences.getBoolean("crazy_mode", true)) {
            tb2.setText("OFF");
            cont2=1;

        }else {
            tb2.setText("ON");
            cont2 = 0;
        }
        tb.setOnClickListener(this);
    }


    public void modo(View view) {

        cont2++;
        if (cont2 % 2==0){
            tb2.setText("ON");
            myEditor.putBoolean("crazy_mode", false);
            myEditor.commit();
        } else {
            tb2.setText("OFF");
            myEditor.putBoolean("crazy_mode", true);
            myEditor.commit();
        }
    }

    @Override
    public void onClick(View view) {

        cont++;
        if (cont % 2 == 0) {
            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.PAUSE);
            stopService(intent);
            tb.setText("OFF");
            myEditor.putBoolean("musica", false);
            myEditor.commit();
        } else {
            Intent intent = new Intent(this, AudioService.class);
            intent.putExtra("action", AudioService.START);
            startService(intent);
            tb.setText("ON");
            myEditor.putBoolean("musica", true);
            myEditor.commit();
        }
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
