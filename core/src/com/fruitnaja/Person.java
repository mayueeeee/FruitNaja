package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by user on 9/4/2560.
 */
public abstract class Person implements Attackable {
    private String name;
    private int hp;
    private int stamina;
    private Vector2 pos;
    private  boolean live;
    private static int idS = 0;
    private int id;
    ArrayList<Item> checkitem = new ArrayList<Item>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public static int getIdS() {
        return idS;
    }

    public static void setIdS(int idS) {
        Person.idS = idS;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person(String name, int hp, int stamina, int id) {
        this.name = name;
        this.hp = hp;
        this.stamina = stamina;
        this.pos = new Vector2(0,0);
        this.live = true;
        this.id = id;
        this.idS += 1;
    }

    public Person() {

    }

    public void move(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A)){
            pos.x-=100*Gdx.graphics.getDeltaTime();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)||Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            pos.x+=200*Gdx.graphics.getDeltaTime();
        }
        else if ((Gdx.input.isKeyPressed(Input.Keys.W)||Gdx.input.isKeyPressed(Input.Keys.UP))&&pos.y<700){
            pos.y+=200*Gdx.graphics.getDeltaTime();
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)||Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            pos.y-=100*Gdx.graphics.getDeltaTime();
        }
    }

    public void die(){
        if (hp==0){
            System.out.println("You Die");
            this.live = false;
        }
    }

    public void attack(){

    }

    public abstract void useSkill();


}
