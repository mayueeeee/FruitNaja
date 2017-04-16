package com.fruitnaja;

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
    private static MediaPlayer mediaPlayer;

    public static void play(){
        int list_no = (int)(Math.random()*2+1);
        System.out.println(list_no);
        play(list_no);
    }

    public static void play(int list){
        String bip = "";
        //To prevent Musicplayer is Null when start program
        try{
            mediaPlayer.stop();
        }
        catch (NullPointerException e){
            System.out.println("No media player!");
        }
        //Select music
        if(list ==1){
            bip = "music/01 Anywhere I Go.mp3";
        }
        else if(list == 2){
            bip = "music/ping fire.mp3";
        }
        Media hit = new Media(new File(bip).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setRate(1.5);
        mediaPlayer.play();
    }

    public static void stop(){
        mediaPlayer.stop();
    }
}
