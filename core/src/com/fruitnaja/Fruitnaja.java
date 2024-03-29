package com.fruitnaja;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;


public class Fruitnaja extends ApplicationAdapter implements ApplicationListener {
	public  static SpriteBatch batch;
	private Texture img;
	private Texture imgB;
	private Texture grid1,grid2,grid3,grid4,grid5,grid6,stUnUseSkillPlayer1,stUnUseSkillPlayer2,stUseSkillPlayer1,stUseSkillPlayer2,stReUnUseSkillPlayer1,stReUnUseSkillPlayer2,stReUseSkillPlayer1,stReUseSkillPlayer2;
	private Texture [] fruit = new Texture[7];
    private Texture [] fruitP = new Texture[7];
    private Texture [] weapon = new Texture[2];
	private Texture [] deco = new Texture[3];
    private Texture trap ;
    private Texture win,lose;
	private long lastHitTime,lastUseSkilTime,lastUseSkilTime2,lastHit1,lastHit2 ;
	private int x = 0,y = 0;
	private Person heal = new Charactor(1);
	private Person poison = new Charactor(5);
	private boolean cam2Skill3 =false;
	private boolean decreseHP = false;
	private Vector2 poisonFruit =new Vector2();
    private BitmapFont font;

	Person player1 = Game.getPlayer(0);
	Person player2 = Game.getPlayer(1);
	public static Camera camera1;
	public static Camera camera2;
	private float camSpeed = 100f;
	private double deltatime;
	private TextureRegion [][] healer,stuner,shielder,traper,poisoner,random,stUnPlayer1,stUnPlayer2,stPlayer1,stPlayer2,reUnPlayer1,reUnPlayer2,rePlayer1,rePlayer2;
	int push1 = 0, push2 = 0,push = 0 ;
	Decoration [][] bush = new Decoration[3][100];
	Fruit[][] fruits = new Fruit[7][20];
    ArrayList<Fruit> poisonFruitStore = new ArrayList<Fruit>();
    ArrayList<Trap> trapStore = new ArrayList<Trap>();

	/** Rectangles for check collision **/
	Rectangle [][] bush_rect = new Rectangle[3][100];
	Rectangle [][] fruit_rect = new Rectangle[7][20];
	Rectangle [] char_rect = new Rectangle[2];
    Rectangle [] char_body_rect = new Rectangle[2];
    ArrayList<Rectangle> poisonFruitStore_rect = new ArrayList<Rectangle>();
    ArrayList<Rectangle> trapStore_rect = new ArrayList<Rectangle>();
    Collision player1_collision,player2_collision;

	Charactor playerf1 = (Charactor) player1;
	Charactor playerf2 = (Charactor) player2;


	int [] roll = {4,4};
	int [] colum = {4,4};
    int skill3;
    int numTrap = 0;
	float etime;

	public String changSkill(int skill){
		switch (skill){
			case 1:return "Shield";
			case 5:return "Trap";
			case 3:return "Poison";
			case 6:return "Random";
		}
		return "";
	}

	@Override
	public void create () {
		//font = new BitmapFont(Gdx.files.internal("font/x.fnt"),Gdx.files.internal("font/x.png"), false);
		font = new BitmapFont();
		/** Generate camera 1 **/
		camera1 = new OrthographicCamera(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight());
		camera1.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera1.update();
		/** Generate camera 2 **/
		camera2 = new OrthographicCamera(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
		camera2.position.set(500+Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight(),0);
		camera2.update();
		/** Load asset **/
		String playerS1 = changSkill(playerf1.getSkill());
		String playerS2 = changSkill(playerf2.getSkill());
		System.out.println(playerf1.getSkill());
		batch = new SpriteBatch();
		imgB = new Texture("map/bg-green.jpg");
		grid1 = new Texture("sprite/Grid01.png");
		grid2 = new Texture("sprite/Grid02.png");
		grid3 = new Texture("sprite/Grid03.png");
		grid4 = new Texture("sprite/Grid04.png");
		grid5 = new Texture("sprite/Grid05.png");
		grid6 = new Texture("sprite/Grid06.png");
        trap = new Texture("skill/trap.png");
        win = new Texture("win.jpg");
        lose = new Texture("lose.jpg");
		weapon[0] = new Texture("weapon/hamm.png");
        weapon[1] = new Texture("weapon/sword.png");
		stUnUseSkillPlayer1 =new Texture("ST allChar/"+playerS1+"1.png");
		stUnUseSkillPlayer2 =new Texture("ST allChar/"+playerS2+"1.png");
		stUseSkillPlayer1 =new Texture("ST allChar/"+playerS1+"2.png");
		stUseSkillPlayer2 =new Texture("ST allChar/"+playerS2+"2.png");
		stReUnUseSkillPlayer1 =new Texture("reverse/r"+playerS1+"1.png");
		stReUnUseSkillPlayer2 =new Texture("reverse/r"+playerS2+"1.png");
		stReUseSkillPlayer1 =new Texture("reverse/r"+playerS1+"2.png");
		stReUseSkillPlayer2 =new Texture("reverse/r"+playerS2+"2.png");
		for(int q = 0; q<7;q++){
			int t =q+1;
			fruit[q] = new Texture("fruits/"+t+".png");
			fruitP[q] = new Texture("poison/"+t+".png");
		}
		for(int q = 0; q<3;q++){
			int t =q+1;
			deco[q] = new Texture("Deco/b"+t+".png");
		}
		/** Load asset **/
		healer = TextureRegion.split(grid2,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		shielder = TextureRegion.split(grid1,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		stuner = TextureRegion.split(grid3,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		poisoner = TextureRegion.split(grid4,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		traper = TextureRegion.split(grid5,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		random = TextureRegion.split(grid6,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		stUnPlayer1 = TextureRegion.split(stUnUseSkillPlayer1,Game.ST_WIDTH,Game.ST_HEIGHT);
		stUnPlayer2 = TextureRegion.split(stUnUseSkillPlayer2,Game.ST_WIDTH,Game.ST_HEIGHT);
		stPlayer1 = TextureRegion.split(stUseSkillPlayer1,Game.ST_WIDTH,Game.ST_HEIGHT);
		stPlayer2 = TextureRegion.split(stUseSkillPlayer2,Game.ST_WIDTH,Game.ST_HEIGHT);
		reUnPlayer1 = TextureRegion.split(stReUnUseSkillPlayer1,Game.ST_WIDTH,Game.ST_HEIGHT);
		reUnPlayer2 = TextureRegion.split(stReUnUseSkillPlayer2,Game.ST_WIDTH,Game.ST_HEIGHT);
		rePlayer1 = TextureRegion.split(stReUseSkillPlayer1,Game.ST_WIDTH,Game.ST_HEIGHT);
		rePlayer2 = TextureRegion.split(stReUseSkillPlayer2,Game.ST_WIDTH,Game.ST_HEIGHT);

		/** Load asset **/
        /** Generate Decorations **/
		for (int y = 0;y <100;y++){
			for(int z = 0;z < 3;z++){
				float rand_x = (float)(Math.random()*7000+100);
				float rand_y = (float)(Math.random()*4000+100);
				bush_rect[z][y] = new Rectangle(rand_x,rand_y,Game.DECOR_WIDTH,Game.DECOR_HEIGHT);
				bush[z][y] = new Decoration();
				bush[z][y].setPosDeco(rand_x,rand_y);
			}
		}

		/** Generate Fruits **/
		for(int n=0;n<20;n++) {
			for(int m = 0;m<7;m++) {
				float rand_x = (float)(Math.random()*7000+100);
				float rand_y = (float)(Math.random()*4000+100);
				fruit_rect[m][n] = new Rectangle(rand_x,rand_y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
				if(Collision.isCollision(bush_rect,fruit_rect[m][n])){
                    rand_x = (float)(Math.random()*7000+100);
                    rand_y = (float)(Math.random()*4000+100);
                    fruit_rect[m][n] = new Rectangle(rand_x,rand_y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
                }
				fruits[m][n] = new Fruit();
				fruits[m][n].setPosFruit(rand_x,rand_y);
			}
		}

		setAnimation(getSprite((Charactor) player1),player1);
		setAnimation(getSprite((Charactor) player2),player2);
		setAnimation(poisoner,poison);

		player1.setPosX(camera1.position.x);
		player1.setPosY(camera1.position.y);
		char_rect[0] = new Rectangle(camera1.position.x,camera1.position.y,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		char_body_rect[0] = new Rectangle(camera1.position.x,camera1.position.y,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
		player2.setPosX(camera2.position.x);
		player2.setPosY(camera2.position.y);
        char_rect[1] = new Rectangle(camera2.position.x,camera2.position.y,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
        char_body_rect[1] = new Rectangle(camera2.position.x,camera2.position.y,Game.CHAR_WIDTH,Game.CHAR_HEIGHT);
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
		person.animationAttackLeft = new Animation(4f,person.animationframeAttackLeft);

		i=3;
		for (int z = 0; z<10 ;z++) {
			person.animationframeAttackRight[z] = ani[i][3];
		}
		person.animationAttackRight = new Animation(4f,person.animationframeAttackRight);
	}

	public void setCamera(){
		if (Gdx.input.isKeyPressed(Input.Keys.A)&&player1.getPos().x>512&&player1.getPos().x<6761&&!player1_collision.left) {
			camera1.position.x -= camSpeed * deltatime;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)&&player1.getPos().x>512&&player1.getPos().x<6761&&!player1_collision.right) {
			camera1.position.x += camSpeed * deltatime;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&player1.getPos().y<4592&&player1.getPos().y>366&&!player1_collision.buttom) {
			camera1.position.y -= camSpeed * deltatime;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.W)&&player1.getPos().y>366&&player1.getPos().y<4592&&!player1_collision.top) {
			camera1.position.y += camSpeed * deltatime;
		}
	}

	public void setCamera2(){
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&player2.getPos().x>512&&player2.getPos().x<6761&&!player2_collision.left) {
			camera2.position.x -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&player2.getPos().x>512&&player2.getPos().x<6761&&!player2_collision.right) {
			camera2.position.x += camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&player2.getPos().y>366&&player2.getPos().y<4592&&!player2_collision.buttom) {
			camera2.position.y -= camSpeed * deltatime;
		}

		else if (Gdx.input.isKeyPressed(Input.Keys.UP)&&player2.getPos().y>366&&player2.getPos().y<4592&&!player2_collision.top) {
			camera2.position.y += camSpeed * deltatime;
		}

	}

	public void move(TextureRegion [][] hero,Person person,boolean hit){
        if (person.getPos().y<=0){
            person.setPosY(1);
        }
        if (person.getPos().y>=4770){
            person.setPosY(4769);
        }
        if (person.getPos().x<=183){
            person.setPosX(184);
        }
        if (person.getPos().x>=6964){
            person.setPosX(6963);
        }
		if(Gdx.input.isKeyPressed(Input.Keys.A)&&person.getPos().x>183&&person.getPos().x<6964&&!player1_collision.left){
			person.setPosX(person.getPos().x-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.D)&&person.getPos().x>183&&person.getPos().x<6964&&!player1_collision.right){
			person.setPosX(person.getPos().x+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 2;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.W)&&person.getPos().y>0&&person.getPos().y<4770&&!player1_collision.top){
			person.setPosY(person.getPos().y+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.S)&&person.getPos().y>0&&person.getPos().y<4770&&!player1_collision.buttom){
			person.setPosY(person.getPos().y-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.V)&& TimeUtils.nanoTime()-lastHit2>1000000000){
			if (push == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
            if (hit){
                playerf2.setDecreseHP (true);
				Gdx.audio.newSound(Gdx.files.internal("sound/PUNCH.wav")).play();
            }
            lastHit2 = TimeUtils.nanoTime();
		}
		else {
			batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
		}
		char_rect[0] = new Rectangle(person.getPos().x+45,person.getPos().y,48,32);
		char_body_rect[0] = new Rectangle(person.getPos().x+15,person.getPos().y,106,75);
	}

	public void move2(TextureRegion [][] hero,Person person,boolean hit){
		if (person.getPos().y<=0){
			person.setPosY(1);
		}
		if (person.getPos().y>=4770){
			person.setPosY(4769);
		}
		if (person.getPos().x<=183){
			person.setPosX(184);
		}
		if (person.getPos().x>=6964){
			person.setPosX(6963);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)&&person.getPos().x>183&&person.getPos().x<6964&&!player2_collision.left){
			person.setPosX(person.getPos().x-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationA.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 1;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&&person.getPos().x>183&&person.getPos().x<6964&&!player2_collision.right){
			person.setPosX(person.getPos().x+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationD.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			push = 2;
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.UP)&&person.getPos().y>0&&person.getPos().y<4770&&!player2_collision.top){
			person.setPosY(person.getPos().y+=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationW.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)&&person.getPos().y>0&&person.getPos().y<4770&&!player2_collision.buttom){
			person.setPosY(person.getPos().y-=100*Gdx.graphics.getDeltaTime());
			batch.draw((TextureRegion) person.animationS.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.ENTER)&&TimeUtils.nanoTime()-lastHit1>1000000000){
			if (push == 1){
				batch.draw((TextureRegion) person.animationAttackLeft.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
			else {
				batch.draw((TextureRegion) person.animationAttackRight.getKeyFrame(etime,true),person.getPos().x,person.getPos().y);
			}
            if (hit){
                playerf1.setDecreseHP (true);
				Gdx.audio.newSound(Gdx.files.internal("sound/PUNCH.wav")).play();
            }
            lastHit1 = TimeUtils.nanoTime();
		}
		else {
			batch.draw(hero[0][0], person.getPos().x,person.getPos().y );
		}
        char_rect[1] = new Rectangle(person.getPos().x+45,person.getPos().y,48,32);
        char_body_rect[1] = new Rectangle(person.getPos().x+15,person.getPos().y,106,75);
		//char_rect[1].toString();
	}

	public void checkSkill(Charactor charactor){
	    if ((charactor.getSkill() == 5||charactor.getSkill() == 6) && charactor.getUse()[0]){
            trapStore_rect.add(new Rectangle(charactor.getPos().x, charactor.getPos().y,100,100));
            trapStore.add(new Trap(charactor.getPos().x, charactor.getPos().y));
            for (Trap x:trapStore) {
                if(!x.isPick()) {
                    batch.draw(trap, x.getPosTrap().x, x.getPosTrap().y, 100, 100);
                }
            }
            charactor.setUse(false,0);
        }
        else if ((charactor.getSkill() == 3||charactor.getSkill() == 6) && charactor.getUse()[1]){
	        poisonFruit.add(charactor.getPos().x,charactor.getPos().y);
	        poisonFruitStore_rect.add(new Rectangle(charactor.getPos().x, charactor.getPos().y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT));
            skill3 = (int)(Math.random()*6);
			poisonFruitStore.add(new Fruit(charactor.getPos().x, charactor.getPos().y,skill3));
			//batch.draw(fruitP[skill3],poisonFruit.x, poisonFruit.y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
			for (Fruit x:poisonFruitStore) {
			    if(!x.isPick()) {
                    batch.draw(fruitP[x.getStyle()], x.getPosFruit().x, x.getPosFruit().y, Game.FRUIT_WIDTH, Game.FRUIT_HEIGHT);
                }
			}
			charactor.setUse(false,1);
            cam2Skill3 = true;
        }
    }

    public void printPoison(){
	    if (cam2Skill3){
	        //batch.draw(fruit[skill3],poisonFruit.x,poisonFruit.y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
			for (Fruit x:poisonFruitStore) {
				batch.draw(fruitP[x.getStyle()],x.getPosFruit().x, x.getPosFruit().y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
			}
        }
    }

    public void printTrap(){
        for (Trap x:trapStore) {
            batch.draw(trap, x.getPosTrap().x, x.getPosTrap().y, 100, 100);
        }
    }

	public void checkHp(TextureRegion [][] hero,TextureRegion [][] heroUse,Charactor charactor,Camera camera,int index ){
		if (!charactor.isSkillUse()){
            if (charactor.isIncreseStamina()&&charactor.getStamina()<100){
                roll[index]+=1;
                batch.draw(hero[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
                charactor.setIncreseStamina(false);
            }
            else {
                batch.draw(hero[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
            }
		}
		else if (TimeUtils.nanoTime()-lastUseSkilTime>1000000000){
			if(roll[index] == 0){
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+-310,camera.position.y-350);
				lastUseSkilTime = TimeUtils.nanoTime();
			}
			else {
				roll[index] -=1;
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
				charactor.setSkillUse(false);
				lastUseSkilTime = TimeUtils.nanoTime();
			}
		}
		if (!charactor.isDecreseHP()){
		    if (charactor.isIncreseHP()&&charactor.getHp()<=200){
		        colum[index]+=1;
                batch.draw(hero[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
                charactor.setIncreseHP(false);
			}
            else {
                batch.draw(hero[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
            }
         }
		else if (TimeUtils.nanoTime()-lastUseSkilTime>1000000000){
			if(colum[index] == 0){
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+-310,camera.position.y-350);
				lastUseSkilTime = TimeUtils.nanoTime();
			}
			else if (charactor.isDecrese2HP()&& charactor.isDecreseHP()){
				colum[index] -=2;
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
				charactor.setDecrese2HP(false);
				charactor.setDecreseHP(false);
				lastUseSkilTime = TimeUtils.nanoTime();
				charactor.setHp(player1.getHp()-100);
			}
			else {
				colum[index] -=1;
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x-310,camera.position.y-350);
				charactor.setDecreseHP(false);
				lastUseSkilTime = TimeUtils.nanoTime();
                charactor.setHp(player1.getHp()-50);
			}
    	}
        font.draw(batch, Integer.toString(charactor.getScore()), camera.position.x-190,camera.position.y-335);
//
	}

	public void checkHpRe(TextureRegion [][] hero,TextureRegion [][] heroUse,Charactor charactor,Camera camera,int index){
		if (!charactor.isSkillUse()){
            if (charactor.isIncreseStamina()&&charactor.getStamina()<100){
                roll[index]+=1;
                batch.draw(hero[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
                charactor.setIncreseStamina(false);
            }
            else {
                batch.draw(hero[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
            }
		}
		else if (TimeUtils.nanoTime()-lastUseSkilTime>1000000000 ){
			if(roll[index] == 0){
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
				lastUseSkilTime = TimeUtils.nanoTime();
			}
			else {
				roll[index] -=1;
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
				charactor.setSkillUse(false);
				lastUseSkilTime = TimeUtils.nanoTime();
			}
		}
		if (!charactor.isDecreseHP()){
            if (charactor.isIncreseHP()&& charactor.getHp()<=200){
                colum[index]+=1;
                batch.draw(hero[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
                charactor.setIncreseHP(false);
             }
            else {
                batch.draw(hero[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
             }
        }
		else if (TimeUtils.nanoTime()-lastUseSkilTime>1000000000){
            if(colum[index] == 0){
                batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
                lastUseSkilTime = TimeUtils.nanoTime();
            }
            else if (charactor.isDecrese2HP()&&charactor.isDecreseHP()){
				colum[index] -=2;
				batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
				charactor.setDecreseHP(false);
				charactor.setDecrese2HP(false);
				lastUseSkilTime = TimeUtils.nanoTime();
				charactor.setHp(player2.getHp()-100);
			}
        else {
            colum[index] -=1;
            batch.draw(heroUse[colum[index]][roll[index]],camera.position.x+100,camera.position.y+265);
            charactor.setDecreseHP(false);
            lastUseSkilTime = TimeUtils.nanoTime();
            charactor.setHp(player2.getHp()-50);
            }
        }
		font.draw(batch, Integer.toString(charactor.getScore()), camera.position.x+260,camera.position.y+280);
	}

	public void checkscore(int [] chec, Person player, Charactor playerf){
		switch (chec[0]){
			case 0 : player.setScore(player.getScore()+10); break;
			case 1 : player.setScore(player.getScore()+20); break;
			case 2 : player.setHp(player.getHp()+50); playerf.setIncreseHP(true); break;
			case 3 : player.setStamina(player.getStamina()+25);playerf.setIncreseStamina(true); break;
			case 4 : player.setScore(player.getScore()+30); break;
			case 5 : player.setScore(player.getScore()+40); break;
			case 6 : player.setScore(player.getScore()+50); break;
		}
	}

	@Override
	public void render () {
	    player1_collision = Collision.isCollision(char_rect[0],bush_rect);
        player2_collision = Collision.isCollision(char_rect[1],bush_rect);
        Collision.isCollision(char_body_rect);
        int [] check1 = Collision.checkCollision(char_body_rect[0],fruit_rect);
        int [] check2 = Collision.checkCollision(char_body_rect[1],fruit_rect);
        int checkPoisonFruit1 = Collision.checkCollision(char_body_rect[0],poisonFruitStore_rect);
        int checkPoisonFruit2 = Collision.checkCollision(char_body_rect[1],poisonFruitStore_rect);
        int checkTrap1 = Collision.checkCollision(char_body_rect[0],trapStore_rect);
        int checkTrap2 = Collision.checkCollision(char_body_rect[1],trapStore_rect);
		etime += Gdx.graphics.getDeltaTime();
		deltatime = Gdx.graphics.getDeltaTime();
		heal.die();
		if(heal.isLive()==false){
			System.exit(0);
		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		/*Left Half*/
		Gdx.gl.glViewport( 0,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		camera1.update();
		batch.setProjectionMatrix(camera1.combined);
		batch.begin();
		batch.draw(imgB,0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.G)&&TimeUtils.nanoTime()-lastHitTime>1000000000){

            decreseHP = true;
            lastHitTime = TimeUtils.nanoTime();
        }
		for (int y = 0;y <3;y++){
			for(int z = 0;z < 100;z++){
				batch.draw(deco[y],bush[y][z].getPosDeco().x,bush[y][z].getPosDeco().y,Game.DECOR_WIDTH,Game.DECOR_HEIGHT);
				//System.out.println("BUSH: "+bush[y][z].getPosDeco().x+" , "+bush[y][z].getPosDeco().y);
			}
		}
		if(check1[0]!=-1){
			fruits[check1[0]][check1[1]].setPick(true);
			Gdx.audio.newSound(Gdx.files.internal("sound/yee.wav")).play();
		}

        checkscore(check1,player1,playerf1);
        //System.out.println("Score1: "+player1.getScore());
		//checkscore(check1,player1,playerf1);
		for(int m =0;m<7;m++){
			for(int n=0;n<20;n++) {
				if (!fruits[m][n].isPick()){
					batch.draw(fruit[m],fruits[m][n].getPosFruit().x,fruits[m][n].getPosFruit().y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
				}
			}
		}
		setCamera();
		setCamera2();
		if (playerf1.getSkill()!=3 && checkPoisonFruit1 != -1){
            try{
                poisonFruitStore.remove(checkPoisonFruit1);
				playerf1.setDecreseHP(true);
            }catch (Exception e){

            }
        }
        if (playerf1.getSkill() == 3||playerf1.getSkill() == 6){
			printPoison();
		}
        if ((playerf1.getSkill()!=5&&playerf1.getSkill() != 6)&&checkTrap1 != -1){
            try{
                trapStore.remove(checkTrap1);
                playerf1.setDecrese2HP(true);
				playerf1.setDecreseHP(true);
            }catch (Exception e){

            }
        }
        if(playerf1.getSkill() == 5||playerf1.getSkill() == 6) {
			printTrap();
		}
		move(getSprite((Charactor) player1),player1,Collision.isCollision(char_body_rect));
		move2(getSprite((Charactor) player2),player2,Collision.isCollision(char_body_rect));
        //System.out.println(check1[0]);
        player1.useSkill();
        checkSkill(playerf1);
        printPoison();
		checkHp(stUnPlayer1,stPlayer1,playerf1,camera1,0);
		checkHpRe(stUnPlayer2,stPlayer2,playerf2,camera1,1);
        if(player1.getHp() <= 0 && player2.getHp() > 0){
		    batch.draw(lose,camera1.position.x-324,camera1.position.y-360,Gdx.graphics.getWidth()/2+50,Gdx.graphics.getHeight()+50);
		    //Music.tada.play();
        }
        else if (player2.getHp() <= 0 && player1.getHp() > 0) {
            batch.draw(win,camera1.position.x-324,camera1.position.y-360,Gdx.graphics.getWidth()/2+50,Gdx.graphics.getHeight()+50);
        }
        batch.end();


		/*Right Half*/
		Gdx.gl.glViewport( Gdx.graphics.getWidth()/2,0,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight() );
		camera2.update();
		batch.setProjectionMatrix(camera2.combined);
		batch.begin();

		batch.draw(imgB,0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.L)&&TimeUtils.nanoTime()-lastHitTime>1000000000){
            player2.setHp(player2.getHp()-50);
            decreseHP = true;
            lastHitTime = TimeUtils.nanoTime();
        }
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
		if(check2[0]!= -1){
			fruits[check2[0]][check2[1]].setPick(true);
			Gdx.audio.newSound(Gdx.files.internal("sound/yee.wav")).play();
		}
        checkscore(check2,player2,playerf2);
        //System.out.println("Score2: "+player2.getScore());
		for(int m =0;m<7;m++){
			for(int n=0;n<20;n++) {
				if (!fruits[m][n].isPick()){
					batch.draw(fruit[m],fruits[m][n].getPosFruit().x,fruits[m][n].getPosFruit().y,Game.FRUIT_WIDTH,Game.FRUIT_HEIGHT);
				}
			}
		}
		setCamera();
		setCamera2();
		if ((playerf2.getSkill()!=3||playerf2.getSkill() != 6)&& checkPoisonFruit2 != -1){
			try{
				poisonFruitStore.remove(checkPoisonFruit2);
				playerf2.setDecreseHP(true);
			}catch (Exception e){

			}
		}
		if (playerf2.getSkill() == 3||playerf2.getSkill() == 6){
			printPoison();
		}
		if ((playerf2.getSkill()!=5||playerf2.getSkill() != 6)&&checkTrap2 != -1){
			try{
				trapStore.remove(checkTrap2);
				playerf2.setDecrese2HP(true);
				playerf2.setDecreseHP(true);
			}catch (Exception e){

			}
		}
		if (playerf2.getSkill()==5||playerf2.getSkill() == 6){
			printTrap();
		}
		move(getSprite((Charactor) player1),player1,Collision.isCollision(char_body_rect));
		move2(getSprite((Charactor) player2),player2,Collision.isCollision(char_body_rect));
		player2.useSkill2();
		checkSkill(playerf2);

		checkHp(stUnPlayer2,stPlayer2,playerf2,camera2,1);
		checkHpRe(stUnPlayer1,stPlayer1,playerf1,camera2,0);
		if(player1.getHp() <= 0 && player2.getHp() > 0){
			batch.draw(win,camera2.position.x-324,camera2.position.y-360,Gdx.graphics.getWidth()/2+50,Gdx.graphics.getHeight()+50 );
			//Music.tada.play();
		}
		else if (player2.getHp() <= 0 && player1.getHp() > 0) {
			batch.draw(lose,camera2.position.x-324,camera2.position.y-360,Gdx.graphics.getWidth()/2+50,Gdx.graphics.getHeight()+50);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

	@Override
	public void pause() {
	}
}
