package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class HighestTurnoverStrategy implements Strategy {
	int highScore;
	public void init(Board board, Color color) {
		highScore = board.getScore().get(color);
		for (int i = 0; i < 64; ++i) {
			if (board.play(color, i)) {
				int score = board.getScore().get(color);
				if (score > highScore) {
					highScore = score;
				}
				board.undo();
			}
		}
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		board.play(color, col, row);
		int score = board.getScore().get(color);
		board.undo();
		return score == highScore ? PlayRating.FAVOR : PlayRating.ABSTAIN;
	}
}
