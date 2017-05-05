package pt.europeia.dominate.application;

public class Dominate {

	private int[][] table;
	private Player[] players;
	private int turn;

	/**Constructor in which the maximum of players is 4
	 * 
	 * @param players
	 */
	public Dominate() {
		table = new int[8][8];		
		players = new Player[2];

		players[0] = new Player();
		players[1] = new Player();

		players[0].setPoints(2);
		players[1].setPoints(2);

		turn = 1;
		
		move(3,3);
		move(4,3);
		move(4,4);
		move(3,4);		

	}	
	
	//The algorithm
	public void update(int i, int j) {
		
		if(turn == 1) {
			turn = 2;
		} else {
			turn = 1;
		}
		
	}

	public void move(int i, int j) {

		if(table[i][j] == 0) {
			table[i][j] = turn;
		} else {
			return;
		}
		
		update(i,j);
		
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

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getP1Score() {
		return players[0].getPoints();
	}

	public int getP2Score() {
		return players[1].getPoints();
	}

	public int getTurn() {
		return turn;
	}
	
	public int[][] getTable() {
		return table;
	}
	
}
