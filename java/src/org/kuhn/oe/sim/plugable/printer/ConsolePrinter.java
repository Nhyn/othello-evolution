package org.kuhn.oe.sim.plugable.printer;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.kuhn.oe.game.Player;

public class ConsolePrinter implements Printer {
	@Override
	public void printGenerationNumber(int generationNumber) {
		System.out.println("Generation " + generationNumber);
	}

	@Override
	public void printTestResult(List<Player> population) {
		Collections.sort(population, new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				if (o1.getWins() > o2.getWins())
					return -1;
				if (o1.getWins() == o2.getWins()) {
					if (o1.getLoses() < o2.getLoses())
						return -1;
					else if (o1.getLoses() == o2.getLoses()) {
						if (o1.getDraws() < o2.getDraws())
							return -1;
						else if (o1.getDraws() == o2.getDraws())
							return 0;
						else
							return 1;
					} else
						return 1;
				} else
					return 1;
			}
		});
		try {
			population.get(0).print(System.out);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		System.out.println();
	}
}