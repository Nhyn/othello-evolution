package org.kuhn.oe.sim.plugable.breeder;

import org.kuhn.oe.game.Player;

public class RoundRobinBreeder implements Breeder {
	private int index = 0;
	private Breeder[] breeders;
	public RoundRobinBreeder(Breeder... breeders) {
		this.breeders = breeders;
	}
	public Player breed(Player player) {
		return breeders[index++ % breeders.length].breed(player);
	}
}