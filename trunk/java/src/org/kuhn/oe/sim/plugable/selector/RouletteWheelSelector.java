package org.kuhn.oe.sim.plugable.selector;

import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class RouletteWheelSelector implements Selector {
	public Player select(List<Player> population) {
		double totalFitness = 0.0d;
		for (Player player : population)
			totalFitness += player.getFitness();
		double targetFitness = random.nextDouble() * totalFitness;
		for (Player player : population) {
			targetFitness -= player.getFitness();
			if (targetFitness <= 0.0D)
				return player;
		}
		throw new RuntimeException();
	}
	
	private Random random = new Random();
}
