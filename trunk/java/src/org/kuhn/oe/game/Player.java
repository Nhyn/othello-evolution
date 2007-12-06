package org.kuhn.oe.game;

import org.kuhn.oe.strategy.CaptureLastTurnStrategy;
import org.kuhn.oe.strategy.CornerStrategy;
import org.kuhn.oe.strategy.DecisiveWinStrategy;
import org.kuhn.oe.strategy.DecreaseOpponentMobilityStrategy;
import org.kuhn.oe.strategy.EdgeStrategy;
import org.kuhn.oe.strategy.HighestTurnoverStrategy;
import org.kuhn.oe.strategy.IncreaseMobilityStrategy;
import org.kuhn.oe.strategy.NearCornerStrategy;
import org.kuhn.oe.strategy.NearEdgeStrategy;
import org.kuhn.oe.strategy.PreventOpponentTurnStrategy;
import org.kuhn.oe.strategy.RandomizationStrategy;
import org.kuhn.oe.strategy.Strategy;

public class Player implements Comparable<Player> {
	public Player(Strategy[] strategies) {
		this(strategies, new double[strategies.length]);
	}
	public Player(Strategy[] strategies, double[] weights) {
		this.strategies = strategies;
		this.weights = weights;
		assert strategies.length == weights.length;
	}

	public boolean play(Board board, Color color) {
		double highScore = Integer.MIN_VALUE;
		Integer highScoreCol = null;
		Integer highScoreRow = null;
		for (Strategy s : strategies)
			s.init(board, color);
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (board.test(color, i, j)) {
					double score = 0;
					for (int k = 0; k < strategies.length; ++k) {
						Strategy s = strategies[k];
						if (s.ratePlay(color, i, j, board)) {
							score += weights[k];
						}
					}
					if (score > highScore || highScoreCol == null) {
						highScore = score;
						highScoreCol = i;
						highScoreRow = j;
					}
				}
			}
		}
		if (highScoreCol != null) {
			board.play(color, highScoreCol, highScoreRow);
			return true;
		} else
			return false;

	}

	public Strategy[] getStrategies() {
		return strategies;
	}
	public double[] getWeights() {
		return weights;
	}
	public void win() {
		++wins;
	}
	public int getWins() {
		return wins;
	}
	public void lose() {
		++loses;
	}
	public int getLoses() {
		return loses;
	}
	public double getWinLoseRatio() {
		return (double) wins / (double) loses;
	}
	public void draw() {
		++draws;
	}
	public int getDraws() {
		return draws;
	}
	public double getFitness() {
		return wins;
	}

	public int compareTo(Player o) {
		double f0 = getFitness();
		double f1 = o.getFitness();
		if (f0 < f1)
			return 1;
		else if (f0 == f1)
			return 0;
		else
			return -1;
	}

	private Strategy[]	strategies;
	private double[]	weights;
	int					wins	= 0;
	int					loses	= 0;
	int					draws	= 0;

	public static final Player	NO_STRATEGY_PLAYER	= new Player(new Strategy[0]);

	public static final Player	ALL_STRATEGY_PLAYER	= new Player(new Strategy[] {
	new CornerStrategy(), 
	new NearCornerStrategy(), 
	new EdgeStrategy(), 
	new NearEdgeStrategy(),
	new HighestTurnoverStrategy(), 
	new PreventOpponentTurnStrategy(), 
	new CaptureLastTurnStrategy(),
	new DecisiveWinStrategy(), 
	new IncreaseMobilityStrategy(), 
	new DecreaseOpponentMobilityStrategy(),
	new RandomizationStrategy()
	});
}
