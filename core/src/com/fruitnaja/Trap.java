package com.fruitnaja;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 17/4/2560.
 */
public class Trap {
    private Vector2 posTrap = new Vector2();
    private boolean pick;
    public void setPosTrap(Vector2 posFruit) {
        this.posTrap = posFruit;
    }

    public Vector2 getPosTrap() {
        return posTrap;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public Trap(float x,float y) {
        this.posTrap.add(x, y);
        this.pick = false;
    }

    public Trap() {
        this.pick = false;
        this.posTrap = new Vector2(0,0);
    }

    public void setPosTrap(float x,float y) {
        this.posTrap.x = x;
        this.posTrap.y = y;
    }
}
