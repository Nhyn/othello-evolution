package org.kuhn.oe.sim.plugable.terminator;

import java.util.List;
import org.kuhn.oe.game.Player;

public interface Terminator {
	boolean terminate(List<Player> population);
}
