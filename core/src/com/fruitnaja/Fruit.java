package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 12/4/2560.
 */
public class Fruit implements Catchable {
    private int style;

    private Vector2 posFruit = new Vector2();
    private boolean pick;

    public int getStyle() {
        return style;
    }

    public Vector2 getPosFruit() {
        return posFruit;
    }

    public void setPosFruit(Vector2 posFruit) {
        this.posFruit = posFruit;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public Fruit(float x,float y, int style) {
        this.posFruit.add(x, y);
        this.style = style;
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
