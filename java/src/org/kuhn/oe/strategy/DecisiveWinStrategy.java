package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class DecisiveWinStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		board.play(color, col, row);
		PlayRating result = board.getScore().get(color.opponent()) == 0 ? PlayRating.FAVOR : PlayRating.ABSTAIN;
		board.undo();
		return result;
		// TODO instead, check that opponent has no valid moves and hasn't won
	}
}
