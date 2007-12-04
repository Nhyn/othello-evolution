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
	public void printBanner(List<Player> population) {
	}
	public void printGenerationNumber(int generationNumber) {
		out.println("Generation " + generationNumber);
	}
	public void printTestResult(List<Player> population) {
		Collections.sort(population);
		population.get(0).print(out);
		out.println();
	}
}