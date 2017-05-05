package pt.europeia.dominate.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import pt.europeia.dominate.application.Dominate;

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

		update();


	}
	
	/**
	 * Updates the canvas
	 */
	public void update() {
		for(int i = 0; i < game.getTable().length; i++) {
			
			for(int j = 0; j < game.getTable().length; j++) {
				
				if(game.getTable()[i][j] == 1) {
					gc.setFill(Color.BLACK);
					gc.fillOval(i*square, j*square, square, square);
				} else
					if(game.getTable()[i][j] == 2) {
						gc.setFill(Color.RED);
						gc.fillOval(i*square, j*square, square, square);
					}
				
			}
		}
		
		p1.setText(game.getP1Score()+"");
		p2.setText(game.getP2Score()+"");
		
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

		if(game.getTable()[x][y] == 0) {
			game.move(x, y);
			update();
		}
	}
}
