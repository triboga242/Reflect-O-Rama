package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;


/**
 * Cartel de Game Over que sale al finalizar la partida
 *
 * Created by Triboga on 10/3/18.
 */

public class ActorGameOver extends Actor {


    private Sprite sprite;

    /**
     * Constructor del actor
     *
     * @param nave nave para tomar puntos de referencia
     */
    public ActorGameOver(ActorNave nave) {

        float altoPantalla= Gdx.graphics.getHeight();
        float anchoPantalla=Gdx.graphics.getWidth();

        Texture cartel = new Texture("ReflectoMatic/gameover.png");
        sprite = new Sprite(cartel);

        sprite.setBounds(anchoPantalla*0.6f, altoPantalla/2, anchoPantalla/8, altoPantalla/15);
        setBounds(anchoPantalla*0.6f, altoPantalla/2, anchoPantalla/8, altoPantalla/15);

        RotateByAction rotateByAction = new RotateByAction();
        rotateByAction.setDuration(0.5f);
        rotateByAction.setAmount(20);

        ScaleByAction scaleByAction = new ScaleByAction();
        scaleByAction.setAmount(2.2f);
        scaleByAction.setDuration(1);

        MoveToAction moveToAction=new MoveToAction();
        moveToAction.setDuration(1);
        moveToAction.setPosition(nave.getX(), nave.getY());

        MoveToAction moveToActionfueraLetrero=new MoveToAction();
        moveToActionfueraLetrero.setDuration(1.5f);
        moveToActionfueraLetrero.setPosition(anchoPantalla*2, altoPantalla*2);


        SequenceAction sequenceAction = new SequenceAction(scaleByAction, rotateByAction);
        ParallelAction parallelAction= new ParallelAction(moveToAction, sequenceAction);
        SequenceAction sequenceActionFuera = new SequenceAction(parallelAction, moveToActionfueraLetrero);
        this.addAction(sequenceActionFuera);


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

}
