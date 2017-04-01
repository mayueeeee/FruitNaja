package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
		y-=200*Gdx.graphics.getDeltaTime();
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)){
			x-=100*Gdx.graphics.getDeltaTime();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			x+=200*Gdx.graphics.getDeltaTime();
		}
		else if ((Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP))&&y<700){
			y+=500*Gdx.graphics.getDeltaTime();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			y-=100*Gdx.graphics.getDeltaTime();
		}

		if(x==600){x=0;}
		if(y<0){y=0;}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
