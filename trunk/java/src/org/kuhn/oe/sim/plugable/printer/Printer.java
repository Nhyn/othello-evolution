package org.kuhn.oe.sim.plugable.printer;

import java.util.List;
import org.kuhn.oe.game.Player;

public interface Printer {
	void printSimulationBanner(List<Player> population);
	void printGenerationHeader(List<Player> population);
	void printTestResult(List<Player> population);
}
