package org.kuhn.oe.sim.plugable.breeder;

import org.kuhn.oe.game.Player;

public class RegularMutationBreeder implements Breeder {
	private Breeder	breeder;
	private int		frequency;
	private int		counter	= 0;
	
	public RegularMutationBreeder(Breeder breeder, int frequency) {
		this.breeder = breeder;
		this.frequency = frequency;
	}
	
	public Player breed(Player player) {
		if (++counter % frequency == 0)
			return breeder.breed(player);
		else
			return cloneBreeder.breed(player);
	}

	private CloneBreeder	cloneBreeder	= new CloneBreeder();
}