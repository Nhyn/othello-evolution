import org.kuhn.oe.game.Game;
import org.kuhn.oe.game.PlayExecutor;
import org.kuhn.oe.strategy.Strategy;

public class PerfTest {
	public void test() throws Exception {
		long start = System.currentTimeMillis();
		Strategy[] strategies = new Strategy[] {
//				new CornerStrategy(),
//				new NearCornerStrategy(),
//				new EdgeStrategy(),
//				new NearEdgeStrategy(),
//				new HighestTurnoverStrategy(),
//				new PreventOpponentTurnStrategy(),
//				new DecisiveWinStrategy(),
//				new IncreaseMobilityStrategy(),
//				new DecreaseOpponentMobilityStrategy(),
//				new RandomizationStrategy()
				};
				
		PlayExecutor p0 = new PlayExecutor(strategies);
		PlayExecutor p1 = new PlayExecutor(strategies);
		
		int num = 10000;
		for (int i = 0; i < num; ++i) {
			new Game(p0, p1);
		}
		System.out.println(((double)num / (double)(System.currentTimeMillis() - start)) * 1000);
		System.out.println("games per second");
		//1219 games per second
		//2272 games per second with bitwise board (still using cache)
		//40 games per second without cache
		//~3100 games per second without cache using mutable board!!!
		
	}
	
	public static void main(String[] args) throws Exception {
		new PerfTest().test();
	}
}
