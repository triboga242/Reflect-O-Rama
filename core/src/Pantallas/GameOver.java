package Pantallas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.reflectorama.game.MyGdxGame;

import Utiles.MyGameCallback;

/**
 * Created by Triboga on 3/3/18.
 */

public class GameOver extends PantallaBase {

    private Stage escena;
    private Image gameover;
    private Texture fondo;

    public void setPuntos_(int puntos_) {
        this.puntos_ = puntos_;
    }

    private int puntos_;
    private MyGdxGame g;
    public static AssetManager manager = new AssetManager();


    public GameOver(final MyGdxGame g, final int puntos) {
        super(g);

        this.g = g;
        this.puntos_ = puntos;

        fondo = new Texture(Gdx.files.internal("ReflectoMatic/Fondo.jpg"));
        escena = new Stage(new FitViewport(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3));
        gameover = new Image(new Texture(Gdx.files.internal("ReflectoMatic/gameover.png")));

        Texture playTexture = new Texture("ReflectoMatic/Ship_White.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(playTexture));
        ImageButton playButton = new ImageButton(drawable);

        Texture menuTexture = new Texture("ReflectoMatic/Nibbleer.png");

        Drawable drawable1 = new TextureRegionDrawable(new TextureRegion(menuTexture));
        ImageButton menuButton = new ImageButton(drawable1);

        gameover.setPosition(Gdx.graphics.getWidth() / 6 - gameover.getWidth() / 2, Gdx.graphics.getHeight() / 4 - gameover.getHeight() / 2);

        playButton.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        playButton.setPosition(Gdx.graphics.getWidth() / 6 - Gdx.graphics.getWidth() / 8, Gdx.graphics.getHeight() / 6 - Gdx.graphics.getHeight() / 12);

        menuButton.setSize(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
        menuButton.setPosition(Gdx.graphics.getWidth() / 6 + Gdx.graphics.getWidth() / 25, Gdx.graphics.getHeight() / 6 - Gdx.graphics.getHeight() / 12);

        playButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                g.bdr.saveCurrentGame(puntos_);

                NaveEspacio naveEspacio = new NaveEspacio(new MyGdxGame(g.myGameCallback, g.bdr));
                naveEspacio.GameInit();

               g.currentScreen = naveEspacio;
               g.setScreen(g.currentScreen);


                dispose();
            }
        });

        menuButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                g.bdr.saveCurrentGame(puntos_);
                //g.myGameCallback.startActivity();
                dispose();

            }
        });

        escena.addActor(playButton);
        escena.addActor(gameover);
        escena.addActor(menuButton);

    }


    @Override
    public void dispose() {
        super.dispose();
        g.myGameCallback.musicaOff();

    }

    @Override
    public void show() {
        super.show();
        g.myGameCallback.musicaOn();
        Gdx.input.setInputProcessor(escena);
    }

    @Override
    public void hide() {
        super.hide();
        g.myGameCallback.musicaOff();
        Gdx.input.setInputProcessor(null);


    }

    @Override
    public void pause() {
        super.pause();
        g.myGameCallback.musicaOff();
    }

    @Override
    public void resume() {
        super.resume();
        g.myGameCallback.musicaOn();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        escena.getBatch().begin();
        escena.getBatch().draw(fondo, 0, 0, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3);
        escena.getBatch().end();
        escena.act();
        escena.draw();
    }
}
