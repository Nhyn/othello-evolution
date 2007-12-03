package org.kuhn.oe.breeder;

import org.kuhn.oe.breeder.mutator.Mutator;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;

public class MassMutationBreeder implements Breeder {
	private Mutator mutator;
	public MassMutationBreeder(Mutator mutator) {
		this.mutator = mutator;
	}
	public Player breed(Player player) {
		double[] weights = player.getPlayExecutor().getWeights();
		double[] newWeights = new double[weights.length];
		for (int i = 0; i < weights.length; ++i) {
			newWeights[i] = mutator.mutate(weights[i]);
		}
		return new Player(new PlayExecutor(player.getPlayExecutor().getStrategies(), newWeights));
	}
}
