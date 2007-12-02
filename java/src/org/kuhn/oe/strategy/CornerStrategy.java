package org.kuhn.oe.strategy;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class CornerStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		return (col == 0 && row == 0)
		|| (col == 0 && row == 7)
		|| (col == 7 && row == 0)
		|| (col == 7 && row == 7);
	}
}