package org.kuhn.oe.sim.plugable.breeder;


import java.util.Random;

import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.breeder.mutator.Mutator;

public class SingleMutationBreeder implements Breeder {
	private Mutator mutator;
	public SingleMutationBreeder(Mutator mutator) {
		this.mutator = mutator;
	}
	public Player breed(Player player) {
		double[] weights = player.getWeights();
		double[] newWeights = weights.clone();
		if (newWeights.length != 0) {
			int i = random.nextInt(newWeights.length);
			newWeights[i] = mutator.mutate(newWeights[i]);
		}
		return new Player(player.getStrategies(), newWeights);
	}
	private static final Random random = new Random();
}