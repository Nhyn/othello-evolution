package org.kuhn.oe.sim.plugable.breeder;

import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.init.Initializer;

public class FreshBloodBreeder implements Breeder {
	private Initializer initializer;
	public FreshBloodBreeder(Initializer initializer) {
		this.initializer = initializer;
	}
	public Player breed(Player player) {
		return initializer.initialize(1).get(0);
	}
}
