package pt.europeia.dominate.application;

import java.util.ArrayList;
import java.util.Arrays;

public class Dominate {

	private pieces[][] table;
	private Player[] players;
	private pieces turn;
	private ArrayList tablePlays;
	ArrayList<ArrayList> toEat;

	//Enumeration of pieces (WHITE, BLACK)
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
		table[3][4] = pieces.BLACK;
		table[4][4] = pieces.WHITE;

		tablePlays = new ArrayList<int[]>();
		toEat = new ArrayList<ArrayList>();

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

		toEat = new ArrayList<ArrayList>();

		//Reset this arrays
		tablePlays = new ArrayList<int[]>();

		players[0].resetPoints();
		players[1].resetPoints();

		//Verifies pieces possible playable positions
		//Verify x
		for(int i = 0; i < 8; i++) {

			//Verify y
			for(int j = 0; j < 8; j++) {

				//Depending on the type of piece it adds points to the designated player
				if(table[i][j] == pieces.WHITE) {
					players[0].addPoint();
				} else if(table[i][j] == pieces.BLACK){
					players[1].addPoint();
				}

				//Verifies 3 horizontal cells (-1,0,1) according do (i,j) cell
				for(int n = -1; n < 2; n++) {

					//Verifies 3 vertical cells (-1,0,1) according do (i,j) cell
					for(int m = -1; m < 2; m++) {

						if(n == 0 && m == 0) {
							continue;
						}

						for(int k = i+n, p = j+m; k > 0 && p > 0 && k < 8 && p < 8 && table[i][j] == turn && table[k][p] == turn.opposite(); k += n , p += m) {

							//Verifies if the next cell in the direction that is going is null if so it's a playable cell
							if(k+n < 8 && p+m < 8 && table[k+n][p+m] == null) {
								
								int[] temps = {k+n,p+m};
								tablePlays.add(temps);
								
								ArrayList<int[]> tempEatable = new ArrayList<int[]>();
								while(k != i || p != j) {
									
									tempEatable.add(new int[] {
											k,p
									});
									k -= n;
									p -= m;
								}
								toEat.add(tempEatable);					
								break;
							}

						}
					}
				}
			}
		}

	}

	/**
	 * Makes a move on the table
	 * @param i move x location
	 * @param j move y location
	 */
	public void move(int i, int j) {

		int[] temporary = {i,j};

		for(Object each : tablePlays) {
			
			if(Arrays.equals((int[])each, temporary)) {
				table[i][j] = turn;
				
				ArrayList<int[]> eat = toEat.get(tablePlays.indexOf(each));
				
				for(int q = 0; q < eat.size(); q++) {
					table[eat.get(q)[0]][eat.get(q)[1]] = turn;
				}
			} 
		}		

		if(table[i][j] == null) {
			return;
		}

		turn = turn.opposite();

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

	/*public static void main(String [] args) {

		Dominate a = new Dominate();
		for(Object temp : a.getTablePlays()) {
			System.out.println(Arrays.toString((int[])temp));
		}
	}*/

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
