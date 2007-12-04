package org.kuhn.oe.sim.plugable.test;

import java.util.List;
import java.util.Random;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.game.Score;

public class SimpleTester implements Tester {
	private int gameCountPerGeneration;
	
	public void setGameCountPerGeneration(int gameCountPerGeneration) {
		this.gameCountPerGeneration = gameCountPerGeneration;
	}
	
	@Override
	public void test(List<Player> population) {
		Board board = new Board();
		GameExecutor game = new GameExecutor();
		
		for (int i = 0; i < gameCountPerGeneration; ++i) {
			Player p0 = population.get(random.nextInt(population.size()));
			Player p1 = population.get(random.nextInt(population.size()));
			if (p0 == p1) {
				--i;
				continue;
			}
			
			game.play(board, p0.getPlayExecutor(), p1.getPlayExecutor());
			Score score = board.getScore();
			if (score.getBlack() > score.getWhite()) {
				p0.win();
				p1.lose();
			} else if (score.getBlack() < score.getWhite()) {
				p0.lose();
				p1.win();
			} else {
				p0.draw();
				p1.draw();
			}
		}
	}
	
	private static Random random = new Random();
}
