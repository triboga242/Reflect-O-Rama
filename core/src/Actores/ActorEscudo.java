package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;



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

    public Polygon getTriangle() {

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        ShapeRenderer spriteDebugger;
        spriteDebugger = new ShapeRenderer();

        spriteDebugger.setProjectionMatrix(normalProjection);
        spriteDebugger.begin(ShapeRenderer.ShapeType.Line);
        spriteDebugger.polygon(getLine().getVertices());
        spriteDebugger.setColor(Color.PURPLE);
        spriteDebugger.end();

        spriteDebugger.end();
        return getLine();
    }

    public Polygon getLine() {
        float[] vertices;
        if (modo == 1) {

            vertices = new float[]{
                    getX(), getY(),
                    getX() + (getWidth()*0.2f), getY() + getHeight()-getHeight()*0.2f,
                    getX() + getWidth(), getY() + getHeight()};
        } else if (modo == 2) {

            vertices = new float[]{
                    getX() + getWidth()- getWidth()*0.2f , getY() + getHeight() - getHeight()*0.2f,
                    getX() + getWidth(), getY(),
                    getX() , getY() + getHeight(),

            };

        } else if (modo == 3) {
            vertices = new float[]{
                    getX(), getY(),
                    getX() + getWidth() *0.8f, getY() + getHeight() *0.2f,
                    getX() + getWidth(), getY() + getHeight()
            };
        } else {
            vertices = new float[]{
                    getX(), getY() + getHeight(),
                    getX() + getWidth()*0.2f, getY() + getHeight()*0.2f,
                    getX() + getWidth(), getY()
            };
        }

        return new Polygon(vertices);

    }
}