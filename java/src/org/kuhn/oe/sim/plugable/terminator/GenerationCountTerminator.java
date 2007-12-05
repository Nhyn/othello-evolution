package org.kuhn.oe.sim.plugable.terminator;

import java.util.List;
import org.kuhn.oe.game.Player;

public class GenerationCountTerminator implements Terminator {
	private int maxGenerationCount;
	private int generationCount = 0;
	public GenerationCountTerminator(int maxGenerationCount) {
		this.maxGenerationCount = maxGenerationCount;
	}
	public boolean terminate(List<Player> population) {
		return ++generationCount > maxGenerationCount;
	}
}
