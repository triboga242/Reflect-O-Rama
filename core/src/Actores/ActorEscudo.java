package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

import javax.sound.sampled.Line;



/**
 * Created by Triboga on 26/2/18.
 */

public class ActorEscudo extends Actor {

    Texture t;
    protected Sprite sprite;
    protected int modo;

    public ActorEscudo(int modo) {
        super();
        t = new Texture("ReflectoMatic/Escudo.png");
        sprite = new Sprite(t);
        modoEscudo(modo);


    }

    public void modoEscudo(int modo) {

        this.modo = modo;
        switch (modo) {
            case 1:
                sprite.setBounds((Gdx.graphics.getWidth() / 2) - (sprite.getWidth()),
                        (Gdx.graphics.getHeight() / 2),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);

                setBounds((Gdx.graphics.getWidth() / 2) - (sprite.getWidth()),
                        (Gdx.graphics.getHeight() / 2),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);

                setName("escudo1");

                break;
            case 2:

                sprite.setBounds((Gdx.graphics.getWidth() / 2),
                        (Gdx.graphics.getHeight() / 2),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);
                sprite.flip(true, false);

                setBounds((Gdx.graphics.getWidth() / 2),
                        (Gdx.graphics.getHeight() / 2),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);


                setName("escudo2");
                break;
            case 3:

                sprite.setBounds((Gdx.graphics.getWidth() / 2),
                        (Gdx.graphics.getHeight() / 2) - (sprite.getHeight()),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);
                sprite.flip(true, true);

                setBounds((Gdx.graphics.getWidth() / 2),
                        (Gdx.graphics.getHeight() / 2) - (sprite.getHeight()),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);


                setName("escudo3");

                break;
            case 4:

                sprite.setBounds((Gdx.graphics.getWidth() / 2) - (sprite.getWidth()),
                        ((Gdx.graphics.getHeight() / 2) - (sprite.getHeight())),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);
                sprite.flip(false, true);

                setBounds((Gdx.graphics.getWidth() / 2) - (sprite.getWidth()),
                        ((Gdx.graphics.getHeight() / 2) - (sprite.getHeight())),
                        Gdx.graphics.getWidth() * 0.15f,
                        Gdx.graphics.getHeight() * 0.15f);
                setName("escudo4");

                break;
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        sprite.setScale(getScaleX(), getScaleY());
        sprite.setRotation(getRotation());
        sprite.setPosition(getX(), getY());
        sprite.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public MeshSpawnShapeValue.Triangle getTriangle() {

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        ShapeRenderer spriteDebugger;
        spriteDebugger = new ShapeRenderer();

        MeshSpawnShapeValue.Triangle t = new MeshSpawnShapeValue.Triangle(getX(), getY(), 0,
                getX() + getHeight(), getX() + getHeight(), 0,
                getY() + getWidth(), getY() + getWidth(), 0);

        spriteDebugger.setProjectionMatrix(normalProjection);
        spriteDebugger.setColor(Color.GREEN);


        spriteDebugger.begin(ShapeRenderer.ShapeType.Line);
        if (modo == 1) {


        }
        if (modo == 2) {
            spriteDebugger.curve(getX() + getWidth(), getY(),
                    getX() + getWidth() * 0.6f, getY() + getHeight() * 0.6f,
                    getX() + getWidth() * 0.8f, getY() + getHeight() * 0.8f,
                    getX(), getY() + getHeight(), 10
            );
        }
        if (modo == 3) {
            spriteDebugger.curve(getX(), getY(),
                    getX() + getWidth() / 2, getY() + getHeight() / 4,
                    getX() + getWidth() / 2, getY() + getHeight() / 4,
                    getX() + getWidth(), getY() + getHeight(), 10
            );


        }
        if (modo == 4) {
            spriteDebugger.curve(getX(), getY() + getHeight(),
                    getX() + getWidth() / 2, getY() + getHeight() / 10,
                    getX() + getWidth() / 2, getY() + getHeight() / 10,
                    getX() + getWidth(), getY(), 10
            );
        }

        /*
        switch (modo) {
            case 1:
                ShapeRenderer spriteDebugger2;
                spriteDebugger2 = new ShapeRenderer();
                t = new MeshSpawnShapeValue.Triangle(getX(), getY(), 0,
                        getX() + getHeight(), getX() + getHeight(), 0,
                        getY() + getWidth(), getY() + getWidth(), 0);
                spriteDebugger2.setProjectionMatrix(normalProjection);

                spriteDebugger2.begin(ShapeRenderer.ShapeType.Filled);
                spriteDebugger2.setColor(Color.PURPLE);

                spriteDebugger2.triangle(getX(), getY(),
                        getX() + getWidth(), getY() + getHeight(),
                        getX() + getWidth(), getY());
                spriteDebugger2.end();

                return t;

        }
        */
        spriteDebugger.end();
        return t;
    }

    public Polygon getLine() {
        float[] vertices;
        if (modo == 1) {

            vertices = new float[]{getX(),
                    getY(),
                    getX() + getHeight() / 4,
                    getY() + getWidth() / 4,
                    getX() + getHeight() / 2,
                    getY() + getWidth() / 2,
                    getX() + getWidth(),
                    getY() + getHeight()};
        } else if (modo == 2) {

            vertices = new float[]{
                    getX() + getWidth(),
                    getY(),
                    getX() + getWidth() * 0.6f,
                    getY() + getHeight() * 0.6f,
                    getX() + getWidth() * 0.8f,
                    getY() + getHeight() * 0.8f,
                    getX(),
                    getY() + getHeight()
            };

        } else if (modo == 3) {
            vertices = new float[]{
                    getX(), getY(),
                    getX() + getWidth() / 2, getY() + getHeight() / 4,
                    getX() + getWidth() / 2, getY() + getHeight() / 4,
                    getX() + getWidth(), getY() + getHeight()
            };
        } else {
            vertices = new float[]{
                    getX(),
                    getY() + getHeight(),
                    getX() + getWidth() / 4,
                    getY() + getHeight() / 4,
                    getX() + getWidth() / 2,
                    getY() + getHeight() / 2,
                    getX() + getWidth(), getY()
            };
        }

        return new Polygon(vertices);

    }
}