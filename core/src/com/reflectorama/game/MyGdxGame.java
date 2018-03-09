package com.reflectorama.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import Pantallas.GameOver;
import Pantallas.NaveEspacio;
import Utiles.BaseDatosReflect;
import Utiles.MyGameCallback;

public class MyGdxGame extends Game {

	public Screen currentScreen;

	public NaveEspacio naveScreen;

    public MyGameCallback myGameCallback;

    public GameOver gameOver;

    public BaseDatosReflect bdr;

	@Override
	public void create () {

		naveScreen= new NaveEspacio(this);
		currentScreen=naveScreen;
		gameOver=new GameOver(this, naveScreen.getPuntuacion());

		setScreen(naveScreen);
	}




	public MyGdxGame(MyGameCallback myGameCallback, BaseDatosReflect baseDatosReflect) {
		super();
		this.myGameCallback = myGameCallback; // initialize in constructor
        this.bdr=baseDatosReflect;

	}


}
