package pt.europeia.dominate.application;

import java.util.ArrayList;
import java.util.Arrays;

public class Dominate {

	private pieces[][] table;
	private Player[] players;
	private pieces turn;
	private ArrayList tablePlays;

	public enum pieces {
		WHITE, BLACK;

		public pieces opposite() {
			if(this == pieces.WHITE) {
				return pieces.BLACK;
			} else {
				return pieces.WHITE;
			}
		}
	}

	/**Constructor in which the maximum of players is 2
	 * 
	 * @param players
	 */
	public Dominate() {

		table = new pieces[8][8];

		table[3][3] = pieces.WHITE;
		table[4][3] = pieces.BLACK;
		table[3][4] = pieces.WHITE;
		table[4][4] = pieces.BLACK;

		tablePlays = new ArrayList<int[]>();
		players = new Player[2];

		players[0] = new Player();
		players[1] = new Player();

		players[0].setPoints(2);
		players[1].setPoints(2);

		turn = pieces.WHITE;

		verify();

	}	

	//Verifies the playable positions
	public void verify() {

		players[0].resetPoints();
		players[1].resetPoints();

		//Verifies pieces possible playable positions
		for(int i = 0; i < 8; i++) {

			for(int j = 0; j < 8; j++) {

				if(table[i][j] == pieces.WHITE) {
					players[0].addPoint();
				} else {
					players[1].addPoint();
				}

				for(int n = -1; n < 2; n++) {

					for(int m = -1; m < 2; m++) {

						if(n == 0 && m == 0) {
							continue;
						}

						for(int k = i+n, p = j+m; table[k][p] == turn.opposite() && k > 0 && p > 0 && k < 8 && p < 8; k += n , p += m) {

							if(table[k+n][p+m] == null) {
								int[] temps = {k+n,p+m};
								tablePlays.add(temps);
								break;
							}

						}
					}
				}
			}
		}

		turn = turn.opposite();

	}

	/**
	 * Makes a move on the table
	 * @param i move x location
	 * @param j move y location
	 */
	public void move(int i, int j) {

		int[] temps = {i,j};

		if(tablePlays.contains(temps)) {
			table[i][j] = turn;
		} else {
			return;
		}

		verify();

	}

	/**
	 * Determines the winner
	 * @return
	 */
	public int dominator() {

		Player temp = new Player();
		int winner = 0;

		//Verifies who is the player with most points
		for (int i = 0; i < players.length; i++) {
			if (temp.getPoints() < players[i].getPoints()) {
				temp = players[i];
				winner = i;
			}
		}

		return winner;
	}

	public int getP1Score() {
		return players[0].getPoints();
	}

	public int getP2Score() {
		return players[1].getPoints();
	}

	public pieces getTurn() {
		return turn;
	}

	public pieces[][] getTable() {
		return table;
	}

	public ArrayList getTablePlays() {
		return tablePlays;
	}

}
