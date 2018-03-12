package Actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Triboga on 8/2/18.
 */

public class ActorNave extends Actor {

    protected Sprite sprite;
    protected boolean colliding;
    private int contador;
    private ShapeRenderer spriteDebugger;

    private Polygon polygon;

    public ActorNave(Texture t) {
        super();
         spriteDebugger=new ShapeRenderer();
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

        polygon = new Polygon(new float[]{
                getX()+getWidth()/9,getY()+getHeight()/9,
                getX()+getWidth()-getWidth()/9,getY()+getHeight()/9,
                getX()+getWidth()-getWidth()/9, getY()+getHeight()-getHeight()/9,
                getX()+getWidth()/9,getY()+getHeight()-getHeight()/9
        });
        polygon.setOrigin((getX()+getWidth())/2, (getY()+getHeight())/2);

        colliding=false;
    }

    public Polygon getHitBoxShape() {

        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        Polygon polygon = getPolygon();
        polygon.setOrigin((getX()+getWidth())/2, (getY()+getHeight())/2);



        spriteDebugger.setProjectionMatrix(normalProjection);
        spriteDebugger.begin(ShapeRenderer.ShapeType.Line);
        spriteDebugger.setColor(Color.GREEN);
        spriteDebugger.polygon(polygon.getTransformedVertices());

        spriteDebugger.end();

        return polygon;

    }

    public Polygon getPolygon(){

        return polygon;
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

        return Intersector.overlapConvexPolygons(getPolygon(), bender.getPligon());
    }


    public Sprite getSprite() {
        return sprite;
    }
}
