package org.kuhn.oe.game;


public class Game {
	public Game(PlayExecutor black, PlayExecutor white) {
		Board board = new Board();
		Color color = Color.BLACK;
		boolean pass = false;
		int round = 0;
		while (board.getScore().getNone() > 0) {
//			System.out.println("ROUND " + ++round);
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
			
//			System.out.println(board);
			color = color.opponent();
		}
		endBoard = board;
	}
	
	public Board getEndBoard() {
		return endBoard;
	}
	
	private Board endBoard;
}
