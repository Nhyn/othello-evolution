package org.kuhn.oe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.kuhn.oe.breeder.Breeder;
import org.kuhn.oe.breeder.CloneBreeder;
import org.kuhn.oe.breeder.SingleMutationBreeder;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Game;
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
	
	public void game() {
		
		Breeder cBreeder = new CloneBreeder();
		Breeder smBreeder = new SingleMutationBreeder();
		
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
			players.add(cBreeder.breed(adam));
		}
		
		
		int b = 0, w = 0, d = 0;
		
		Random random = new Random();
		
		for (int j = 1; j <= 100; ++j) {
//			System.out.println("ROUND " + j + " with " + players.size() + " players");
			
			List<Player> babies = new ArrayList<Player>();
			
			for (int i = 0; i < players.size(); ++i) {
				for (int ii = 0; ii < 6; ++ii ) {
					Player p0 = players.get(i);
					Player p1 = players.get(random.nextInt(players.size()));
					if (p0 == p1) continue;
					
					Game game = new Game(p0.getPlayExecutor(), p1.getPlayExecutor());
					Board board = game.getEndBoard();
					
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
			System.out.println(players.get(0));
			
			for (int i = 0; i < players.size() / 3; ++i) {
				babies.add(cBreeder.breed(players.get(i)));
				babies.add(cBreeder.breed(players.get(i)));
				babies.add(smBreeder.breed(players.get(i)));
			}
			
			players = babies;
			
		}
		
		
		
		System.out.println(String.format("black: %d, white: %d, draw: %d", b, w, d));
		

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
	
	public static void main(String[] args) {
		new Driver().game();
	}
}
