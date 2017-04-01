package com.fruitnaja.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fruitnaja.Fruitnaja;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopLauncher extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();

		class LaunchGDX implements EventHandler<ActionEvent>{
			@Override // Override the handle method
			public void handle(ActionEvent e) {
				//System.out.println("xxx");
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				new LwjglApplication(new Fruitnaja(), config);
			}
		}

	}
	public static void main (String[] arg) {
		launch(arg);

	}
}
