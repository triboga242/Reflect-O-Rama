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

		naveScreen= new NaveEspacio();

		currentScreen=new NaveEspacio(this);

		setScreen(currentScreen);
	}




	public MyGdxGame(MyGameCallback myGameCallback, BaseDatosReflect baseDatosReflect) {
		super();
		this.myGameCallback = myGameCallback; // initialize in constructor
        this.bdr=baseDatosReflect;

	}


}
