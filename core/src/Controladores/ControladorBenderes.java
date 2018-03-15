package Controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;

import java.util.ArrayList;

import Actores.ActorBender;

/**
 * Clase para controlar los benders activos
 * Created by Triboga on 26/2/18.
 */

public class ControladorBenderes {

    public static ArrayList<ActorBender> benders = new ArrayList<ActorBender>();

    /**
     * Aniadir benders a la lista
     * @param bender bender a añadir
     */
    public void aniadirBender(ActorBender bender){
        benders.add(bender);

    }

}
