package org.kuhn.oe.sim.plugable.printer;

import java.util.List;
import org.kuhn.oe.game.Player;

public class MultiPrinter implements Printer {
	private Printer[] printers;
	public MultiPrinter(Printer... printers) {
		this.printers = printers;
	}
	public void printBanner(List<Player> population) {
		for (Printer p : printers)
			p.printBanner(population);
	}
	public void printGenerationNumber(int generationNumber) {
		for (Printer p : printers)
			p.printGenerationNumber(generationNumber);
	}
	public void printTestResult(List<Player> population) {
		for (Printer p : printers)
			p.printTestResult(population);
	}
}