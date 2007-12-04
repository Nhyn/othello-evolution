package org.kuhn.oe.sim.plugable.init;

import java.util.ArrayList;
import java.util.List;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.sim.plugable.breeder.Breeder;

public class PrototypePlayerInitializer implements Initializer {
	private Player	prototypePlayer;
	private Breeder	breeder;

	public void setPrototypePlayer(Player prototypePlayer) {
		this.prototypePlayer = prototypePlayer;
	}
	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}

	public List<Player> initialize(int populationSize) {
		assert prototypePlayer != null;
		assert breeder != null;

		List<Player> population = new ArrayList<Player>(populationSize);
		for (int i = 0; i < populationSize; ++i)
			population.add(breeder.breed(prototypePlayer));
		return population;
	}
}
