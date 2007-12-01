package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class NearEdgeStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		if (((row == 1 || row == 6) && col >= 2 && col <= 5)
		|| ((col == 1 || col == 6) && row >= 2 && row <= 5)) {
			return PlayRating.FAVOR;
		} else {
			return PlayRating.ABSTAIN;
		}
	}
}