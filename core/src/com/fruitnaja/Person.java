package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

/**
 * Created by user on 9/4/2560.
 */
public abstract class Person implements Attackable {
    private int hp;
    private int stamina;
    private Vector2 pos;
    private  boolean live;
    private long lastHitTimeS;

    TextureRegion[] animationframeS = new TextureRegion[2];
    Animation animationS;
    TextureRegion[] animationframeW = new TextureRegion[2];
    Animation animationW;
    TextureRegion[] animationframeA = new TextureRegion[2];
    Animation animationA;
    TextureRegion[] animationframeD = new TextureRegion[2];
    Animation animationD;
    TextureRegion[] animationframeAttackLeft = new TextureRegion[10];
    Animation animationAttackLeft;
    TextureRegion[] animationframeAttackRight = new TextureRegion[10];
    Animation animationAttackRight;

    ArrayList<Fruit> checkFruit = new ArrayList<Fruit>();
    ArrayList<Weapon> checkWeapon = new ArrayList<Weapon>();

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public boolean isLive() {
        return live;
    }

    public Person() {
        this.hp = 200;
        this.stamina = 100;
        this.pos = new Vector2(512/2,300/2);
        this.live = true;
    }

    public void move(){
        if((Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A))&&pos.x>0){
            pos.x-=100*Gdx.graphics.getDeltaTime();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            pos.x+=100*Gdx.graphics.getDeltaTime();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP))){
            pos.y+=100*Gdx.graphics.getDeltaTime();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN))&&pos.y>0){
            pos.y-=100*Gdx.graphics.getDeltaTime();
        }
    }

    public void die(){
        if (hp==0){
            System.out.println("You Die");
            this.live = false;
        }
    }

    public boolean attack(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)&& TimeUtils.nanoTime()-lastHitTimeS>1000000000){
            lastHitTimeS = TimeUtils.nanoTime();
            return true;
        }
        else {
            return false;
        }
    }

    public abstract void useSkill();


}
