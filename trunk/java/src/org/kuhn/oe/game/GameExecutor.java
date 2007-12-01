package org.kuhn.oe.game;


public class GameExecutor {
	public void play(Board board, PlayExecutor black, PlayExecutor white) {
		board.reset();
		Color color = Color.BLACK;
		boolean pass = false;
		int round = 0;
		while (board.getScore().getNone() > 0) {
			boolean playResult;
			if (color == Color.BLACK)
				playResult = black.play(board, color);
			else
				playResult = white.play(board, color);
			
			if (playResult) {
				pass = false;
			} else {
				if (pass == true)
					break;
				pass = true;
			}
			
			color = color.opponent();
		}
	}
}
