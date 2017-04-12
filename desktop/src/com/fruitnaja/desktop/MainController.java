package com.fruitnaja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fruitnaja.Configure;
import com.fruitnaja.Fruitnaja;
import com.fruitnaja.Music;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.net.Inet4Address;
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
    private Parent root;
    private Label txt_ip = new Label();

    //Object in Charactor select
    @FXML
    private ImageView player1_preview,player2_preview;
    @FXML
    private Text player1_release,player2_release;

    @FXML
    public void initialize() throws Exception{
        txt_ip.setText(Inet4Address.getLocalHost().getHostAddress());
    }

    @FXML
    private void backToMain(MouseEvent actionEvent) throws IOException {
        stage=(Stage) btn_back.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/main.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Back btn press");
    }

    public void runGDX(MouseEvent mouseEvent)  {
        System.out.println("Play btn press");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        config.width = Configure.WIDTH;
        config.height = Configure.HEIGH;
        new LwjglApplication(new Fruitnaja(), config);
    }

    public void showHighScore(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_highscore.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/highscore.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Highscore btn press");
    }

    public void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void showOption(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_highscore.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/option.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Option btn press");
    }

    public void playMao(MouseEvent mouseEvent) {
        Music.play(2);
    }

    public void playNormal(MouseEvent mouseEvent) {
        Music.play(2);
    }

    public void playEDM(MouseEvent mouseEvent) {
        Music.play(1);
    }

    public void stopMusic(MouseEvent mouseEvent) {
        Music.stop();
    }

    public void showSelectSkill(int player,String skill) throws IOException{
        Image x = new Image(new URL("file:skill/shield/skill1_0006_RStand.png").toString());
        //System.out.println(new URL("file:skill/shield/skill1_0006_RStand.png").toString());
        if (player==1){
            player1_release.setVisible(true);
        }
        else if (player==2){
            player2_release.setVisible(true);
        }
        else {

        }


        if (skill.equals("skill_heal")){
            player1_preview.setImage(x);
            System.out.println("mui");
        }
    }

    public int selectSkill(MouseEvent mouseEvent){
        //Grayscale Effect for disable skill
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1);
        grayscale.setBrightness(-0.2);
        //Get node name
        String skill = mouseEvent.getPickResult().getIntersectedNode().getId();
        //Apply effect to node
        mouseEvent.getPickResult().getIntersectedNode().setEffect(grayscale);
        mouseEvent.getPickResult().getIntersectedNode().setDisable(true);
        try {
            showSelectSkill(1,skill);
            System.out.println("MUI");
        }
        catch (IOException e){
            e.printStackTrace();
        }



        System.out.println(skill);

        //System.out.println("xxxxx");
        return 1;
    }

    public void showSkill(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_skill.getScene().getWindow();
        root = FXMLLoader.load(new URL("file:layouts/character-select.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Skill btn press");
    }
}
