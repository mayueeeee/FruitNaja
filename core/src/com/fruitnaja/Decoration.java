package com.fruitnaja;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 16/4/2560.
 */
public class Decoration {
    private Vector2 posDeco;

    public Vector2 getPosDeco() {
        return posDeco;
    }

    public void setPosDeco(Vector2 posDeco) {
        this.posDeco = posDeco;
    }

    public void setPosDeco(float x,float y) {
        this.posDeco.x = x;
        this.posDeco.y = y;
    }

    public Decoration(){
        this.posDeco= new Vector2(0,0);
    }
}
