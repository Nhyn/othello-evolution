package org.kuhn.oe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.kuhn.oe.breeder.Breeder;
import org.kuhn.oe.breeder.CloneBreeder;
import org.kuhn.oe.breeder.RegularMutationBreeder;
import org.kuhn.oe.breeder.SingleMutationBreeder;
import org.kuhn.oe.breeder.mutator.SimpleMutator;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.game.Score;
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


public class Driver {
	
	public static void main(String[] args) throws Exception {
		new Driver().game();
	}
	
	public void game() throws Exception {
		// initialization
		Strategy[] strategies = new Strategy[] {
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
		};
//		for (Strategy s : strategies)
//			System.out.print(s.getClass().getSimpleName() + " ");
//		System.out.println();
		Player adam = new Player(new PlayExecutor(strategies));
		List<Player> population = new ArrayList<Player>();
		Breeder cloneBreeder = new CloneBreeder();
		for (int i = 0; i < 100; ++i) {
			population.add(cloneBreeder.breed(adam));
		}
		
		Random random = new Random();
		
		GameExecutor game = new GameExecutor();
		Board board = new Board();
		
		for (int gen = 0; gen < 10000; ++gen) {
			long startMs = System.currentTimeMillis();
			int startGameCount = game.getCount();
			
			List<Player> offspring = new ArrayList<Player>();
			
			// measure fitness
			for (int i = 0; i < population.size(); ++i) {
				for (int ii = 0; ii < 100; ++ii ) {
					Player p0 = population.get(i);
					Player p1 = population.get(random.nextInt(population.size()));
					if (p0 == p1) {
						--ii;
						continue;
					}
					
					game.play(board, p0.getPlayExecutor(), p1.getPlayExecutor());
					
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
				
			}
			
			System.out.println("Generation: " + gen);
			long dur = System.currentTimeMillis() - startMs;
			int games = game.getCount() - startGameCount;
			System.out.println(String.format("%d games in %d ms (%1.2f games per second)", games, dur, (float)games / ((float)dur / 1000.0)));
			System.out.println();
			
			//System.out.println(players.get(0));
			sort(population);
			population.get(0).print(System.out);
			System.out.println();
			
			// selection and reproduction
			Breeder regularMutationBreeder = new RegularMutationBreeder(new SingleMutationBreeder(new SimpleMutator()), 5);
			for (int i = 0; i < population.size() / 3; ++i) {
				offspring.add(regularMutationBreeder.breed(population.get(i)));
				offspring.add(regularMutationBreeder.breed(population.get(i)));
				offspring.add(regularMutationBreeder.breed(population.get(random.nextInt(population.size()))));
			}
			
			population = offspring;
			
		}
	}
	
	private void sort(List<Player> list) {
		Collections.sort(list, new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				if (o1.getWins() > o2.getWins())
					return -1;
				if (o1.getWins() == o2.getWins()) {
					if (o1.getLoses() < o2.getLoses())
						return -1;
					else if (o1.getLoses() == o2.getLoses()) {
						if (o1.getDraws() < o2.getDraws())
							return -1;
						else if (o1.getDraws() == o2.getDraws())
							return 0;
						else
							return 1;
					}
					else
						return 1;
				} else
					return 1;
			}
		});
	}
}
