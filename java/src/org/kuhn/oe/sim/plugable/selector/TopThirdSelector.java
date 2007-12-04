package org.kuhn.oe.sim.plugable.selector;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class TopThirdSelector implements Selector {
	public Player select(List<Player> population) {
		Collections.sort(population);
		return population.get(random.nextInt(population.size() / 3));
	}

	Random	random	= new Random();
}
