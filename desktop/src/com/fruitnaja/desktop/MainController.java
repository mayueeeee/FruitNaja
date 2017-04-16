package com.fruitnaja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fruitnaja.Charactor;
import com.fruitnaja.Fruitnaja;
import com.fruitnaja.Game;
import com.fruitnaja.Music;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;


/**
 * Created by nutno on 5/4/2560.
 */
public class MainController{

    @FXML
    private Button backBtn;
    @FXML
    private ImageView btn_start,btn_back,btn_highscore,btn_play,btn_start_game;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    //Object in Charactor select
    @FXML
    private ImageView player1_preview,player2_preview;
    @FXML
    private Text player1_release,player2_release;
    @FXML
    private ImageView skill_heal,skill_poison,skill_shield,skill_stun,skill_trap,skill_random;
    private static String[] select_skill = new String[2];


    /* Methods for called by FXML */
    @FXML
    //All pages -- Back button
    private void backToMain(MouseEvent actionEvent) throws IOException {
        stage=(Stage) btn_back.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/main.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Back btn press");
    }

    public void backToMain() throws IOException{
        stage=(Stage) btn_start_game.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/main.fxml"));
        stage.getScene().setRoot(root);
    }
//    private void showHowto(MouseEvent actionEvent) throws IOException {
//        stage=(Stage) btn_back.getScene().getWindow();
//        root = FXMLLoader.load(new URL("file:layouts/howtoplay.fxml"));
//        stage.getScene().setRoot(root);
//        System.out.println("Back btn press");
//    }


    //Select skill page -- Start Button
    public void runGDX(MouseEvent mouseEvent) throws IOException {
        //System.out.println("Play btn press");
        createPlayer();
        backToMain();

        //System.out.println("-----gdx-----");
        //debugSelect();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        config.width = 1280;
        config.height = 720;
        new LwjglApplication(new Fruitnaja(), config);
    }

    //Main page -- High Score Button
    public void showHighScore(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_highscore.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/highscore.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Highscore btn press");
    }

    //Main page -- Exit Button
    public void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //Main page -- Option Button
    public void showOption(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_highscore.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/option.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Option btn press");
    }

    //Main page -- Play Button
    public void showSkill(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_play.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/character-select.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Skill btn press");
    }

    //Music page -- Normal Button
    public void playNormal(MouseEvent mouseEvent) {
        Music.play(2);
    }

    //Music page -- EDM Button
    public void playEDM(MouseEvent mouseEvent) {
        Music.play(1);
    }

    //Music page -- OFF Button
    public void stopMusic(MouseEvent mouseEvent) {
        Music.stop();
    }

    /* Methods for select skills page */

//    public String randomSkill(){
//        int skill = (int)(Math.random()*5+1);
//        switch (skill){
//            case 1: return "skill_heal";
//            case 2: return "skill_shield";
//            case 3: return "skill_poison";
//            case 4: return "skill_stun";
//            case 5: return "skill_trap";
//            default: return "ERROR!";
//        }
//    }
//
//    public void showSelectSkill() throws IOException{
//        String random;
//        for (int i = 1; i <= 2; i++) {
//            random = randomSkill();
//            if (i==2){
//                while (select_skill[0].equals(random)){
//                    random = randomSkill();
//                }
//            }
//            select_skill[i-1] = random;
//            showSelectSkill(i,select_skill[i-1]);
//        }
//        disableSkill();
//    }

    public void disableSkill(){
        disableSkill(skill_heal);
        disableSkill(skill_poison);
        disableSkill(skill_random);
        disableSkill(skill_shield);
        disableSkill(skill_stun);
        disableSkill(skill_trap);
    }

    public void disableSkill(Node node){
        //Generated grayscale effect
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        grayscale.setBrightness(-0.2);
        //Apply effect to Node
        node.setDisable(true);
        node.setEffect(grayscale);
        //System.out.println("Node: "+node.getId());
    }

    public void showSelectSkill(int player,String skill) throws IOException{
        skill = skill.substring(6);
        Image file = new Image(new URL("file:skill/"+skill+"/default.png").toString());
        if (player==1){
            player1_release.setVisible(true);
            player1_preview.setImage(file);
        }
        else if (player==2){
            player2_release.setVisible(true);
            player2_preview.setImage(file);
        }
    }

    public void enableSkill(Node img){
        img.setEffect(null);
        img.setDisable(false);
    }

    public void enableSkill(int player,boolean isInvert){
        if(isInvert==false){
            if(select_skill[player-1].equals("skill_heal")){
                enableSkill(skill_heal);
            }
            else if(select_skill[player-1].equals("skill_trap")){
                enableSkill(skill_trap);
            }
            else if(select_skill[player-1].equals("skill_shield")){
                enableSkill(skill_shield);
            }
            else if(select_skill[player-1].equals("skill_stun")){
                enableSkill(skill_stun);
            }
            else if(select_skill[player-1].equals("skill_random")){
                enableSkill(skill_random);
            }
            else if(select_skill[player-1].equals("skill_poison")){
                enableSkill(skill_poison);
            }
        }
        else {
            if(player==2&select_skill[0]!=null){
                player=0;
            }
            else{
                player=1;
            }
            if(select_skill[player].equals("skill_heal")){
                //enableSkill(skill_heal);
                enableSkill(skill_trap);
                enableSkill(skill_shield);
                enableSkill(skill_stun);
                enableSkill(skill_random);
                enableSkill(skill_poison);
            }
            else if(select_skill[player].equals("skill_trap")){
                enableSkill(skill_heal);
                //enableSkill(skill_trap);
                enableSkill(skill_shield);
                enableSkill(skill_stun);
                enableSkill(skill_random);
                enableSkill(skill_poison);
            }
            else if(select_skill[player].equals("skill_shield")){
                enableSkill(skill_heal);
                enableSkill(skill_trap);
                //enableSkill(skill_shield);
                enableSkill(skill_stun);
                enableSkill(skill_random);
                enableSkill(skill_poison);
            }
            else if(select_skill[player].equals("skill_stun")){
                enableSkill(skill_heal);
                enableSkill(skill_trap);
                enableSkill(skill_shield);
                //enableSkill(skill_stun);
                enableSkill(skill_random);
                enableSkill(skill_poison);
            }
            else if(select_skill[player].equals("skill_random")){
                enableSkill(skill_heal);
                enableSkill(skill_trap);
                enableSkill(skill_shield);
                enableSkill(skill_stun);
                //enableSkill(skill_random);
                enableSkill(skill_poison);
            }
            else if(select_skill[player].equals("skill_poison")){
                enableSkill(skill_heal);
                enableSkill(skill_trap);
                enableSkill(skill_shield);
                enableSkill(skill_stun);
                enableSkill(skill_random);
                //enableSkill(skill_poison);
            }
        }

    }

    public void selectSkill(MouseEvent mouseEvent){
        //Get node name
        String skill = mouseEvent.getPickResult().getIntersectedNode().getId();
        //Apply effect to node
        disableSkill(mouseEvent.getPickResult().getIntersectedNode());
        int select=0;
        for (int i = 0; i < select_skill.length; i++) {
            if(select_skill[i]==null){
                select=i;
                break;
            }
        }
        try {
            select_skill[select]= skill;
            showSelectSkill(select+1,skill);
            if (select_skill[1]!=null){
                disableSkill();
                btn_start.setEffect(null);
                btn_start.setDisable(false);
            }
        }
        catch (IOException e){

        }
        //System.out.println(skill);
        debugSelect();


    }

    public void debugSelect(){
        for (String x:select_skill) {
            System.out.println("DEB: "+x);
        }
        System.out.println("--------------");
    }

    public void releaseSkill(MouseEvent mouseEvent){
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        grayscale.setBrightness(-0.2);
        String player = mouseEvent.getPickResult().getIntersectedNode().getId().substring(6,7);
        int player_id = Integer.parseInt(player);
        debugSelect();
        if (select_skill[1]!=null){
            enableSkill(player_id,true);
            btn_start.setDisable(true);
            btn_start.setEffect(grayscale);
        }

        try {

            if(player_id==1){
                player1_release.setVisible(false);
                player1_preview.setImage(null);
                enableSkill(1,false);
                select_skill[0] = null;
            }
            else {
                player2_release.setVisible(false);
                player2_preview.setImage(null);
                enableSkill(2,false);
                select_skill[1] = null;
            }
            debugSelect();
        }
        catch (NullPointerException e){
            System.out.println("NULL!!!!!");
        }
    }

    public void createPlayer(){
        debugSelect();
       Game.addPlayer(new Charactor(Game.transfromSkill(select_skill[0])));
       Game.addPlayer(new Charactor(Game.transfromSkill(select_skill[1])));
    }

    public void showHowto(MouseEvent mouseEvent) throws IOException {
        System.out.println("----howto-----");
        debugSelect();
        stage=(Stage) btn_start.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/howtoplay.fxml"));
        stage.getScene().setRoot(root);
        Scene scene = stage.getScene();
        System.out.println("Highscore btn press");
        System.out.println("xxcx");
    }


}
