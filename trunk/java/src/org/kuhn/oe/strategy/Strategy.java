package org.kuhn.oe.strategy;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public interface Strategy {
	void init(Board board, Color color);
	boolean ratePlay(Color color, int col, int row, Board board);
}
