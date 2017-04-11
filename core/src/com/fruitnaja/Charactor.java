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

    public Charactor() {


    }

    public Charactor(String name, int hp, int stamina,  int id, int skill) {
        super(name, hp, stamina, id);
        this.skill = skill;
    }

    public void useSkill(){
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            if (skill == 1&&getHp()<200){
                setHp(getHp()+50);
            }

        }

    }
}
