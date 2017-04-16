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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
	private Texture [] fruit = new Texture[7];
	private Texture [] deco = new Texture[3];
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
	private TextureRegion [][] healer;
	private TextureRegion [][] stuner;
	private TextureRegion [][] shielder;
	private TextureRegion [][] traper;
	private TextureRegion [][] poisoner;
	int push1 = 0;
	int push2 = 0;


	float etime;



	@Override
	public void create () {
		camera1 = new OrthographicCamera(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
		camera1.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera1.update();

		camera2 = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		camera2.position.set(500+Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera2.update();

		batch = new SpriteBatch();
		img = new Texture("chai.png");
		imgB = new Texture("map/bg-green.jpg");
		grid1 = new Texture("sprite/Grid01.png");
		grid2 = new Texture("sprite/Grid02.png");
		grid3 = new Texture("sprite/Grid03.png");
		grid4 = new Texture("sprite/Grid04.png");
		grid5 = new Texture("sprite/Grid05.png");
		for(int q = 0; q<7;q++){
			int t =q+1;
			fruit[q] = new Texture("fruits/"+t+".png");
		}
		for(int q = 0; q<3;q++){
			int t =q+1;
			deco[q] = new Texture("Deco/b"+t+".png");
		}

		healer = TextureRegion.split(grid2,127,182);
		shielder = TextureRegion.split(grid1,127,182);
		stuner = TextureRegion.split(grid3,127,182);
		poisoner = TextureRegion.split(grid4,127,182);
		traper = TextureRegion.split(grid5,127,182);


		setAnimation(healer,heal);
		setAnimation(shielder,shield);
		setAnimation(stuner,stun);
		setAnimation(traper,trap);
		setAnimation(poisoner,poison);

	}

	@Override
	public void resize(int width, int height){

	}

	public void setAnimation(TextureRegion [][] ani,Person person){
		int index = 0;
		int i = 0;
		//walk down
		for (int j = 1 ;  j < 3 ;j++){
			person.animationframeS[index++] = ani[i][j];
		}
		person.animationS = new Animation(1f/2f,person.animationframeS);

		//walk up
		index = 0;
		i=1;
		for (int j = 1 ;  j < 3 ;j++){
			person.animationframeW[index++] = ani[i][j];
		}
		person.animationW = new Animation(1f/2f,person.animationframeW);

		//walk Left
		index = 0;
		i=2;
		for (int j = 1 ;  j >= 0 ;j--){
			person.animationframeA[index++] = ani[i][j];
		}
		person.animationA = new Animation(1f/2f,person.animationframeA);

		//walk Right
		index = 0;
		i=3;
		for (int j = 0 ;  j < 2 ;j++){
			person.animationframeD[index++] = ani[i][j];
		}
		person.animationD = new Animation(1f/2f,person.animationframeD);

		i=2;
		for (int z = 0; z<10 ;z++) {
			person.animationframeAttackLeft[z] = ani[i][3];
		}
		person.animationAttackLeft = new Animation(1f/2f,person.animationframeAttackLeft);

		i=3;
		for (int z = 0; z<10 ;z++) {
			person.animationframeAttackRight[z] = ani[i][3];
		}
		person.animationAttackRight = new Animation(1f/2f,person.animationframeAttackRight);
	}

	public void setCamera(Person person,TextureRegion [][] hero){
		if (Gdx.input.isKeyPressed(Input.Keys.A)&&camera1.position.x>512) {

			camera1.position.x -= camSpeed * deltatime;
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
			push1 = 1;

		}

		else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			if(person.getPos().x>(Gdx.graphics.getWidth()/2)){
				camera1.position.x += camSpeed * deltatime;
			}
			else {
				camera1.position.x += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
			push1 = 2;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&camera1.position.y>300) {
			camera1.position.y -= camSpeed * deltatime;
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			if(person.getPos().y>(Gdx.graphics.getHeight()/2)){
				camera1.position.y += camSpeed * deltatime;
			}
			else {
				camera1.position.y += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.V)){
			if (push1 == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),camera1.position.x,camera1.position.y);
			}
		}
		else {
			batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
		}
	}

	public void setCamera2(Person person,TextureRegion [][] hero){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera2.position.x -= camSpeed * deltatime;
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
			push2 = 1;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(person.getPos().x>(Gdx.graphics.getWidth()/2)){
				camera2.position.x += camSpeed * deltatime;
			}
			else {
				camera2.position.x += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
			push2 = 2;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera2.position.y -= camSpeed * deltatime;
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			if(person.getPos().y>(Gdx.graphics.getHeight()/2)){
				camera2.position.y += camSpeed * deltatime;
			}
			else {
				camera2.position.y += camSpeed/2 * deltatime;
			}
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			if (push2 == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),camera2.position.x,camera2.position.y);
			}
		}
		else {
		batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
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
		camera1.update();
		batch.setProjectionMatrix(camera1.combined);
		batch.begin();
		batch.draw(imgB,0,0);
		batch.draw(deco[0],(float) (Math.random()*100),(float) (Math.random()*100),100,100);
		if((camera1.position.x>960&&camera1.position.x<1000)&&(camera1.position.y<1040&&camera1.position.y>995)||(camera2.position.x>960&&camera2.position.x<1000)&&(camera2.position.y<1040&&camera2.position.y>995)){
			batch.enableBlending();
		}
		else {
			batch.draw(fruit[0],1000,1000,80,80);
		}
		setCamera(trap,traper);
		setCamera2(stun,stuner);
		trap.setPos(new Vector2(camera1.position.x,camera1.position.y));
		batch.end();


   		 /*Right Half*/
		Gdx.gl.glViewport( Gdx.graphics.getWidth()/2,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		camera2.update();
		batch.setProjectionMatrix(camera2.combined);
		batch.begin();
		batch.draw(imgB,0,0);
		if((camera1.position.x>960&&camera1.position.x<1000)&&(camera1.position.y<1040&&camera1.position.y>995)||(camera2.position.x>960&&camera2.position.x<1000)&&(camera2.position.y<1040&&camera2.position.y>995)){
			batch.enableBlending();
		}
		else {
			batch.draw(fruit[0],1000,1000,80,80);
		}
		stun.setPos(new Vector2(camera2.position.x,camera2.position.y));
		setCamera(trap,traper);
		setCamera2(stun,stuner);
		batch.end();
//
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
