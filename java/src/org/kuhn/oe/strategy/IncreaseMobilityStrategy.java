package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class IncreaseMobilityStrategy implements Strategy {
	int before;
	public void init(Board board, Color color) {
		before = 0;
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (board.test(color, i, j))
					++before;
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		int after = 0;
		board.play(color, col, row);
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (board.test(color, i, j))
					++after;
		board.undo();
		return after > before;
	}
}
