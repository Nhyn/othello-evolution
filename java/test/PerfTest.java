import junit.framework.TestCase;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.GameExecutor;
import org.kuhn.oe.game.PlayExecutor;

public class PerfTest extends TestCase {
	public void testNoStrategyPerformanceTest() {
		long start = System.currentTimeMillis();
		int num = 10000;
		GameExecutor game = new GameExecutor();
		Board board = new Board();
		for (int i = 0; i < num; ++i) {
			game.play(board, PlayExecutor.NO_STRATEGY_PLAY_EXECUTOR, PlayExecutor.NO_STRATEGY_PLAY_EXECUTOR);
		}
		System.out.println(((double)num / (double)(System.currentTimeMillis() - start)) * 1000);
		System.out.println("games per second");
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
