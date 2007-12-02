package org.kuhn.oe.game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import org.kuhn.oe.strategy.Strategy;

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
		writer.flush();
	}

	int wins = 0;
	int loses = 0;
}
