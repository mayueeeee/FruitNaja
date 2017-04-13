package com.fruitnaja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fruitnaja.Configure;
import com.fruitnaja.Fruitnaja;
import com.fruitnaja.Music;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView btn_start,btn_back,btn_highscore,btn_skill;
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
    private int click_count=0;
    private String[] select_skill = new String[2];


    /* Methods for called by FXML */
    @FXML
    //All pages -- Back button
    private void backToMain(MouseEvent actionEvent) throws IOException {
        stage=(Stage) btn_back.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/main.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Back btn press");
    }

    //Select skill page -- Start Button
    public void runGDX(MouseEvent mouseEvent)  {
        System.out.println("Play btn press");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        config.width = Configure.WIDTH;
        config.height = Configure.HEIGH;
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
        stage=(Stage) btn_skill.getScene().getWindow();
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
    public String randomSkill(){
        int skill = (int)(Math.random()*5+1);
        switch (skill){
            case 1: return "skill_heal";
            case 2: return "skill_shield";
            case 3: return "skill_poison";
            case 4: return "skill_stun";
            case 5: return "skill_trap";
            default: return "ERROR!";
        }
    }

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

    public void showSelectSkill() throws IOException{
        String random;
        for (int i = 1; i <= 2; i++) {
            random = randomSkill();
            if (i==2){
                while (select_skill[0].equals(random)){
                    random = randomSkill();
                }
            }
            select_skill[i-1] = random;
            showSelectSkill(i,select_skill[i-1]);
        }
        disableSkill();

//        for (String x:select_skill) {
//            System.out.println(x);
//
//        }
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

    public void selectSkill(MouseEvent mouseEvent){
        //Get node name
        String skill = mouseEvent.getPickResult().getIntersectedNode().getId();
        //Apply effect to node
        disableSkill(mouseEvent.getPickResult().getIntersectedNode());
        try {
            if (skill.equals("skill_random")){
                showSelectSkill();
                System.out.println("Rand");
            }
            else {
                click_count++;
                showSelectSkill(click_count,skill);
                if(click_count==2){
                    disableSkill();
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }



        System.out.println(skill);


    }

    public void releaseSkill(MouseEvent mouseEvent){
        String player = mouseEvent.getPickResult().getIntersectedNode().getId().substring(6,7);
        int player_id = Integer.parseInt(player);
        if(player_id==1){
            player1_release.setVisible(false);
            player1_preview.setImage(null);
        }
        else {
            player2_release.setVisible(false);
            player2_preview.setImage(null);
        }
        click_count--;
        System.out.println("Release "+player);
    }

    public void createPlayer(){

    }


}
