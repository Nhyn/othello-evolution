package org.kuhn.oe.breeder;


import java.util.Random;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;

public class SingleMutationBreeder implements Breeder {
	public Player breed(Player player) {
		double[] weights = player.getPlayExecutor().getWeights();
		int i = rand.nextInt(weights.length);
		double weight = weights[i];
		weight += rand.nextDouble() - 0.5d;
		weights[i] = weight;
		return new Player(new PlayExecutor(player.getPlayExecutor().getStrategies(), weights));
	}
	
	private static final Random rand = new Random();
}
