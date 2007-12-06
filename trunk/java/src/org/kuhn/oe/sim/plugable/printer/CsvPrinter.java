package org.kuhn.oe.sim.plugable.printer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.strategy.Strategy;

public class CsvPrinter implements Printer {
	private PrintStream out;
	public CsvPrinter() {
		this(System.out);
	}
	public CsvPrinter(OutputStream outputStream) {
		this.out = new PrintStream(outputStream, true);
	}
	public void printSimulationBanner(List<Player> population) {
		out.print("generation,");
		for (Strategy s : population.get(0).getStrategies()) {
			out.print(s.getClass().getSimpleName());
			out.print(",");
		}
		out.println("wins,loses,ratio");
	}
	public void printGenerationHeader(List<Player> population) {
	}
	public void printTestResult(List<Player> population) {
		out.print(++generationNumber);
		out.print(",");
		Collections.sort(population);
		Player player = population.get(0);
		for (double weight : player.getWeights()) {
			out.print(weight);
			out.append(",");
		}
		out.print(player.getWins());
		out.print(",");
		out.print(player.getLoses());
		out.print(",");
		out.print(player.getWinLoseRatio());
		out.println();
	}
	private int generationNumber = 0;
}