package pt.europeia.dominate.controllers;

import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.europeia.dominate.application.Dominate;
import pt.europeia.dominate.application.Dominate.pieces;

public class GameController {

	Dominate game;	
	GraphicsContext gc;
	double square;
	int i = 0, j = 0;

	@FXML
	Canvas canvas = new Canvas();

	@FXML
	Text p1, p2;
	
	@FXML
	Text pturn;

	@FXML
	public void initialize() {

		square = canvas.getWidth()/8;

		gc = canvas.getGraphicsContext2D();

		game = new Dominate();

		for(int i = 0; i < canvas.getWidth(); i += canvas.getWidth()/8) {
			gc.setStroke(Color.BLACK);
			gc.strokeLine(0, i, canvas.getWidth(), i);
			gc.strokeLine(i, 0, i, canvas.getWidth());
		}

		p1.setText(game.getP1Score()+"");
		p2.setText(game.getP2Score()+"");
		pturn.setText(game.getTurn()+"");

		update();


	}

	/**
	 * Updates the canvas
	 */
	public void update() {
		for(int i = 0; i < game.getTable().length; i++) {

			for(int j = 0; j < game.getTable().length; j++) {

				if(game.getTable()[i][j] == pieces.BLACK) {
					gc.setFill(Color.BLACK);
					gc.fillOval(i*square, j*square, square, square);
				} else if(game.getTable()[i][j] == pieces.WHITE) {
					gc.setFill(Color.WHITE);
					gc.strokeOval(i*square, j*square, square, square);
					gc.fillOval(i*square, j*square, square, square);
				}

			}
		}
		
		/*for(int i = 0; i < game.getTablePlays().size(); i++) {
			gc.fillOval(game.getTablePlays().get(i), game.getTablePlays().get(i), square/3, square/3);
		}*/

		p1.setText(game.getP1Score()+"");
		p2.setText(game.getP2Score()+"");
		
		System.out.println(game.getTurn());
		for (Object each : game.getTablePlays()) {
			System.out.println(Arrays.toString((int[])each));
		}

	}

	public void move(MouseEvent event) {

		int x = 0, y = 0;

		for(double i = 1; i*square <= canvas.getWidth(); i++ ) {
			if(event.getX() < i*square) {
				x = (int) i-1;
				break;
			}		
		}

		for(double i = 1; i*square <= canvas.getWidth(); i++ ) {
			if(event.getY() < i*square) {
				y = (int) i-1;
				break;
			}		
		}
		
		System.out.println("cell: " + x + " ; " + y);
		
		game.move(x, y);
		pturn.setText(""+game.getTurn());
		update();
		
		//End of the game
		if((game.getP1Score() + game.getP2Score()) == 64) {
			Group bp = new Group();
			Text winner = new Text(20,30, game.getWinner() + "'s " + "WON");
			bp.getChildren().add(winner);
			Scene scene = new Scene(bp,100,50);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		}
			
	}
	
	
}
