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

public class PlayExecutor {
	public PlayExecutor(Strategy[] strategies) {
		this(strategies, new double[strategies.length]);
	}
	public PlayExecutor(Strategy[] strategies, double[] weights) {
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
		}
		else
			return false;

	}
	
	Strategy[]	strategies;
	double[]	weights;
	
	public Strategy[] getStrategies() {
		return strategies;
	}
	public double[] getWeights() {
		return weights;
	}
	
	public static final PlayExecutor NO_STRATEGY_PLAY_EXECUTOR = new PlayExecutor(new Strategy[0]);
	public static final PlayExecutor ALL_STRATEGY_PLAY_EXECUTOR = new PlayExecutor(new Strategy[] {
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
