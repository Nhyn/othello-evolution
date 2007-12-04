package org.kuhn.oe.sim.plugable.selector;

import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class TopThirdSelector implements Selector {
	@Override
	public Player select(List<Player> population) {
		return population.get(random.nextInt(population.size() / 3));
	}

	Random	random	= new Random();
}
