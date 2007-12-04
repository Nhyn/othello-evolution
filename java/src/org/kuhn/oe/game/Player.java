package org.kuhn.oe.game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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
//		int total = wins + loses + draws;
//		if (total == 0) return 0.0d;
//		return (double)wins / (double)total;
	}
	@Override
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
	
	public void print(OutputStream out) throws IOException {
		double max = 0.0;
		for (double weight : this.getPlayExecutor().getWeights())
			if (Math.abs(weight) > max)
				max = Math.abs(weight);
		
		OutputStreamWriter writer = new OutputStreamWriter(out);
		for (int i = 0; i < this.getPlayExecutor().getStrategies().length; ++i) {
			String name = this.getPlayExecutor().getStrategies()[i].getClass().getSimpleName();
			double weight = this.getPlayExecutor().getWeights()[i];
			writer.append(String.format("%35s: %+1.2f", name, weight));
			if (max > 0.0) {
				String bar = "";
				int num = (int)((Math.abs(weight) / max) * 40);
				for (int j = 0; j < num; ++j)
					bar += "X";
				writer.append(String.format(" %40s | %-40s", weight < 0.0 ? bar : "", weight > 0.0 ? bar : ""));
			}
			writer.append("\r\n");
		}
		writer.append("\r\n");
		writer.append("     Wins : " + wins + "\r\n");
		writer.append("    Loses : " + loses + "\r\n");
		writer.append("    Draws : " + draws + "\r\n");
		writer.flush();
	}

	int wins = 0;
	int loses = 0;
	int draws = 0;
}
