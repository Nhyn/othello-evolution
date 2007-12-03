package org.kuhn.oe.breeder;


import java.util.Random;

import org.kuhn.oe.breeder.mutator.Mutator;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;

public class SingleMutationBreeder implements Breeder {
	private Mutator mutator;
	public SingleMutationBreeder(Mutator mutator) {
		this.mutator = mutator;
	}
	public Player breed(Player player) {
		double[] weights = player.getPlayExecutor().getWeights();
		double[] newWeights = weights.clone();
		if (newWeights.length != 0) {
			int i = rand.nextInt(newWeights.length);
			newWeights[i] = mutator.mutate(newWeights[i]);
		}
		return new Player(new PlayExecutor(player.getPlayExecutor().getStrategies(), newWeights));
	}
	
	private static final Random rand = new Random();
}