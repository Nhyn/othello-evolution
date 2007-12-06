package org.kuhn.oe.sim.plugable.test;

import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.game.Score;

public abstract class AbstractPlayTester implements Tester {
	protected void play(Player p0, Player p1) {
		board.reset();
		game.play(board, p0, p1);
		Score score = board.getScore();
		if (score.getBlack() > score.getWhite()) {
			p0.win();
			p1.lose();
		} else if (score.getBlack() < score.getWhite()) {
			p0.lose();
			p1.win();
		} else {
			p0.draw();
			p1.draw();
		}
	}

	Board			board	= new Board();
	GameExecutor	game	= new GameExecutor();
}
