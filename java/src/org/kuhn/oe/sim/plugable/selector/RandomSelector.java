package org.kuhn.oe.sim.plugable.selector;

import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class RandomSelector implements Selector {
	public Player select(List<Player> population) {
		return population.get(random.nextInt(population.size()));
	}
	
	private Random random = new Random();
}
