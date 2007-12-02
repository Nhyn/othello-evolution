package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class NearEdgeStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		return ((row == 1 || row == 6) && col >= 2 && col <= 5)
		|| ((col == 1 || col == 6) && row >= 2 && row <= 5);
	}
}