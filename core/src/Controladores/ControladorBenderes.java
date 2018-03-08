package Controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import java.util.ArrayList;

import Actores.ActorBender;

/**
 * Created by Triboga on 26/2/18.
 */

public class ControladorBenderes {

    public static ArrayList<ActorBender> benders = new ArrayList<ActorBender>();
    private ShapeRenderer spriteDebugger;


    public void aniadirBender(ActorBender bender){
        benders.add(bender);

    }

    public void htBenderes() {
        Matrix4 normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());

        spriteDebugger.setProjectionMatrix(normalProjection);
        spriteDebugger.begin(ShapeRenderer.ShapeType.Filled);
        spriteDebugger.setColor(Color.GREEN);


        for (ActorBender b : benders) {
            spriteDebugger.rect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        }
        spriteDebugger.end();

    }
}
