package org.kuhn.oe.game;


public class GameExecutor {
	public void play(Board board, Player black, Player white) {
		board.reset();
		Color color = Color.BLACK;
		boolean pass = false;
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
