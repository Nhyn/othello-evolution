
import junit.framework.TestCase;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;


public class BoardTest extends TestCase {
	public void test() {
		Board b = new Board();
		System.out.print(b);
		b.play(Color.WHITE, 3, 5);
		System.out.print(b);
		b.play(Color.BLACK, 26);
		System.out.print(b);
		b.play(Color.WHITE, 1, 2);
		System.out.print(b);
		b.play(Color.BLACK, 3, 6);
		System.out.print(b);
	}
	
//	public void test2() {
//		for (int i = 0; i < 8; ++i) {
//			for (int j = 0; j < 8; ++j) {
//				System.out.println(i + ", " + j);
//				Board b = new Board();
//				b.setColor(i, j, Color.BLACK);
//				assertEquals(Color.BLACK, b.getColor(i, j));
//				b.setColor(i, j, Color.WHITE);
//				assertEquals(Color.WHITE, b.getColor(i, j));
//				b.setColor(i, j, Color.NONE);
//				assertEquals(Color.NONE, b.getColor(i, j));
//			}
//		}
//	}
}
