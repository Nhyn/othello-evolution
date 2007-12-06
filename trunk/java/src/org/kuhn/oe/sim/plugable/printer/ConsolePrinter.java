package org.kuhn.oe.sim.plugable.printer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import org.kuhn.oe.game.Player;

public class ConsolePrinter implements Printer {
	private PrintStream out;
	public ConsolePrinter() {
		this(System.out);
	}
	public ConsolePrinter(OutputStream outputStream) {
		this.out = new PrintStream(outputStream, true);
	}
	public void printSimulationBanner(List<Player> population) {
	}
	public void printGenerationHeader(List<Player> population) {
		out.println("Generation " + ++generationNumber + ":");
	}
	public void printTestResult(List<Player> population) {
		Collections.sort(population);
		Player player = population.get(0);
		double max = 0.0;
		for (double weight : player.getWeights())
			if (Math.abs(weight) > max)
				max = Math.abs(weight);
		out.println();
		for (int i = 0; i < player.getStrategies().length; ++i) {
			String name = player.getStrategies()[i].getClass().getSimpleName();
			double weight = player.getWeights()[i];
			out.print(String.format("%35s: %+1.2f", name, weight));
			if (max > 0.0) {
				String bar = "";
				int num = (int)((Math.abs(weight) / max) * 40);
				for (int j = 0; j < num; ++j)
					bar += "X";
				out.print(String.format(" %40s | %-40s", weight < 0.0 ? bar : "", weight > 0.0 ? bar : ""));
			}
			out.println();
		}
		out.println();
		out.println("     Wins : " + player.getWins());
		out.println("    Loses : " + player.getLoses());
		out.println("    Draws : " + player.getDraws());
		out.println();
	}
	private int generationNumber = 0;
}