package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 12/4/2560.
 */
public class Fruit implements Catchable {
    private String name;
    private int effect;
    private Vector2 posFruit;
    private int idFruit;
    private boolean pick;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEffect() {
        return effect;
    }

    public void setEffect(int effect) {
        this.effect = effect;
    }

    public Vector2 getPosFruit() {
        return posFruit;
    }

    public void setPosFruit(Vector2 posFruit) {
        this.posFruit = posFruit;
    }



    public int getIdFruit() {
        return idFruit;
    }

    public void setIdFruit(int idFruit) {
        this.idFruit = idFruit;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public Fruit(String name, int effect, int idFruit) {
        this.name = name;
        this.effect = effect;
        this.posFruit = new Vector2((float) (Math.random()*7067),(float) (Math.random()*4064));
        this.idFruit = idFruit;
        this.pick = false;
    }

    public Fruit() {
        this.pick = false;
        this.posFruit = new Vector2(0,0);
    }

    public void setPosFruit(float x,float y) {
        this.posFruit.x = x;
        this.posFruit.y = y;
    }

    @Override
    public int catchObj() {
        return 0;
    }
}
