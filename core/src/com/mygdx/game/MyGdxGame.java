package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		ScreenManager.getInstance().initialize(this);
		Resources.getInstance().intialize();

		Resources.getInstance().loadAssets();
		ScreenManager.getInstance().show(Screen.MAIN_MENU);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		ScreenManager.getInstance().dispose();
	}
}
