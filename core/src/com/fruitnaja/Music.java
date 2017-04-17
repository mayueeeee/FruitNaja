package com.fruitnaja;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Created by Sarunyu Chankong on 5/4/2560.
 *
 * All about Game class
 * -This class is helper class (All static methods,constants,data fields)
 *
 */

public class Music {

    //public static Sound punch = Gdx.audio.newSound(Gdx.files.internal("sound/PUNCH.wav"));
    //public static Sound yeeha = Gdx.audio.newSound(Gdx.files.internal("sound/yee.wav"));
    //public static Sound tada = Gdx.audio.newSound(Gdx.files.internal("sound/tada.wav"));
    //public static com.badlogic.gdx.audio.Music edm = Gdx.audio.newMusic(Gdx.files.internal("music/edm.mp3"));

    private static MediaPlayer mediaPlayer;

    public static void play(){
        int list_no = (int)(Math.random()*2+1);
        System.out.println(list_no);
        play(list_no);
        //edm.play();
    }

    public static void play(int list){
        String bip = "";
        //To prevent Musicplayer is Null when start program
        try{
            //mediaPlayer.stop();
        }
        catch (NullPointerException e){
            System.out.println("No media player!");
        }
        //Select music
        if(list ==1){
            bip = "music/edm.mp3";
        }
        else if(list == 2){
            bip = "music/Windows Error Remix.mp3";
        }
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setRate(1.095);
        mediaPlayer.play();
    }

    public static void stop(){
        mediaPlayer.stop();
    }
}
