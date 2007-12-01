package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class IncreaseMobilityStrategy implements Strategy {
	int before;
	public void init(Board board, Color color) {
		before = 0;
		for (int i = 0; i < 64; ++i) {
			if (board.test(color, i)) {
				++before;
			}
		}
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		int after = 0;
		board.play(color, col, row);
		for (int i = 0; i < 64; ++i) {
			if (board.test(color, i)) {
				++after;
			}
		}
		board.undo();
		if (after > before)
			return PlayRating.FAVOR;
		else
			return PlayRating.ABSTAIN;
//		else
//			return PlayRating.DISFAVOR;
	}
}
