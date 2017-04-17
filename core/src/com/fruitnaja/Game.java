package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;

/**
 * Created by Sarunyu Chankong on 5/4/2560.
 *
 * All about Game class
 * -This class is helper class (All static methods,constants,data fields)
 * -Store require constants
 * -Store player in game
 */

public class Game {
    /** Require constants **/
    public static final int WIDTH=1024,HEIGH=600;
    public static final int DECOR_WIDTH=131,DECOR_HEIGHT=90;
    public static final int FRUIT_WIDTH=70,FRUIT_HEIGHT=70;
    public static final int CHAR_WIDTH=127,CHAR_HEIGHT=182;
    public static final int ST_WIDTH=212,ST_HEIGHT=90;
    /** Objects Store **/
    private static ArrayList<Person> player = new ArrayList();

    /** Helping methods **/
    public static void loadConfigure(){
        Json json = new Json();
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal("config/config.json"));
        System.out.println(json.prettyPrint(list));
    }

    public static Person getPlayer(int index) {
        return player.get(index);
    }

    public static int addPlayer(Person person){
        player.add(person);
        int index = player.size();
        return index;
    }

    public static String getPlayer(String skill){

       return skill;
    }

    public static void clearStorePlayer(){
        player.clear();
    }

    public static int transfromSkill(String skill){
        System.out.println("IP :"+skill);
        if(skill.equals("skill_heal")){
            return 1;
        }
        else if(skill.equals("skill_trap")){
            return 5;
        }
        else if(skill.equals("skill_shield")){
            return 2;
        }
        else if(skill.equals("skill_stun")){
            return 4;
        }
        else if(skill.equals("skill_random")){
            return 6;
        }
        else if(skill.equals("skill_poison")){
            return 3;
        }
        return 0;
    }


    public static void saveHighScore(Person x){

    }
    public static void loadHighScore(){

    }


}
