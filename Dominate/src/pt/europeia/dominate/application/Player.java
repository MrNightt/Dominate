package pt.europeia.dominate.application;

public class Player {
	
	private int moves;
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public void resetPoints() {
		this.points = 0;
	}
	
	public void addPoint() {
		this.points += 1;
	}

	public int getMoves() {
		return moves;
	}
	

}
