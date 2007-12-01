package org.kuhn.oe.breeder;

import org.kuhn.oe.game.Player;


public class CloneBreeder implements Breeder {
	public Player breed(Player player) {
		return new Player(player.getPlayExecutor());
	}
}
