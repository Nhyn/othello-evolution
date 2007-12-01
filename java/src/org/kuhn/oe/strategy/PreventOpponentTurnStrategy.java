package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class PreventOpponentTurnStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		PlayRating result = PlayRating.FAVOR;
		board.play(color, col, row);
		Color opponent = color.opponent();
		for (int i = 0; i < 64; ++i) {
			if (board.test(opponent, i)) {
				result = PlayRating.ABSTAIN;
				break;
			}
		}
		board.undo();
		return result;
	}
}