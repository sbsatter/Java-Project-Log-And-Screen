package TickTackToe;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;
public class BoardController implements Initializable {
	@FXML
	MenuButton choosePlayer;
	@FXML
	Button newGame;
	
	@FXML
	Button b0;
	@FXML
	Button b1;
	@FXML
	Button b2;
	@FXML
	Button b3;
	@FXML
	Button b4;
	@FXML
	Button b5;
	@FXML
	Button b6;
	@FXML
	Button b7;
	@FXML
	Button b8;
	@FXML
	Label message;
	@FXML
	Label label;
	
//	
//	@FXML
//	Button p1;
//	@FXML
//	Button p2;
	
	String user;
	String com;
	boolean noMove = true;
	boolean comPlayer = false;
	int[] a = new int[9];
	int easiness = -1;
	int lastMove = 0;
	int player = -1;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startGame();
		moveButtons();
	}
	
	public void startGame(){
		startNewGame();
		
		newGame.setOnAction(e -> {
Parent homePage = null;
			
			try {
				homePage = FXMLLoader.load(getClass().getResource("Screen.fxml"));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Scene pane = new Scene(homePage);
			Stage stage2 = (Stage)((Node) e.getSource()).getScene().getWindow();
			stage2.setScene(pane);
			stage2.show();
				
		});
		
	}
	
	public void moveButtons(){
		b0.setOnAction(e -> {
			if(a[0] == -1 && !comPlayer){
				lastMove++;
				b0.setText(user);
				a[0] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b1.setOnAction(e -> {
			if(a[1] == -1&& !comPlayer){
				lastMove++;
				b1.setText(user);
				a[1] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness==0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b2.setOnAction(e -> {
			if(a[2] == -1 && !comPlayer){
				lastMove++;
				b2.setText(user);
				a[2] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b3.setOnAction(e -> {
			if(a[3] == -1 && !comPlayer){
				lastMove++;
				b3.setText(user);
				a[3] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b4.setOnAction(e -> {
			if(a[4] == -1 && !comPlayer){
				lastMove++;
				b4.setText(user);
				a[4] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b5.setOnAction(e -> {
			if(a[5] == -1 && !comPlayer){
				lastMove++;
				b5.setText(user);
				a[5] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b6.setOnAction(e -> {
			if(a[6] == -1 && !comPlayer){
				b6.setText(user);
				a[6] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b7.setOnAction(e -> {
			if(a[7] == -1 && !comPlayer){
				lastMove++;
				b7.setText(user);
				a[7] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
		b8.setOnAction(e -> {
			if(a[8] == -1 && !comPlayer){
				lastMove++;
				b8.setText(user);
				a[8] = 0;
				noMove = false;
				comPlayer = true;
				if(easiness == 0)
					comMoveRandom();
				else {
					computerMoveMedium();
				}
			}
		});
	}
	public void startNewGame(){
		gameVarSetter gvs = new gameVarSetter();
		easiness = gvs.getE();
		player = gvs.getP();
		//System.out.println("easiness | player " + easiness + " " + player);
		
		b0.setText("");
		b1.setText("");
		b2.setText("");
		b3.setText("");
		b4.setText("");
		b5.setText("");
		b6.setText("");
		b7.setText("");
		b8.setText("");
		for(int i = 0; i < a.length; i++){
			a[i] = -1;
		}
		if(easiness == 0){
			label.setText("Easy Mode");
		} else if(easiness == 1){
			label.setText("Hard Mode");
		}
		if(player == 1){
			message.setText("You are Player 01");
			user = "X";
			com = "O";
			comPlayer = false;
			lastMove = 0;
		} else if(player == 2){
			message.setText("You are Player 02");
			user = "O";
			com = "X";
			comPlayer = true;
			noMove = false;
			lastMove = 1;
			int random = (int) (Math.random()*9) + 0;
			setComputerMoves(random);
		}
	}
	
	public int generateRandom(){
		int random = (int) (Math.random()*9) + 0;
		if(a[random] != -1 && lastMove < 5){
			return generateRandom();
		} else 
			return random;
	}
	public void comMoveRandom(){
		printA();
		if(gameOver()){
			gameOverAlert();
		} else {
			int random = generateRandom();
			setComputerMoves(random);
			if(gameOver()){
				gameOverAlert();
				//startGame();
			}
		}
	}
	public void setComputerMoves(int random){
		if(comPlayer){
			a[random] = 1;
			comPlayer = false;
			noMove = false;
			if(random == 0){
				b0.setText(com);
			} 
			else if(random == 1){
				b1.setText(com);
			}
			else if(random == 2){
				b2.setText(com);
			} 
			else if(random == 3){
				b3.setText(com);
			} 
			else if(random == 4){
				b4.setText(com);
			} 
			else if(random == 5){
				b5.setText(com);
			} 
			else if(random == 6){
				b6.setText(com);
			} 
			else if(random == 7){
				b7.setText(com);
			} 
			else if(random == 8){
				b8.setText(com);
			}
		}
	}
	 
	public int computerMoves(){
	    int pos = -1;
	    int random = generateRandom();
	    for(int i = 0; i < 9; i++){
	      if(a[i] == -1){
	        pos = i;
	        a[pos] = 1;
	        if(comWins()){
	          a[pos] = -1;
	          return pos;
	        }
	        a[pos] = -1;
	      }
	    }
	    for(int i = 0; i < 9; i++){
	    	if(a[i] == -1){
		        pos = i;
		        a[pos] = 0;
		        if(userWins()){
		          a[pos] = -1;
		          return pos;
		        }
		        a[pos] = -1;
		      }
	    }
	    return random;
	    }
	public void computerMoveMedium(){
		printA();
		if(gameOver()){
			gameOverAlert();
		} else {
			int pos = computerMoves();
			a[pos] = 1;
			setComputerMoves(pos);
			if(gameOver()){
				gameOverAlert();
				//startGame();
			}
		}
	}
	
	
	public void gameOverAlert(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Game Over!");
		if(comWins()){
			label.setText("Game Over! Computer wins!");
			alert.setHeaderText("Game Over! Computer wins!");
		} else if(userWins()){
			label.setText("Game Over! You wins!");
			alert.setHeaderText("Game Over! You win!");
		} 
		else{
			label.setText("Game Over! It's a draw!");
			alert.setHeaderText("Game Over! It's a draw!");
		}
		alert.setContentText("Do you want to play again?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			startNewGame();
			
		} else {
		    
		}
	}
	
	public void printA(){
//		for(int i = 0; i <= 8; i+=3){
//			System.out.printf("%5d, %5d, %5d, %s" , a[i], a[i+1], a[i+2],"\n");
//		}
//		System.out.println();
	}
	
	//game winning logic
	public boolean isFull(){
		for(int i = 0; i < 9 ; i++){
			if(a[i] == -1){
				return false;
			}
		}
		return true;
	}
	public boolean userWins(){
		//diagonal
		if(a[0] == 0 && a[4] == 0 && a[8] == 0){
			return true;
		} 
		else if(a[2] == 0 && a[4] == 0 && a[6] == 0){
			return true;
		} else {				 //row-col
			if(a[0] == 0 && a[3] == 0 && a[6] ==0){
				return true;
			} 
			else if(a[1] == 0 && a[4] == 0 && a[7] == 0){
				return true;
			}
			else if(a[2] == 0 && a[5] == 0 && a[8] == 0){
				return true;
			} else {
				for(int i = 0; i < 9; i+= 3){
					if(a[i] == 0 && a[i+1] == 0 && a[i+2] == 0){
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	public boolean comWins(){
		//diagonal
		if(a[0] == 1 && a[4] == 1 && a[8] == 1){
			return true;
		} 
		else if(a[2] == 1 && a[4] == 1 && a[6] == 1){
			return true;
		}else {				//row col
			if(a[0] == 1 && a[3] == 1 && a[6] == 1){
				return true;
			} 
			else if(a[1] == 1 && a[4] == 1 && a[7] == 1){
				return true;
			}
			else if(a[2] == 1 && a[5] == 1 && a[8] == 1){
				return true;
			} else {
				for(int i = 0; i < 9; i+= 3){
					if(a[i] == 1 && a[i+1] == 1 && a[i+2] == 1){
						return true;
					}
				}
			}
			
		}
		
		return false;
	}
	public boolean gameOver(){
		if(isFull()){
			if(!userWins() && !comWins()){
				return true;
			} else {
				return true;
			}
		} else {
			if(userWins()){
				return true;
			} else if(comWins()){
				return true;
			} else {
				return false;
			}
		}
	}
}
