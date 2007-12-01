package org.kuhn.oe.strategy;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class NearCornerStrategy implements Strategy {
	public void init(Board board, Color color) {
		// TODO Auto-generated method stub
		
	}
	public PlayRating ratePlay(Color color, int col, int row, Board board) {
		if ((col == 0 && row == 1) 
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
		|| (col == 7 && row == 6)) {
			return PlayRating.FAVOR;
		} else {
			return PlayRating.ABSTAIN;
		}
	}
}