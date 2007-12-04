package org.kuhn.oe.sim.plugable.selector;

import java.util.List;

import org.kuhn.oe.game.Player;

public interface Selector {
	Player select(List<Player> population);
}
