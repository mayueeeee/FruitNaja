package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	int x = 0,y = 0;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("chai.png");

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//		y-=200*Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(img, x, y);
		batch.end();




	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
