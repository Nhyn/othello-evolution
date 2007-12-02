package org.kuhn.oe.strategy;

import java.util.Random;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class RandomizationStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		return random.nextInt(2) == 0;
	}
	
	private static Random random = new Random();
}
