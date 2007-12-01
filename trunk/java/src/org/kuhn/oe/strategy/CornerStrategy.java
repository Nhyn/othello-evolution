package org.kuhn.oe.strategy;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class CornerStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		if ((col == 0 && row == 0)
		|| (col == 0 && row == 7)
		|| (col == 7 && row == 0)
		|| (col == 7 && row == 7)) {
			return PlayRating.FAVOR;
		} else {
			return PlayRating.ABSTAIN;
		}
	}
}