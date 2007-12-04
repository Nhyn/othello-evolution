package org.kuhn.oe.sim.plugable.printer;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.kuhn.oe.game.Player;

public class ConsolePrinter implements Printer {
	@Override
	public void printGenerationNumber(int generationNumber) {
		System.out.println("Generation " + generationNumber);
	}

	@Override
	public void printTestResult(List<Player> population) {
		Collections.sort(population);
		try {
			population.get(0).print(System.out);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		System.out.println();
	}
}