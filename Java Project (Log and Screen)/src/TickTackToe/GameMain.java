package TickTackToe;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class GameMain extends Application {
	SendMail mail = new SendMail();
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			//mail.testMailSender();
			BorderPane root = new BorderPane();
			StackPane page = (StackPane)FXMLLoader.load(GameMain.class.getResource("Screen.fxml"));
			Scene scene = new Scene(page);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tick Tack Toe");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			mail.errSender(e);
		}
	}
	
	public static void gameLauncher() {
		ScreenShot ss = new ScreenShot();
		ss.start();
//		launch(args);
		
		
	}
}
