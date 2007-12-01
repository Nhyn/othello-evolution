package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class EdgeStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		if (((row == 0 || row == 7) && col >= 2 && col <= 5)
		|| ((col == 0 || col == 7) && row >= 2 && row <= 5)) {
			return PlayRating.FAVOR;
		} else {
			return PlayRating.ABSTAIN;
		}
	}
}
