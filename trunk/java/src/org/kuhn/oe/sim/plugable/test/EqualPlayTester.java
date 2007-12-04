package org.kuhn.oe.sim.plugable.test;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Player;

public class EqualPlayTester extends AbstractPlayTester {
	private int gameCountPerPlayer;
	
	public void setGameCountPerPlayer(int gameCountPerPlayer) {
		this.gameCountPerPlayer = gameCountPerPlayer;
	}
	
	public void test(List<Player> population) {
		for (int i = 0; i < gameCountPerPlayer / 2; ++i) {
			Collections.shuffle(population, random);
			for (int j = 0, k = population.size() - 1; j < population.size(); k = j++) {
				Player p0 = population.get(j);
				Player p1 = population.get(k);
				play(p0, p1);
			}
		}
	}
	
	private static Random random = new Random();
}
