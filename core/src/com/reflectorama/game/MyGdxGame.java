package com.reflectorama.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import Pantallas.NaveEspacio;
import Utiles.BaseDatosReflect;
import Utiles.MyGameCallback;

public class MyGdxGame extends Game {

	public Screen currentScreen;
	public NaveEspacio naveScreen;
    public MyGameCallback myGameCallback;
    public BaseDatosReflect bdr;

	@Override
	public void create () {

		naveScreen= new NaveEspacio(this);
		currentScreen=naveScreen;
		setScreen(naveScreen);
	}


	/**
	 * Constructor de MyGame para pasar
	 * @param myGameCallback interface de la que extiende para pasar la configuracion
	 * @param baseDatosReflect base de datos para poder guardar la puntuacion
	 */
	public MyGdxGame(MyGameCallback myGameCallback, BaseDatosReflect baseDatosReflect) {
		super();
		this.myGameCallback = myGameCallback; // initialize in constructor
        this.bdr=baseDatosReflect;
	}

}
