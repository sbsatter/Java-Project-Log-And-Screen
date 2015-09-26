package TickTackToe;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScreenController implements Initializable{
	@FXML
	Button hard;
	@FXML
	Button easy;
	@FXML
	Button p1;
	@FXML
	Button p2;
	@FXML
	Button newGame;
	int easiness = 0;
	int player = 1;
	gameVarSetter gvs;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		gvs = new gameVarSetter();
		starter();
		
	}
	public void starter(){
		newGame.setOnAction(e -> {
			if(gvs.getP() == -1){
				gvs.setP(1);
			}
			if(gvs.getE() == -1){
				gvs.setE(0);
			}
			Parent homePage = null;
			
			try {
				homePage = FXMLLoader.load(getClass().getResource("gameBoard.fxml"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene pane = new Scene(homePage);
			Stage stage2 = (Stage)((Node) e.getSource()).getScene().getWindow();
			stage2.setScene(pane);
			stage2.show();
		});
		
		p1.setOnAction(e -> {
			gvs.setP(1);
		});

		p2.setOnAction(e -> {
			gvs.setP(2);
		});
		easy.setOnAction(e -> {
			gvs.setE(0);
		});
		hard.setOnAction(e -> {
			gvs.setE(1);
		});
	}
	

}
