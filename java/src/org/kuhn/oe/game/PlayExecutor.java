package org.kuhn.oe.game;
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
		Integer highScoreIndex = null;

		for (Strategy s : strategies)
			s.init(board, color);
		
		for (int i = 0; i < 64; ++i) {
			if (board.play(color, i))
				return true;
			
//			if (board.test(color, i)) {
//				double score = 0;
//				for (int j = 0; j < strategies.length; ++j) {
//					Strategy s = strategies[j];
//					PlayRating rating = s.ratePlay(color, i % 8, i / 8, board);
//					if (rating == PlayRating.FAVOR) {
//						score += weights[j];
//					}
//				}
//				if (highScoreIndex == null || score > highScore) {
//					highScore = score;
//					highScoreIndex = i;
//				}
//			}
		}
//		if (highScoreIndex != null) {
//			board.play(color, highScoreIndex);
//			return true;
//		}
//		else
			return false;

	}
	
	Strategy[]	strategies;
	double[]	weights;
	
	public Strategy[] getStrategies() {
		return strategies.clone();
	}
	public double[] getWeights() {
		return weights.clone();
	}
	
	public static final PlayExecutor NO_STRATEGY_PLAY_EXECUTOR = new PlayExecutor(new Strategy[0]);
}
