package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class PreventOpponentTurnStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		boolean result = true;
		board.play(color, col, row);
		Color opponent = color.opponent();
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (board.test(opponent, i, j)) {
					result = false;
					break;
				}
		board.undo();
		return result;
	}
}