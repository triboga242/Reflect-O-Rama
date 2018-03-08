package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Triboga on 8/2/18.
 */

public class ActorNave extends Actor {

    protected Sprite sprite;
    protected boolean colliding;
    private int contador;

    public ActorNave(Texture t) {
        super();
        contador=0;
        sprite = new Sprite(t);
        sprite.setBounds((Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth()*0.1f)) ,
                (Gdx.graphics.getHeight() / 2) - (Gdx.graphics.getHeight()*0.1f),
                Gdx.graphics.getWidth()*0.2f,
                Gdx.graphics.getHeight()*0.2f);

        setBounds((Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth()*0.1f)) ,
                (Gdx.graphics.getHeight() / 2) - (Gdx.graphics.getHeight()*0.1f),
                Gdx.graphics.getWidth()*0.2f,
                Gdx.graphics.getHeight()*0.2f);

        setName("PlanetExpress");
        colliding=false;
    }

    public Rectangle getHitBox() {
        return sprite.getBoundingRectangle();
    }
    @Override
    public void act(float delta) {
        super.act(delta);
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

    public boolean colisiona (ActorBender bender){

        boolean overlaps=getHitBox().overlaps(bender.getRectangle());
        contador++;

        if (overlaps){
            return true;
        }
        return false;
        /*
        if(overlaps&&colliding==false){
            colliding=true; //In the InputProcessor, we only listen to keydown (thus we move) only if not colliding.
            //we only do the collision act if it's the first time we detect collision, that's why we don't return colliding as the last command.
            return true;
        }else if(!overlaps){
            colliding=false;
            return false;
        }

        return false;
        */
    }


    public Sprite getSprite() {
        return sprite;
    }
}
