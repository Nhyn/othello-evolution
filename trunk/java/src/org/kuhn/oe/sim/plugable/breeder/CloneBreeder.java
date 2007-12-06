package org.kuhn.oe.sim.plugable.breeder;

import org.kuhn.oe.game.Player;

public class CloneBreeder implements Breeder {
	public Player breed(Player player) {
		return new Player(player.getStrategies(), player.getWeights());
	}
}
