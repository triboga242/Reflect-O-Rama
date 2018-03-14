package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.reflectorama.game.MyGdxGame;

import java.util.Random;


/**
 * Actor bender que se van creando yposicionando en sitios aleatorios para
 * ir todos a colisionar con la nave
 *
 * Created by Triboga on 8/2/18.
 */

public class ActorBender extends Actor {
    public Sprite sprite;

    protected Random randomGenerator;
    protected int altoPantalla;
    protected int anchoPantalla;
    private int velocidad;

    private long espera;
    private long comienzo;

    private float benderAlto;
    private float benderAncho;

    private static final int MOVEMENT = 2;

    private ShapeRenderer spriteDebugger;

    private  Polygon polygon;
    private Texture texture;
    private int currentRandom;


    /**
     * Constructor para actor bender
     * @param benderTextura la textura del actor
     * @param velo velocidad que llevara el bender hacia la nave
     * @param espera tiempo que esperara antes de ir hacia la nave
     * @param game juego para compartir preferencias y configuraciones
     */
    public ActorBender(Texture benderTextura, int velo, long espera, MyGdxGame game) {
        super();


        this.espera = espera;
        this.velocidad = velo;
        this.polygon=new Polygon();
        this.texture=benderTextura;

        spriteDebugger=new ShapeRenderer();
        sprite = new Sprite(benderTextura);
        altoPantalla = Gdx.graphics.getHeight();
        anchoPantalla = Gdx.graphics.getWidth();

        //Tamaño en funcion del modo activado
        if (!game.myGameCallback.getCrazyMode()){
            benderAncho=anchoPantalla/25;
            benderAlto=anchoPantalla/12;
            this.espera = espera;
            this.velocidad = velo;

        }else{
            benderAncho=anchoPantalla/37;
            benderAlto=anchoPantalla/20;
            this.espera = espera-1;
            this.velocidad = velo+3;
        }

        randomGenerator = new Random();
        int donde = randomGenerator.nextInt(4);
        comienzo = System.currentTimeMillis();


        //Generador aleatorio de posiciones
        switch (donde) {
            case 0:
                sprite.setBounds(-benderAlto-20,
                        (float) randomGenerator.nextInt(altoPantalla) +1,
                        benderAncho ,
                        benderAlto);

                setBounds(-benderAlto-20,
                        (float) randomGenerator.nextInt(altoPantalla) +1,
                        benderAncho ,
                        benderAlto);
                break;
            case 1:
                sprite.setBounds((float) anchoPantalla + benderAlto+20,
                        (float) randomGenerator.nextInt(altoPantalla) +1,
                        benderAncho  ,
                        benderAlto );

                setBounds((float) anchoPantalla + benderAlto+20,
                        (float) randomGenerator.nextInt(altoPantalla) +1,
                        benderAncho  ,
                        benderAlto );

                break;
            case 2:
                sprite.setBounds((float) randomGenerator.nextInt(anchoPantalla)+1,
                        -benderAlto-20,
                        benderAncho ,
                        benderAlto);

                setBounds((float) randomGenerator.nextInt(anchoPantalla)+1,
                        -benderAlto-20,
                        benderAncho ,
                        benderAlto);
                break;
            case 3:
                sprite.setBounds((float) randomGenerator.nextInt(anchoPantalla)+1,
                        (float) altoPantalla + benderAlto+20,
                        benderAncho ,
                        benderAlto);

                setBounds((float) randomGenerator.nextInt(anchoPantalla)+1,
                        (float) altoPantalla + benderAlto+20,
                        benderAncho ,
                        benderAlto);
                break;
        }

        //Tiempo de espera
        MoveToAction mv1 = new MoveToAction();
        mv1.setDuration(espera);
        mv1.setPosition(getX(), getY());

        //Movimiento hacia la nave
        MoveToAction mv2 = new MoveToAction();
        mv2.setDuration(velocidad);
        mv2.setPosition(anchoPantalla / 2, altoPantalla / 2);

        //Rotacion del bender
        RotateByAction ra= new RotateByAction();
        ra.setAmount(1270);
        ra.setDuration(velocidad);

        this.addAction(new SequenceAction(mv1, new ParallelAction(mv2, ra)));

    }

    @Override
    public void act(float deltaTime) {

        super.act(deltaTime);

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);

        sprite.setScale(getScaleX(), getScaleY());
        sprite.setRotation(getRotation());
        sprite.setPosition(getX(), getY());
        sprite.setColor(getColor().r, getColor().g, getColor().b, getColor().a);
        sprite.draw(batch);
    }

    /**
     * Dibuja el contorno del poligono de los benders
     * @return poligono de colision de los benders
     */
    public Polygon getHitBox() {

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        polygon.setVertices(new float[]{getX(),getY(),
                getX()+getWidth(),getY(),
                getX()+getWidth(), getY()+getHeight(),
                getX(),getY()+getHeight()});

        polygon.setOrigin((getX()+getWidth())/2, (getY()+getHeight())/2);

        spriteDebugger.setProjectionMatrix(normalProjection);
        spriteDebugger.begin(ShapeRenderer.ShapeType.Line);
        spriteDebugger.setColor(Color.GREEN);
        spriteDebugger.polygon(polygon.getTransformedVertices());

        spriteDebugger.end();

        return polygon;

    }

    /**
     *
     * @return poligono de colision del bender
     */
    public Polygon getPligon(){


        polygon.setVertices(new float[]{getX(),getY(),
                getX()+getWidth(),getY(),
                getX()+getWidth(), getY()+getHeight(),
                getX(),getY()+getHeight()});


        return polygon;
    }

    /**
     *
     * @return rectangulo del sprite
     */
    public Rectangle getRectangle(){return sprite.getBoundingRectangle();}

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean remove() {
        return super.remove();

    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
