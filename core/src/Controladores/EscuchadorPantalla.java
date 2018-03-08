package Controladores;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import Actores.ActorEscudo;

/**
 * Created by Triboga on 1/3/18.
 */

public class EscuchadorPantalla extends ApplicationAdapter implements InputProcessor {

    private ActorEscudo e1, e2, e3, e4;

    public EscuchadorPantalla(ActorEscudo e1, ActorEscudo e2, ActorEscudo e3, ActorEscudo e4) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
    }

    @Override
    public boolean keyDown(int keycode) {


        int contador = 0;

        if (e1.isVisible()) contador++;

        if (e2.isVisible()) contador++;
        if (e3.isVisible()) contador++;
        if (e4.isVisible()) contador++;

        switch (keycode) {

            case Input.Keys.I:
                if (!e1.isVisible() && contador < 2) {
                    e1.setVisible(true);
                } else {
                    e1.setVisible(false);
                }
                break;
            case Input.Keys.Q:
                if (!e1.isVisible() && contador < 2) {
                    e1.setVisible(true);
                } else {
                    e1.setVisible(false);
                }
                break;
            case Input.Keys.O:
                if (!e2.isVisible() && contador < 2) {
                    e2.setVisible(true);
                } else {
                    e2.setVisible(false);
                }
                break;
            case Input.Keys.W:
                if (!e2.isVisible() && contador < 2) {
                    e2.setVisible(true);
                } else {
                    e2.setVisible(false);
                }
                break;
            case Input.Keys.K:
                if (!e4.isVisible() && contador < 2) {
                    e4.setVisible(true);
                } else {
                    e4.setVisible(false);
                }

                break;
            case Input.Keys.A:
                if (!e4.isVisible() && contador < 2) {
                    e4.setVisible(true);
                } else {
                    e4.setVisible(false);
                }

                break;
            case Input.Keys.L:
                if (!e3.isVisible() && contador < 2) {
                    e3.setVisible(true);
                } else {
                    e3.setVisible(false);
                }
                break;
            case Input.Keys.P:
                if (!e3.isVisible() && contador < 2) {
                    e3.setVisible(true);
                } else {
                    e3.setVisible(false);
                }
                break;
            case Input.Keys.S:
                if (!e3.isVisible() && contador < 2) {
                    e3.setVisible(true);
                } else {
                    e3.setVisible(false);
                }
                break;
        }


        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        float altoPantalla= Gdx.graphics.getHeight();
        float anchoPantalla=Gdx.graphics.getWidth();

        int contador = 0;

        if (e1.isVisible()) contador++;

        if (e2.isVisible()) contador++;
        if (e3.isVisible()) contador++;
        if (e4.isVisible()) contador++;


        if (screenX >= anchoPantalla / 2 && screenY >= altoPantalla / 2) {

            if (!e3.isVisible() && contador < 2) {
                e3.setVisible(true);
            } else {
                e3.setVisible(false);
            }
        } else if (screenX < anchoPantalla / 2 && screenY < altoPantalla / 2) {

            if (!e1.isVisible() && contador < 2) {
                e1.setVisible(true);
            } else {
                e1.setVisible(false);
            }
        } else if (screenX >anchoPantalla / 2 && screenY < altoPantalla / 2) {

            if (!e2.isVisible() && contador < 2) {
                e2.setVisible(true);
            } else {
                e2.setVisible(false);
            }
        } else if (screenX < anchoPantalla / 2 && screenY > altoPantalla / 2) {

            if (!e4.isVisible() && contador < 2) {
                e4.setVisible(true);
            } else {
                e4.setVisible(false);
            }
        }


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
