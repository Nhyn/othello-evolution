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
		for (Strategy s : population.get(0).getPlayExecutor().getStrategies()) {
			out.print(s.getClass().getSimpleName());
			out.print(",");
		}
		out.println();
	}
	public void printGenerationHeader(List<Player> population) {
	}
	public void printTestResult(List<Player> population) {
		Collections.sort(population);
		out.print(population.get(0).toString());
		out.println();
	}
}