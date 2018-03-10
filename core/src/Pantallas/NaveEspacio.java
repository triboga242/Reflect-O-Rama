package Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.reflectorama.game.MyGdxGame;

import java.util.ArrayList;
import java.util.Random;

import Actores.ActorBender;
import Actores.ActorEscudo;
import Actores.ActorGameOver;
import Actores.ActorNave;
import Actores.ActorVida;
import Controladores.ControladorBenderes;
import Controladores.EscuchadorPantalla;
import Utiles.Explosion;


/**
 * Created by Triboga on 8/2/18.
 * gogo
 */

public class NaveEspacio extends PantallaBase {

    private Stage escena;

    private SpriteBatch spriteBatch;
    private Group actores;

    private ActorNave nave;
    private ActorEscudo e1;
    private ActorEscudo e2;
    private ActorEscudo e3;
    private ActorEscudo e4;

    private ActorVida actorVida1;
    private ActorVida actorVida2;
    private ActorVida actorVida3;

    private ControladorBenderes controlBender;
    private int numeroBenders;
    private Random randomGenerator;

    private Texture naveTextura;
    private Texture benderTextura;
    private Texture fondo;

    private ArrayList<Explosion> explosions;

    private InputMultiplexer multiplexer;

    private int vida;

    public int getPuntuacion() {
        return puntuacion;
    }

    private int puntuacion;
    private static final int VIDA_INICIAL = 3;

    private float altoPantalla;
    private float anchoPantalla;

    private boolean debugmode;

    private EscuchadorPantalla escuchadorPantalla;

    private static final int BENDERS_FINITOS=1000;
    private int contadorBenders;
    private long aux;
    private MyGdxGame game;

    public NaveEspacio() {
    }


    public NaveEspacio(final MyGdxGame g) {
        super(g);

        game = g;

        spriteBatch=new SpriteBatch();

        numeroBenders=25;
        contadorBenders=0;

        debugmode=game.myGameCallback.getDebugmode();

        altoPantalla = Gdx.graphics.getHeight();
        anchoPantalla = Gdx.graphics.getWidth();

        vida = VIDA_INICIAL;
        puntuacion = 0;

        game.myGameCallback.musicaOn();
        controlBender = new ControladorBenderes();

        escena = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        naveTextura = new Texture(Gdx.files.internal("ReflectoMatic/Nave-01-small.png/"));
        benderTextura = new Texture(Gdx.files.internal("ReflectoMatic/Bender.png"));
        actorVida1 = new ActorVida(1);
        actorVida2 = new ActorVida(2);
        actorVida3 = new ActorVida(3);

        explosions= new ArrayList<Explosion>();

        actores = new Group();
        actores.addActor(nave = new ActorNave(naveTextura));

        crearBenders(numeroBenders);

        actores.addActor(e1 = new ActorEscudo(1));
        actores.addActor(e2 = new ActorEscudo(2));
        actores.addActor(e3 = new ActorEscudo(3));
        actores.addActor(e4 = new ActorEscudo(4));

        if (!debugmode) {

            e1.setVisible(false);
            e2.setVisible(false);
            e3.setVisible(false);
            e4.setVisible(false);
        }

        actores.addActor(actorVida1);
        actores.addActor(actorVida2);
        actores.addActor(actorVida3);

        escuchadorPantalla = new EscuchadorPantalla(e1, e2, e3, e4);

        escena.addActor(actores);

        //We initialize the background
        fondo = new Texture(Gdx.files.internal("ReflectoMatic/Fondo.jpg"));
        multiplexer = new InputMultiplexer();

        Gdx.input.setInputProcessor(escuchadorPantalla);

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        BitmapFont font = new BitmapFont();
        font.setColor(Color.YELLOW);
        font.getData().setScale(3f);

        escena.getBatch().begin();
        escena.getBatch().draw(fondo, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(escena.getBatch(), "Chatarra: " + puntuacion, anchoPantalla - anchoPantalla / 4, altoPantalla - altoPantalla / 25);

        escena.getBatch().end();
        escena.act(delta); //Realizamos las acciones dibujando el tiempo transcurrido entre frame y frame
        if (debugmode) {
            e1.getTriangle();
            e2.getTriangle();
            e3.getTriangle();
            e4.getTriangle();

            nave.getHitBoxShape();

            for (ActorBender b : controlBender.benders) {

                b.getRectangle();
                b.getHitBox();
            }
        }
        ArrayList<ActorBender> bendersMuertos = new ArrayList<ActorBender>();
        ArrayList<Explosion> explosionesExplotadas = new ArrayList<Explosion>();



        for (Explosion e: explosions){
            e.update(delta);
            if (e.remove){
                e.clear();
                e.remove();
                explosionesExplotadas.add(e);
            }
        }


        explosions.removeAll(explosionesExplotadas);

        for (Explosion e: explosionesExplotadas){
            e.clear();
            e.remove();
        }


        for (ActorBender b : controlBender.benders) {

            for (Explosion e: explosions){
                e.render((spriteBatch));
            }


            //A veces hay colisiona con dos escudos a la vez, podria solucionarlo poniendo un boleano que controle que solo cuente una
            //colision, pero lo dejo para que sea un tipo de "bonus".

            if (isCollision(e1, b) && e1.isVisible()) {
                bendersMuertos.add(b);

                Explosion explosion= new Explosion(b.getX(), b.getY());
                actores.addActor(explosion);
                explosionesExplotadas.add(explosion);

                puntuacion++;
                b.clear();
                b.remove();
                Gdx.app.log("Escudo1 colisiona", "BUUUUUUUUUM");
            }

            if (isCollision(e2, b) && e2.isVisible()) {
                bendersMuertos.add(b);
                Explosion explosion= new Explosion(b.getX(), b.getY());
                actores.addActor(explosion);
                explosionesExplotadas.add(explosion);
                puntuacion++;
                b.clear();
                b.remove();
                Gdx.app.log("Escudo2 colisiona", "BUUUUUUUUUM");
            }
            if (isCollision(e3, b) && e3.isVisible()) {
                bendersMuertos.add(b);
                Explosion explosion= new Explosion(b.getX(), b.getY());
                actores.addActor(explosion);
                explosionesExplotadas.add(explosion);
                puntuacion++;
                b.clear();
                b.remove();
                Gdx.app.log("Escudo3 colisiona", "BUUUUUUUUUM");
            }
            if (isCollision(e4, b) && e4.isVisible()) {
                bendersMuertos.add(b);
                Explosion explosion= new Explosion(b.getX(), b.getY());
                actores.addActor(explosion);
                explosionesExplotadas.add(explosion);
                puntuacion++;
                b.remove();
                b.clear();
                Gdx.app.log("Escudo4 colisiona", "BUUUUUUUUUM");
            }


            if (nave.colisiona(b)) {
                Explosion explosion= new Explosion(b.getX(), b.getY());
                actores.addActor(explosion);
                explosionesExplotadas.add(explosion);
                bendersMuertos.add(b);
                b.remove();
                b.clear();
                vida--;

                if (actorVida3.isVisible()) {
                    actorVida3.setVisible(false);
                } else if (actorVida2.isVisible()) {
                    actorVida2.setVisible(false);
                } else if (actorVida1.isVisible()) {
                    actorVida1.setVisible(false);
                }


                if (vida == 0) {

                    gameOver();

                    escena.addAction(Actions.sequence(Actions.delay(2.5f),
                            Actions.run(new Runnable() {
                                @Override
                                public void run() {


                                    if (!debugmode) {
                                        game.bdr.saveCurrentGame(puntuacion);
                                    }
                                    try {
                                        finalize();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                    for (ActorBender a : controlBender.benders) {
                                        a.remove();
                                        a.clear();
                                    }
                                    controlBender.benders.clear();

                                    Gdx.app.exit();
                                }
                            })));


                }
            }
        }
        controlBender.benders.removeAll(bendersMuertos);

        if (game.myGameCallback.getCrazyMode()) {
            if (ControladorBenderes.benders.size() <= 10) {
                crearBenders(numeroBenders);
            }
        }else {

            if (contadorBenders<BENDERS_FINITOS && controlBender.benders.size() <= 10){
                crearBenders(numeroBenders);
                Gdx.app.log("CREANDO", "25 VENDERS MAS, VAN "+contadorBenders);
            }
            Gdx.app.log("CREADOS", "VENDERs "+contadorBenders);
            Gdx.app.log("VIVOS", "VENDERs "+controlBender.benders.size());

        }

        escena.draw();
    }


    private void crearBenders(int cantidad){
        randomGenerator = new Random();

        aux = 0;
        for (int i = 0; i < cantidad; i++) {

            aux += (long) new Random().nextInt(4);
            ActorBender b = new ActorBender(benderTextura, randomGenerator.nextInt(9) + 3, aux + 1, game);
            actores.addActor(b);
            controlBender.aniadirBender(b);
            contadorBenders++;
        }

    }

    private boolean isCollision(ActorEscudo escudo, ActorBender bender) {

        return Intersector.overlapConvexPolygons(escudo.getLine(), bender.getPligon());
    }

    public void GameInit(){
        contadorBenders=0;
        vida=VIDA_INICIAL;
        puntuacion=0;
        escuchadorPantalla = new EscuchadorPantalla(e1, e2, e3, e4);
        Gdx.input.setInputProcessor(escuchadorPantalla);

    }

    private void gameOver(){

        ActorGameOver gameOver = new ActorGameOver(nave);

        RotateByAction ra = new RotateByAction();
        ra.setAmount(30);
        ra.setDuration(1);

        MoveToAction esperaNave=new MoveToAction();
        esperaNave.setDuration(1.5f);

        MoveToAction moveToActionfueraNave=new MoveToAction();
        moveToActionfueraNave.setDuration(1.5f);
        moveToActionfueraNave.setPosition(anchoPantalla*2, altoPantalla*2);

        nave.addAction(new SequenceAction(ra,esperaNave, moveToActionfueraNave));
        escena.addActor(gameOver);
    }

    @Override
    public void resize(int width, int height) {
        escena.getBatch().begin();
        escena.getBatch().draw(fondo, 0, 0, width, height);
        escena.getBatch().end();
    }

    @Override
    public void dispose() {
        super.dispose();
        escena.dispose();
        escuchadorPantalla.dispose();
        fondo.dispose();

        game.myGameCallback.musicaOff();
    }

    @Override
    public void pause() {
        super.pause();
        game.myGameCallback.musicaOff();
    }


    @Override
    public void resume() {
        super.resume();
        game.myGameCallback.musicaOn();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}

