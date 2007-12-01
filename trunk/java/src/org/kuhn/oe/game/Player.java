package org.kuhn.oe.game;

public class Player {
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
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (double weight : playExecutor.getWeights()) {
			buf.append(weight);
			buf.append(" ");
		}
		buf.append("wins: " + wins + ", loses: " + loses + ", ratio: " + getWinLoseRatio());
		return buf.toString();
	}

	int wins = 0;
	int loses = 0;
}
