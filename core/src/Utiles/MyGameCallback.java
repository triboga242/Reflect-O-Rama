package Utiles;

/**
 * Interfaz para pasar preferencias y configuraciones entre libgdx y android nativo
 * Created by Triboga on 3/3/18.
 */

public interface MyGameCallback {

    void startActivity();

    void musicaOn();

    void musicaOff();

    boolean getCrazyMode();

    boolean getDebugmode();

}
