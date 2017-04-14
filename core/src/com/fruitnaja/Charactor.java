package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by user on 10/4/2560.
 */
public class Charactor extends Person {
    public int skill;

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
        if(Gdx.input.isKeyPressed(Input.Keys.Q)&&getStamina()>0){
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
            if (skill == 6){
                skill = ((int)(Math.random()*5))+1;
            }
        }

    }
}
