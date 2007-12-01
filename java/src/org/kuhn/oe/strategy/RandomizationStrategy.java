package org.kuhn.oe.strategy;

import java.util.Random;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class RandomizationStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		return random.nextInt(2) == 0 ? PlayRating.FAVOR : PlayRating.ABSTAIN;
	}
	
	private static Random random = new Random();
}
