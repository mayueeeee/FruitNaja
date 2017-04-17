package com.fruitnaja;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by user on 12/4/2560.
 */
public class Weapon {
    private String name;
    private int effect;
    private Vector2 posWeapon;
    private int idWeapon;
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

    public Vector2 getPosWeapon() {
        return posWeapon;
    }

    public void setPosWeapon(Vector2 posWeapon) {
        this.posWeapon = posWeapon;
    }

    public int getIdWeapon() {
        return idWeapon;
    }

    public void setIdWeapon(int idWeapon) {
        this.idWeapon = idWeapon;
    }

    public boolean isPick() {
        return pick;
    }

    public void setPick(boolean pick) {
        this.pick = pick;
    }

    public Weapon(String name, int effect, int idWeapon) {
        this.name = name;
        this.effect = effect;
        this.posWeapon = new Vector2((float)(Math.random()*7067),(float)(Math.random()*4684));
        this.idWeapon = idWeapon;
        this.pick = true;
    }

    public Weapon() {
        
    }

}
