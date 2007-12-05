package org.kuhn.oe.game;


public class Player implements Comparable<Player> {
	private PlayExecutor playExecutor;
	
	public Player(PlayExecutor playExecutor) {
		this.playExecutor = playExecutor;
	}
	
	public PlayExecutor getPlayExecutor() {
		return playExecutor;
	}
	
	public void win() {
		++wins;
	}
	public int getWins() {
		return wins;
	}
	public void lose() {
		++loses;
	}
	public int getLoses() {
		return loses;
	}
	public double getWinLoseRatio() {
		return (double)wins / (double)loses;
	}
	public void draw() {
		++draws;
	}
	public int getDraws() {
		return draws;
	}
	public double getFitness() {
		return wins;
	}

	public int compareTo(Player o) {
		double f0 = getFitness();
		double f1 = o.getFitness();
		if (f0 < f1)
			return 1;
		else if (f0 == f1)
			return 0;
		else
			return -1;
	}

	int wins = 0;
	int loses = 0;
	int draws = 0;
}
