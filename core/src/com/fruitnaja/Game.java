package com.fruitnaja;

import com.badlogic.gdx.Gdx;
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

    public void clearStorePlayer(){
        player.clear();
    }
}
