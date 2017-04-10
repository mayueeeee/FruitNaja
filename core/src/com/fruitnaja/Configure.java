package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

/**
 * Created by nutno on 5/4/2560.
 */
public class Configure {
    protected static int screen_width,screen_height;
    public static final int WIDTH=1024,HEIGH=600;

    public static void loadConfigure(){
        Json json = new Json();
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal("config/config.json"));
        System.out.println(json.prettyPrint(list));
    }
}
