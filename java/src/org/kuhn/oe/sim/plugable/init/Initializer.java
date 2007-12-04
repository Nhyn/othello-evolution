package org.kuhn.oe.sim.plugable.init;

import java.util.List;

import org.kuhn.oe.game.Player;

public interface Initializer {
	List<Player> initialize(int populationSize);
}
