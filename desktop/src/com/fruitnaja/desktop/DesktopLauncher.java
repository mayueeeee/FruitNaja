package com.fruitnaja.desktop;

import com.fruitnaja.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class DesktopLauncher extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		//File file = new File("layouts/main.fxml");
		//System.out.println(file.getAbsolutePath());
		//System.out.println(file.exists());
		Parent root = FXMLLoader.load(new URL("file:layouts/main.fxml"));
		primaryStage.setTitle("FruitNaJa");
		primaryStage.setScene(new Scene(root, Configure.WIDTH, Configure.HEIGH));
		//primaryStage.setResizable(false);
		primaryStage.show();
		//System.out.println(Inet4Address.getLocalHost().getHostAddress());
		Thread music = new Thread(){
			public void run(){
				Music.play();
				System.out.println("Music Thread");
			}
		};
		music.start();

		Thread client = new Thread(){
			public void run(){
				//Music.playEDM();

				//SocketClient client = new SocketClient("192.168.1.216");
				System.out.println("Client Thread");
			}
		};



		SocketServer server = new SocketServer();

		//System.out.println("uuu");





		/*class LaunchGDX implements EventHandler<ActionEvent>{
			@Override // Override the handle method
			public void handle(ActionEvent e) {
				Configure.loadConfigure();
				//System.out.println("xxx");
				LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
				config.forceExit = false;
				config.width = Configure.WIDTH;
				config.height = Configure.HEIGH;
				new LwjglApplication(new Fruitnaja(), config);
			}
		}*/

	}
	public static void main (String[] arg) {
		launch(arg);

	}
}
