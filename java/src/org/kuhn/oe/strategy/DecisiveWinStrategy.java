package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class DecisiveWinStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		board.play(color, col, row);
		boolean result = board.getScore().get(color.opponent()) == 0;
		board.undo();
		return result;
	}
}
