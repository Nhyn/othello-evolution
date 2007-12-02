package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class EdgeStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		return ((row == 0 || row == 7) && col >= 2 && col <= 5)
		|| ((col == 0 || col == 7) && row >= 2 && row <= 5);
	}
}
