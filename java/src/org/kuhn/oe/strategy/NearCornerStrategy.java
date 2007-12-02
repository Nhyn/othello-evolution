package org.kuhn.oe.strategy;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class NearCornerStrategy implements Strategy {
	public void init(Board board, Color color) {
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		return (col == 0 && row == 1) 
		|| (col == 1 && row == 1) 
		|| (col == 1 && row == 0)
		|| (col == 6 && row == 0) 
		|| (col == 6 && row == 1)
		|| (col == 7 && row == 1) 
		|| (col == 0 && row == 6)
		|| (col == 1 && row == 6) 
		|| (col == 1 && row == 7)
		|| (col == 6 && row == 7)
		|| (col == 6 && row == 6)
		|| (col == 7 && row == 6);
	}
}