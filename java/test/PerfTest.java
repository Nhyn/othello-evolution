import junit.framework.TestCase;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.Player;

public class PerfTest extends TestCase {
	public void testAllStrategyPerformanceTest() {
		long start = System.currentTimeMillis();
		int num = 100;
		GameExecutor game = new GameExecutor();
		Board board = new Board();
		for (int i = 0; i < num; ++i) {
			game.play(board, Player.ALL_STRATEGY_PLAYER, Player.ALL_STRATEGY_PLAYER);
		}
		System.out.print("all strategies: ");
		System.out.print(((double)num / (double)(System.currentTimeMillis() - start)) * 1000);
		System.out.println(" games per second");
		//1219 games per second
		//2272 games per second with bitwise board (still using cache)
		//40 games per second without cache
		//~3100 games per second without cache using mutable board!!!
		
		// 1146 new baseline
		// 855 play refactor into vector
		// 946 with fewer getcolor calls
		// 1155 with optimized valid calculation
	}
	public void testNoStrategyPerformanceTest() {
		long start = System.currentTimeMillis();
		int num = 1000;
		GameExecutor game = new GameExecutor();
		Board board = new Board();
		for (int i = 0; i < num; ++i) {
			game.play(board, Player.NO_STRATEGY_PLAYER, Player.NO_STRATEGY_PLAYER);
		}
		System.out.print("no strategies: ");
		System.out.print(((double)num / (double)(System.currentTimeMillis() - start)) * 1000);
		System.out.println(" games per second");
		//1219 games per second
		//2272 games per second with bitwise board (still using cache)
		//40 games per second without cache
		//~3100 games per second without cache using mutable board!!!
		
		// 1146 new baseline
		// 855 play refactor into vector
		// 946 with fewer getcolor calls
		// 1155 with optimized valid calculation
	}
//	public void test() throws Exception {
//		
//		Strategy[] strategies = new Strategy[] {
////				new CornerStrategy(),
////				new NearCornerStrategy(),
////				new EdgeStrategy(),
////				new NearEdgeStrategy(),
////				new HighestTurnoverStrategy(),
////				new PreventOpponentTurnStrategy(),
////				new DecisiveWinStrategy(),
////				new IncreaseMobilityStrategy(),
////				new DecreaseOpponentMobilityStrategy(),
////				new RandomizationStrategy()
//				};
//				
//		PlayExecutor p0 = new PlayExecutor(strategies);
//		PlayExecutor p1 = new PlayExecutor(strategies);
//		

//		
//	}
//	
//	public static void main(String[] args) throws Exception {
//		new PerfTest().test();
//	}
}
