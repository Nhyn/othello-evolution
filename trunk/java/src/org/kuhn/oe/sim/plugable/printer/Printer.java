package org.kuhn.oe.sim.plugable.printer;

import java.util.List;
import org.kuhn.oe.game.Player;

public interface Printer {
	void printBanner(List<Player> population);
	void printGenerationNumber(int generationNumber);
	void printTestResult(List<Player> population);
}
