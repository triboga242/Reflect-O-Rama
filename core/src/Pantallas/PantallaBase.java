package Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 *
 * Created by Triboga on 8/2/18.
 */

public abstract class PantallaBase implements Screen {


    private Game game;
    public PantallaBase(Game g){
        game=g;
    }

    public PantallaBase(){}

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
    public Game getGame(){
        return game;
    }

}
