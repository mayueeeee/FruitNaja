package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by user on 10/4/2560.
 */
public class Charactor extends Person {
    public int skill;
    private long lastHitTimeS;

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Charactor(String name, int hp, int stamina,  int id, int skill) {
        super(hp, stamina);
        this.skill = skill;
    }

    public void useSkill(){
        if(Gdx.input.isKeyPressed(Input.Keys.Q)&&getStamina()>0&& TimeUtils.nanoTime()-lastHitTimeS>1000000000){
            if (skill == 1&&getHp()<200){
                setHp(getHp()+50);
                setStamina(getStamina()-25);
            }
            if (skill == 2){
                setStamina(getStamina()-25);
            }
            if (skill == 3){
                setStamina(getStamina()-25);
            }
            if (skill == 4){
                setStamina(getStamina()-25);
            }
            if (skill == 5){
                setStamina(getStamina()-25);
            }
            lastHitTimeS = TimeUtils.nanoTime();
        }

    }
}
