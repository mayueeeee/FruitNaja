package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	public  static SpriteBatch batch;
	private Texture img;
	private Texture imgB;
	private Sprite bgSprite;
	int x = 0,y = 0;
	Person heal = new Charactor("U",200,100,0,1);
	public static Camera camera;
	private float camSpeed = 100f;
	private double deltatime;
	private Vector3 mouse_position = new Vector3(0, 0, 0);


	@Override
	public void create () {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		camera.update();

		batch = new SpriteBatch();
		img = new Texture("chai.png");
		imgB = new Texture("map/bg-glass.jpg");


	}


	@Override
	public void render () {
		deltatime = Gdx.graphics.getDeltaTime();
		mouse_position.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(mouse_position);

		if (Gdx.input.isKeyPressed(Input.Keys.A)&& camera.position.x>512) {

			camera.position.x -= camSpeed * deltatime;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.position.x += camSpeed * deltatime;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.S)&&camera.position.y>300) {

			camera.position.y -= camSpeed * deltatime;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			camera.position.y += camSpeed * deltatime;
		}
		camera.update();
		System.out.println(camera.position.y);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		System.out.println(heal.getPos().y);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(imgB,0,0);
		batch.draw(img, heal.getPos().x,heal.getPos().y );
		batch.end();
		heal.move();




	}



//	@Override
//	public void resume() {
//	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void pause() {
	}
}
