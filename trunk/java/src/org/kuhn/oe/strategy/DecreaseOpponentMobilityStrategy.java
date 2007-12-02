package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class DecreaseOpponentMobilityStrategy implements Strategy {
	int before;
	public void init(Board board, Color color) {
		// count opponent's moves before the play
		Color opponent = color.opponent();
		before = 0;
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (board.test(opponent, i, j))
					++before;
	}
	public boolean ratePlay(Color color, int col, int row, Board board) {
		// count opponent's moves after the play
		board.play(color, col, row);
		Color opponent = color.opponent();
		int after = 0;
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				if (board.test(opponent, i, j))
					++after;
		board.undo();
		return after < before;
	}
}
