package Actores;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Iconos de vida con la cara de Fry
 * Created by Triboga on 6/3/18.
 */

public class ActorVida extends Actor {

    private Texture t;
    private Sprite sprite;

    /**
     * Constructor del actor
     * @param modo si es la primera, segunda o tercera vida
     */
    public ActorVida(int modo){


        float altoPantalla=Gdx.graphics.getHeight();
        float anchoPantalla=Gdx.graphics.getWidth();
        float anchoVida=anchoPantalla/17;
        float altoVida = anchoVida*1.2f;

        t = new Texture("ReflectoMatic/vidasfry.png");
        this.sprite = new Sprite(t);

        switch (modo) {
            case 1:
            sprite.setBounds(anchoPantalla / 60,
                    altoPantalla - altoVida,
                    anchoVida,
                    altoVida);

            setBounds(anchoPantalla / 60,
                    altoPantalla - altoVida,
                    anchoVida,
                    altoVida);
            break;
            case 2:
                sprite.setBounds(anchoPantalla / 60 + (anchoVida*1.2f),
                        altoPantalla - altoVida,
                        anchoVida,
                        altoVida);

                setBounds(anchoPantalla / 60 + (anchoVida*1.2f),
                        altoPantalla - altoVida,
                        anchoVida,
                        altoVida);

                break;
            case 3:
                sprite.setBounds(anchoPantalla / 60 + (anchoVida*2.4f),
                        altoPantalla - altoVida,
                        anchoVida,
                        altoVida);

                setBounds(anchoPantalla / 60 + (anchoVida*2.4f),
                        altoPantalla - altoVida,
                        anchoVida,
                        altoVida);

                break;
        }
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
