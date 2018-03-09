package Utiles;

/**
 * Created by Triboga on 3/3/18.
 */

public interface MyGameCallback {

    void startActivity();

    void musicaOn();

    void musicaOff();

    boolean crazyMode = false;

    boolean getCrazyMode();

    boolean getDebugmode();

    void lanzatoast();
}
