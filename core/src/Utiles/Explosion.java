package Utiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Triboga on 10/3/18.
 */

public class Explosion extends Actor {
    public static final float FRAME_LENGTH = 0.2f;
    public static final int OFFSET = 8;
    public static final int SIZE = 64;
    public static final int IMAGE_SIZE = 32;

    private static Animation anim = null;
    float x, y;
    float statetime;

    public boolean remove = false;

    public Explosion (float x, float y) {
        this.x = x - OFFSET;
        this.y = y - OFFSET;
        statetime = 0;

        if (anim == null)
            anim = new Animation(FRAME_LENGTH, TextureRegion.split(new Texture("ReflectoMatic/explosion.png"), IMAGE_SIZE, IMAGE_SIZE)[0]);

    }

    public void update (float deltatime) {
        statetime += deltatime;
        if (anim.isAnimationFinished(statetime))
            remove = true;
    }

    public void render (SpriteBatch batch) {


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        TextureRegion textureRegion = (TextureRegion) anim.getKeyFrame(statetime);

        batch.draw(textureRegion, x, y, SIZE, SIZE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        statetime += delta;
        if (anim.isAnimationFinished(statetime)) {
            remove = true;
            this.remove();
        }
    }


}