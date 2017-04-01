package com.fruitnaja;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Created by nutno on 1/4/2560.
 */
public class Controller {
    @FXML
    void buttonOnAction(ActionEvent event) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Fruitnaja(), config);
    }
}
