package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by user on 10/4/2560.
 */
public class Charactor extends Person {
    public int skill;
    private long lastHitTimeS;
    private boolean skillUse;
    private Vector2 trap;
    private boolean [] use = {false,false};

    public Vector2 getTrap() {
        return trap;
    }

    public void setTrap(Vector2 trap) {
        this.trap = trap;
    }

    public boolean[] getUse() {
        return use;
    }

    public void setUse(boolean use,int num) {
        this.use[num] = use;
    }

    public boolean isSkillUse() {
        return skillUse;
    }

    public void setSkillUse(boolean skillUse) {
        this.skillUse = skillUse;
    }


    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Charactor(int skill) {
        super();
        this.skill = skill;
        this.skillUse = false;

    }

    public  void createTrap(){
        this.trap.x = this.getPos().x;
        this.trap.y = this.getPos().y;
    }

    public void useSkill(){
        if(Gdx.input.isKeyPressed(Input.Keys.Q)&&getStamina()>0&& TimeUtils.nanoTime()-lastHitTimeS>1000000000){
            if (skill == 1&&getHp()<200){
                setHp(getHp()+50);
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 2){
                createTrap();
                use[0] = true;
                System.out.println("Trap");
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 3){
                use[1] = true;
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 4){
                skill = (int)(Math.random()*3+1);
                if (skill == 1&&getHp()<200){
                    setHp(getHp()+50);
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
                else if (skill == 2){
                    createTrap();
                    use[0] = true;
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
                else if (skill == 3){
                    use[1] = true;
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
            }
            lastHitTimeS = TimeUtils.nanoTime();
            System.out.println();
        }

    }

    public void useSkill2(){
        if(Gdx.input.isKeyPressed(Input.Keys.SLASH)&&getStamina()>0&& TimeUtils.nanoTime()-lastHitTimeS>1000000000){
            if (skill == 1&&getHp()<200){
                setHp(getHp()+50);
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 2){
                createTrap();
                use[0] = true;
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 3){
                use[1] = true;
                setStamina(getStamina()-25);
                skillUse = true;
            }
            else if (skill == 4){
                skill = (int)(Math.random()*3+1);
                if (skill == 1&&getHp()<200){
                    setHp(getHp()+50);
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
                else if (skill == 2){
                    createTrap();
                    use[0] = true;
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
                else if (skill == 3){
                    use[1] = true;
                    setStamina(getStamina()-25);
                    skillUse = true;
                }
            }
            lastHitTimeS = TimeUtils.nanoTime();
        }

    }
}
