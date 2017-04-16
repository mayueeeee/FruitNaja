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
import com.badlogic.gdx.utils.TimeUtils;


public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	public  static SpriteBatch batch;
	private Texture img;
	private Texture imgB;
	private Texture grid1,grid2,grid3,grid4,grid5,grid6;
	private Texture [] fruit = new Texture[7];
	private Texture [] deco = new Texture[3];
	private long lastHitTime;
	private int x = 0,y = 0;
	private Person heal = new Charactor(1);
	private Person stun = new Charactor(2);
	private Person shield = new Charactor(3);
	private Person trap = new Charactor(4);
	private Person poison = new Charactor(5);

	Person player1 = Game.getPlayer(0);
	Person player2 = Game.getPlayer(1);
	public static Camera camera1;
	public static Camera camera2;
	private float camSpeed = 100f;
	private double deltatime;
	private TextureRegion [][] healer,stuner,shielder,traper,poisoner,random;
	int push1 = 0, push2 = 0,push = 0 ;
	Decoration [][] bush = new Decoration[3][100];
	Fruit[][] fruits = new Fruit[7][20];


	float etime;



	@Override
	public void create () {
		/** Generate camera 1 **/
		camera1 = new OrthographicCamera(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
		camera1.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera1.update();
		/** Generate camera 2 **/
		camera2 = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		camera2.position.set(500+Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera2.update();
		/** Load asset **/
		batch = new SpriteBatch();
		imgB = new Texture("map/bg-green.jpg");
		grid1 = new Texture("sprite/Grid01.png");
		grid2 = new Texture("sprite/Grid02.png");
		grid3 = new Texture("sprite/Grid03.png");
		grid4 = new Texture("sprite/Grid04.png");
		grid5 = new Texture("sprite/Grid05.png");
		grid6 = new Texture("sprite/Grid06.png");
		for(int q = 0; q<7;q++){
			int t =q+1;
			fruit[q] = new Texture("fruits/"+t+".png");
		}
		for(int q = 0; q<3;q++){
			int t =q+1;
			deco[q] = new Texture("Deco/b"+t+".png");
		}
		/** Load asset **/
		healer = TextureRegion.split(grid2,127,182);
		shielder = TextureRegion.split(grid1,127,182);
		stuner = TextureRegion.split(grid3,127,182);
		poisoner = TextureRegion.split(grid4,127,182);
		traper = TextureRegion.split(grid5,127,182);
		random = TextureRegion.split(grid6,127,182);
		for (int y = 0;y <100;y++){
			for(int z = 0;z < 3;z++){
				bush[z][y] = new Decoration();
				bush[z][y].setPosDeco((float)(Math.random()*7000+100),(float)(Math.random()*4000+100));
			}
		}
		/*
		for (int y = 0;y <100;y++){
			for(int z = 0;z < 3;z++){
				bush[z][y].setPosDeco((float)(Math.random()*7000+100),(float)(Math.random()*4000+100));
			}
		}*/

		for(int n=0;n<20;n++) {
			for(int m = 0;m<7;m++) {
				fruits[m][n] = new Fruit();
				fruits[m][n].setPosFruit((float)(Math.random()*7000+100),(float)(Math.random()*4000+100));
			}
		}

		/* for(int n=0;n<20;n++) {
			for(int m = 0;m<7;m++) {
				fruits[m][n].setPosFruit((float)(Math.random()*7000+100),(float)(Math.random()*4000+100));
			}
		}*/


		setAnimation(getSprite((Charactor) player1),player1);
		setAnimation(getSprite((Charactor) player2),player2);

		setAnimation(healer,heal);
		setAnimation(shielder,shield);
		setAnimation(stuner,stun);
		setAnimation(traper,trap);
		setAnimation(poisoner,poison);

		player1.setPosX(camera1.position.x);
		player1.setPosY(camera1.position.y);
		player2.setPosX(camera2.position.x);
		player2.setPosY(camera2.position.y);
	}

	@Override
	public void resize(int width, int height){

	}

	public TextureRegion[][] getSprite(Charactor x){
		if(x.getSkill()==1){
			return healer;
		}
		else if(x.getSkill()==2){
			return shielder;
		}
		else if(x.getSkill()==3){
			return poisoner;
		}
		else if(x.getSkill()==4){
			return stuner;
		}
		else if(x.getSkill()==5){
			return traper;
		}
		else if(x.getSkill()==6){
			return random;
		}
		else {
			return null;
		}

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

	public void setCamera(){
		if (Gdx.input.isKeyPressed(Input.Keys.A)&&player1.getPos().x>512&&player1.getPos().x<6761) {
			camera1.position.x -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.D)&&player1.getPos().x>512&&player1.getPos().x<6761) {
			camera1.position.x += camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&player1.getPos().y<4592&&player1.getPos().y>366) {
			camera1.position.y -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.W)&&player1.getPos().y>366&&player1.getPos().y<4592) {
			camera1.position.y += camSpeed * deltatime;
		}
	}

	public void setCamera2(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&player2.getPos().x>512&&player2.getPos().x<6761) {
			camera2.position.x -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&player2.getPos().x>512&&player2.getPos().x<6761) {
			camera2.position.x += camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&player2.getPos().y>366&&player2.getPos().y<4592) {
			camera2.position.y -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.UP)&&player2.getPos().y>366&&player2.getPos().y<4592) {
			camera2.position.y += camSpeed * deltatime;
		}

	}

	public void move(TextureRegion [][] hero,Person person){
		if(Gdx.input.isKeyPressed(Input.Keys.A)&&person.getPos().x>0){
			person.setPosX(person.getPos().x-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)&&person.getPos().x<7087){
			person.setPosX(person.getPos().x+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 2;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.W)&&person.getPos().y>0){
			person.setPosY(person.getPos().y+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&person.getPos().y<4966){
			person.setPosY(person.getPos().y-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.V)){
			if (push == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
		}
		else {
			batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
		}
	}

	public void move2(TextureRegion [][] hero,Person person){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&&person.getPos().x>0){
			person.setPosX(person.getPos().x-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&person.getPos().x<7087){
			person.setPosX(person.getPos().x+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 2;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)&&person.getPos().y>0){
			person.setPosY(person.getPos().y+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&person.getPos().y<4966){
			person.setPosY(person.getPos().y-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)){
			if (push == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
		}
		else {
			batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
		}
	}

	public void checkFruit(){
		for(int m =0;m<7;m++){
			for (int n = 0; n < 20; n++){
				if((camera1.position.x>fruits[m][n].getPosFruit().x-47&&camera1.position.x<fruits[m][n].getPosFruit().x+47)&&(camera1.position.y<fruits[m][n].getPosFruit().x+19&&camera1.position.y>fruits[m][n].getPosFruit().y-19)||(camera2.position.x>fruits[m][n].getPosFruit().x-47&&camera2.position.x<fruits[m][n].getPosFruit().x+47)&&(camera2.position.y<fruits[m][n].getPosFruit().x+19&&camera2.position.y>fruits[m][n].getPosFruit().y-19)){
					fruits[m][n].setPick(true);
				}
			}
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

		for (int y = 0;y <3;y++){
			for(int z = 0;z < 100;z++){
				batch.draw(deco[y],bush[y][z].getPosDeco().x,bush[y][z].getPosDeco().y,131,90);
			}
		}
		for(int m=0;m<7;m++){
			int n = 0;
			checkFruit();
			if (n == 0){
				batch.draw(fruit[m],fruits[m][n].getPosFruit().x,fruits[m][n].getPosFruit().y,70,70);
			}
			else if(fruits[m][n].isPick()){
				fruit[m].dispose();
				n += 1;
				System.out.println(n);
			}
			else {
				batch.draw(fruit[m],fruits[m][n].getPosFruit().x,fruits[m][n].getPosFruit().y,70,70);
			}
		}
		setCamera();
		setCamera2();
		move(getSprite((Charactor) player1),player1);
		move2(getSprite((Charactor) player2),player2);
		System.out.println(player1.getPos().x+" "+player1.getPos().y);
		batch.end();


		/*Right Half*/
		Gdx.gl.glViewport( Gdx.graphics.getWidth()/2,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		camera2.update();
		batch.setProjectionMatrix(camera2.combined);
		batch.begin();
		batch.draw(imgB,0,0);

		for (int y = 0;y <3;y++){
			for(int z = 0;z < 100;z++){
				if(y == 0){
					batch.draw(deco[y],bush[y][z].getPosDeco().x,bush[y][z].getPosDeco().y,131,90);
				}
				else if (y == 1){
					batch.draw(deco[y],bush[y][z].getPosDeco().x,bush[y][z].getPosDeco().y,126,90);
				}
				else {
					batch.draw(deco[y], bush[y][z].getPosDeco().x, bush[y][z].getPosDeco().y, 134, 90);
				}
			}
		}
		if((camera1.position.x>960&&camera1.position.x<1000)&&(camera1.position.y<1040&&camera1.position.y>995)||(camera2.position.x>960&&camera2.position.x<1000)&&(camera2.position.y<1040&&camera2.position.y>995)){
			batch.enableBlending();
		}
		else {
			for(int m =0;m<7;m++){
				for(int n=0;n<20;n++) {
					batch.draw(fruit[m],fruits[m][n].getPosFruit().x,fruits[m][n].getPosFruit().y,70,70);
				}
			}
		}
		setCamera();
		setCamera2();
		move(getSprite((Charactor) player1),player1);
		move2(getSprite((Charactor) player2),player2);
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
	}

	@Override
	public void pause() {
	}
}
