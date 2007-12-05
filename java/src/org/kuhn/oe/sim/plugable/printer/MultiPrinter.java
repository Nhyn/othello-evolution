package org.kuhn.oe.sim.plugable.printer;

import java.util.List;
import org.kuhn.oe.game.Player;

public class MultiPrinter implements Printer {
	private Printer[] printers;
	public MultiPrinter(Printer... printers) {
		this.printers = printers;
	}
	public void printSimulationBanner(List<Player> population) {
		for (Printer p : printers)
			p.printSimulationBanner(population);
	}
	public void printGenerationHeader(List<Player> population) {
		for (Printer p : printers)
			p.printGenerationHeader(population);
	}
	public void printTestResult(List<Player> population) {
		for (Printer p : printers)
			p.printTestResult(population);
	}
}