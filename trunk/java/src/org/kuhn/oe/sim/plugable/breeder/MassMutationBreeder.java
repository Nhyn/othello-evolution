package org.kuhn.oe.sim.plugable.breeder;

import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.breeder.mutator.Mutator;

public class MassMutationBreeder implements Breeder {
	private Mutator	mutator;
	public MassMutationBreeder(Mutator mutator) {
		this.mutator = mutator;
	}
	public Player breed(Player player) {
		double[] weights = player.getWeights();
		double[] newWeights = new double[weights.length];
		for (int i = 0; i < weights.length; ++i) {
			newWeights[i] = mutator.mutate(weights[i]);
		}
		return new Player(player.getStrategies(), newWeights);
	}
}
