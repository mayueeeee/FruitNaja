package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	public  static SpriteBatch batch;
	private Texture img;
	private Texture imgB;
	private Texture grid1;
	private Texture grid2;
	private Texture grid3;
	private Texture grid4;
	private Texture grid5;
	private Sprite bgSprite;
	private long lastHitTime;
	int x = 0,y = 0;
	Person heal = new Charactor("U",200,100,0,1);
	Person stun = new Charactor("fluk",200,100,1,2);
	Person shield = new Charactor("Baitoey",200,100,2,3);
	Person trap = new Charactor("Tik",200,100,3,4);
	Person poison = new Charactor("Myuu",200,100,4,5);
	public static Camera camera1;
	public static Camera camera2;
	private float camSpeed = 100f;
	private double deltatime;
	private Vector3 mouse_position = new Vector3(0, 0, 0);
	private TextureRegion [][] healer;
	private TextureRegion [][] stuner;
	private TextureRegion [][] shielder;
	private TextureRegion [][] traper;
	private TextureRegion [][] poisoner;


	TextureRegion[] animationframeS = new TextureRegion[2];
	Animation animationS;
	TextureRegion[] animationframeW = new TextureRegion[2];
	Animation animationW;
	TextureRegion[] animationframeA = new TextureRegion[2];
	Animation animationA;
	TextureRegion[] animationframeD = new TextureRegion[2];
	Animation animationD;


	float etime;


	@Override
	public void create () {
		camera1 = new OrthographicCamera(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
		camera1.position.x = Gdx.graphics.getWidth() / 4;
		camera1.position.y = Gdx.graphics.getHeight() / 2;
		camera1.update();

		camera2 = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		camera2.position.x = Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 4);
		camera2.position.y = Gdx.graphics.getHeight() / 2;
		camera2.update();

		batch = new SpriteBatch();
		img = new Texture("chai.png");
		imgB = new Texture("map/bg-glass.jpg");
		grid1 = new Texture("sprite/Grid01.png");
		grid2 = new Texture("sprite/Grid02.png");
		grid3 = new Texture("sprite/Grid03.png");
		grid4 = new Texture("sprite/Grid04.png");
		grid5 = new Texture("sprite/Grid05.png");


		healer = TextureRegion.split(grid1,127,182);
		stuner = TextureRegion.split(grid1,127,182);
		shielder = TextureRegion.split(grid1,127,182);
		traper = TextureRegion.split(grid1,127,182);
		poisoner = TextureRegion.split(grid1,127,182);

		setAnimation(healer);
		setAnimation(shielder);
		setAnimation(stuner);
		setAnimation(traper);
		setAnimation(poisoner);
	}

	public void setAnimation(TextureRegion [][] ani){
		int index = 0;
		int i = 0;
		//walk down
		for (int j = 1 ;  j < 3 ;j++){
			animationframeS[index++] = ani[i][j];
		}
		animationS = new Animation(1f/2f,animationframeS);

		//walk up
		index = 0;
		i=1;
		for (int j = 1 ;  j < 3 ;j++){
			animationframeW[index++] = ani[i][j];
		}
		animationW = new Animation(1f/2f,animationframeW);

		//walk Left
		index = 0;
		i=2;
		for (int j = 1 ;  j >= 0 ;j--){
			animationframeA[index++] = ani[i][j];
		}
		animationA = new Animation(1f/2f,animationframeA);

		//walk Right
		index = 0;
		i=3;
		for (int j = 0 ;  j < 2 ;j++){
			animationframeD[index++] = ani[i][j];
		}
		animationD = new Animation(1f/2f,animationframeD);
	}

	public void setCamera(){
		if (Gdx.input.isKeyPressed(Input.Keys.A)&&camera1.position.x>512) {

			camera1.position.x -= camSpeed * deltatime;
			batch.draw((TextureRegion) animationA.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);


		}

		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if(heal.getPos().x>(Gdx.graphics.getWidth()/2)){
				camera1.position.x += camSpeed * deltatime;
			}
			else {
				camera1.position.x += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) animationD.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&camera1.position.y>300) {
			camera1.position.y -= camSpeed * deltatime;
			batch.draw((TextureRegion) animationS.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if(heal.getPos().y>(Gdx.graphics.getHeight()/2)){
				camera1.position.y += camSpeed * deltatime;
			}
			else {
				camera1.position.y += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) animationW.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
		}
		else {
			batch.draw(healer[0][0], heal.getPos().x,heal.getPos().y );
		}
	}

	public void setCamera2(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&camera2.position.x>512) {

			camera2.position.x -= camSpeed * deltatime;
			batch.draw((TextureRegion) animationA.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);


		}

		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(heal.getPos().x>(Gdx.graphics.getWidth()/2)){
				camera2.position.x += camSpeed * deltatime;
			}
			else {
				camera2.position.x += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) animationD.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&camera1.position.y>300) {
			camera2.position.y -= camSpeed * deltatime;
			batch.draw((TextureRegion) animationS.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if(heal.getPos().y>(Gdx.graphics.getHeight()/2)){
				camera2.position.y += camSpeed * deltatime;
			}
			else {
				camera2.position.y += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) animationW.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
		}
		else {
			batch.draw(healer[0][0], heal.getPos().x,heal.getPos().y );
		}
	}


	@Override
	public void render () {
		etime += Gdx.graphics.getDeltaTime();
		deltatime = Gdx.graphics.getDeltaTime();

		if (Gdx.input.isKeyPressed(Input.Keys.G)&&TimeUtils.nanoTime()-lastHitTime>1000000000){
			heal.setHp(heal.getHp()-50);
			lastHitTime = TimeUtils.nanoTime();
		}


		heal.die();
		if(heal.isLive()==false){
			System.exit(0);
		}



//		System.out.println(camera.position.y);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		/*Left Half*/
		Gdx.gl.glViewport( 0,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		//Set up camera with viewport in mind
		camera1.update();
		batch.setProjectionMatrix(camera1.combined);
		batch.begin();
		batch.draw(imgB,0,0);
		setCamera();
//		heal.setPos(new Vector2(camera2.position.x,camera2.position.y));
		heal.setPos(new Vector2(camera1.position.x,camera1.position.y));
//		heal.move();
		heal.useSkill();
		batch.end();
		System.out.println(heal.getHp());


   		 /*Right Half*/
		Gdx.gl.glViewport( Gdx.graphics.getWidth()/2,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		//Set up camera again with other viewport in mind
		camera2.update();
		batch.setProjectionMatrix(camera2.combined);
		batch.begin();
		batch.draw(imgB,0,0);
		setCamera2();
		heal.setPos(new Vector2(camera2.position.x,camera2.position.y));
//		heal.setPos(new Vector2(camera1.position.x,camera1.position.y));
//		heal.move();
		heal.useSkill();
		batch.end();
		System.out.println(heal.getHp());
//
//
// 		System.out.println(heal.getPos().y);

//		System.out.println(heal.getStamina());











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
