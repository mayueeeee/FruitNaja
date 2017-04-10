package com.fruitnaja;

import java.util.Vector;

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
        super();

    }

    public Charactor(String name, int hp, int stamina, Vector pos, int id, int skill) {
        super(name, hp, stamina, pos, id);
        this.skill = skill;
    }

    public void useSkill(){
        switch (this.skill){
            case 1:
                System.out.println("Healing");
            case 2:
                System.out.println("Shield");
            case 3:
                System.out.println("Stun");
            case 4:
                System.out.println("Poison");
            case 5:
                System.out.println("Speed");
            case 6:
                System.out.println("Ramdom");
        }
    }
}
