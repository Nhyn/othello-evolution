package org.kuhn.oe.strategy;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;

public class CaptureLastTurnStrategy extends PreventOpponentTurnStrategy {
	@Override
	public boolean ratePlay(Color color, int col, int row, Board board) {
		// if there are an even number of blanks on the board, try to prevent opponent's next turn,
		// as this will put this player in position to have the last turn
		if (board.getScore().getNone() % 2 == 0)
			return super.ratePlay(color, col, row, board);
		else return false;
	}
}
