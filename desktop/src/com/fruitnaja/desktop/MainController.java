package com.fruitnaja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fruitnaja.Configure;
import com.fruitnaja.Fruitnaja;
import com.fruitnaja.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.Inet4Address;


/**
 * Created by nutno on 5/4/2560.
 */
public class MainController{

    @FXML
    private Button backBtn;
    @FXML
    private ImageView btn_start,btn_back,btn_highscore;
    private Stage stage;
    private Parent root;
    @FXML
    private Label txt_ip = new Label();

    @FXML
    public void initialize() throws Exception{
        txt_ip.setText(Inet4Address.getLocalHost().getHostAddress());
    }

    @FXML
    private void backToMain(MouseEvent actionEvent) throws IOException {
        stage=(Stage) btn_back.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("layouts/main.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("layouts/highscore.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Highscore btn press");
    }

    public void exitGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void showOption(MouseEvent mouseEvent) throws IOException {
        stage=(Stage) btn_highscore.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("layouts/option.fxml"));
        stage.getScene().setRoot(root);
        System.out.println("Option btn press");
    }

    public void playMao(MouseEvent mouseEvent) {
        Music.play(2);
    }
}
