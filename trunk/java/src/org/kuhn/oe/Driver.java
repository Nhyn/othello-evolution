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
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.game.Player;
import org.kuhn.oe.game.Score;
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
		
		Breeder cloneBreeder = new CloneBreeder();
		Breeder singleMutationBreeder = new RegularMutationBreeder(new SingleMutationBreeder(), 10);
		
		Strategy[] strategies = new Strategy[] {
		new CornerStrategy(),
		new NearCornerStrategy(),
		new EdgeStrategy(),
		new NearEdgeStrategy(),
		new HighestTurnoverStrategy(),
		new PreventOpponentTurnStrategy(),
		new DecisiveWinStrategy(),
		new IncreaseMobilityStrategy(),
		new DecreaseOpponentMobilityStrategy(),
		new RandomizationStrategy()
		};
		for (Strategy s : strategies)
			System.out.print(s.getClass().getSimpleName() + " ");
		System.out.println();
		
		Player adam = new Player(new PlayExecutor(strategies));
		
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < 12; ++i) {
			players.add(cloneBreeder.breed(adam));
		}
		
		
		int b = 0, w = 0, d = 0;
		
		Random random = new Random();
		
		long start = System.currentTimeMillis();
		GameExecutor game = new GameExecutor();
		Board board = new Board();
		
		for (int j = 1; j <= 1000; ++j) {
			List<Player> babies = new ArrayList<Player>();
			
			for (int i = 0; i < players.size(); ++i) {
				for (int ii = 0; ii < 6; ++ii ) {
					Player p0 = players.get(i);
					Player p1 = players.get(random.nextInt(players.size()));
					if (p0 == p1) continue;
					
					game.play(board, p0.getPlayExecutor(), p1.getPlayExecutor());
					
					Score score = board.getScore();
					if (score.getBlack() > score.getWhite()) {
						p0.win();
						p1.lose();
						++b;
					} else if (score.getBlack() < score.getWhite()) {
						p0.lose();
						p1.win();
						++w;
					} else {
						++d;
					}
				}
				
			}
			
			sort(players);
			//System.out.println(players.get(0));
			players.get(0).print(System.out);
			System.out.println();
			
			for (int i = 0; i < players.size() / 3; ++i) {
				babies.add(singleMutationBreeder.breed(players.get(i)));
				babies.add(singleMutationBreeder.breed(players.get(i)));
				babies.add(singleMutationBreeder.breed(players.get(i)));
			}
			
			players = babies;
			
		}
		
		
		
		System.out.println(String.format("black: %d, white: %d, draw: %d", b, w, d));
		long dur = System.currentTimeMillis() - start;
		System.out.println(game.getCount() + " games in " + dur + " ms (" + (float)game.getCount() / ((float)dur / 1000.0) + " games per second)");

	}
	
	private void sort(List<Player> list) {
		Collections.sort(list, new Comparator<Player>() {
			public int compare(Player o1, Player o2) {
				if (o1.getWins() > o2.getWins())
					return -1;
				if (o1.getWins() == o2.getWins()) {
					if (o1.getLoses() < o2.getLoses())
						return -1;
					else if (o1.getLoses() == o2.getLoses())
						return 0;
					else
						return 1;
				} else
					return 1;
			}
		});
	}
}
