package pt.europeia.dominate.application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pt.europeia.dominate.application.Main;
import javafx.scene.Scene;


public class Main extends Application {
		
	Scene game;
	
	@Override
	public void start(Stage primaryStage) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/GDominate.fxml"));
		try {
			
			game = new Scene(loader.load());
			primaryStage = new Stage();
			primaryStage.setScene(game);
//			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
