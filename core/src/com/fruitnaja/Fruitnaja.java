package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	Texture imgB;
	int x = 0,y = 0;
	Person heal = new Charactor("U",200,100,0,1);

	
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("chai.png");
		imgB = new Texture("map/bg-glass.jpg");


	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		System.out.println(heal.getPos().x);
		y-=200*Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(imgB,0,0);
		batch.draw(img, heal.getPos().x,heal.getPos().y );
		batch.end();
		heal.move();




	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
