package org.kuhn.oe.sim.plugable.test;

import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class RandomPlayTester extends AbstractPlayTester {
	private int gameCountPerGeneration;
	
	public void setGameCountPerGeneration(int gameCountPerGeneration) {
		this.gameCountPerGeneration = gameCountPerGeneration;
	}
	
	public void test(List<Player> population) {
		for (int i = 0; i < gameCountPerGeneration; ++i) {
			Player p0 = population.get(random.nextInt(population.size()));
			Player p1 = population.get(random.nextInt(population.size()));
			if (p0 == p1) {
				--i;
				continue;
			}
			play(p0, p1);			
		}
	}
	
	private static Random random = new Random();
}
