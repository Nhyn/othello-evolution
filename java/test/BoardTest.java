
import junit.framework.TestCase;
import org.kuhn.oe.game.Board;
import org.kuhn.oe.game.Color;


public class BoardTest extends TestCase {
	public void test() throws Exception {
		Board b = new Board();
		b.print(System.out);
		assertEquals("0:---------------------------WB------BW---------------------------2/2/60", b.toString());
		
		assertTrue(b.play(Color.BLACK, 3, 2)); // black north
		b.print(System.out);
		assertEquals("1:-------------------B-------BB------BW---------------------------4/1/59", b.toString());
		
		assertTrue(b.play(Color.WHITE, 2, 4)); // white west
		b.print(System.out);
		assertEquals("2:-------------------B-------BB-----WWW---------------------------3/3/58", b.toString());

		assertTrue(b.play(Color.BLACK, 1, 5)); // black southwest
		b.print(System.out);
		assertEquals("3:-------------------B-------BB-----BWW----B----------------------5/2/57", b.toString());

		assertTrue(b.play(Color.WHITE, 2, 2)); // white northwest
		b.print(System.out);
		assertEquals("4:------------------WB-------WB-----BWW----B----------------------4/4/56", b.toString());
		
		assertTrue(b.play(Color.BLACK, 4, 5)); // black south
		b.print(System.out);
		assertEquals("5:------------------WB-------WB-----BWB----B--B-------------------6/3/55", b.toString());

		assertTrue(b.play(Color.WHITE, 5, 4)); // white east
		b.print(System.out);
		assertEquals("6:------------------WB-------WB-----BWWW---B--B-------------------5/5/54", b.toString());
		
		assertTrue(b.play(Color.BLACK, 6, 3)); // black northeast
		b.print(System.out);
		assertEquals("7:------------------WB-------WB-B---BWWB---B--B-------------------7/4/53", b.toString());
		
		assertTrue(b.play(Color.WHITE, 0, 6)); // white southeast
		b.print(System.out);
		assertEquals("8:------------------WB-------WB-B---WWWB---W--B---W---------------5/7/52", b.toString());
		
		assertFalse(b.play(Color.BLACK, 0, 0)); // invalid
		assertFalse(b.play(Color.BLACK, 4, 2)); // invalid
		assertFalse(b.play(Color.BLACK, 6, 4)); // invalid
		
		b.undo(); // undo
		assertEquals("7:------------------WB-------WB-B---BWWB---B--B-------------------7/4/53", b.toString());
		
		assertFalse(b.test(Color.BLACK, 0, 0)); // false test
		assertEquals("7:------------------WB-------WB-B---BWWB---B--B-------------------7/4/53", b.toString());
		
		assertTrue(b.test(Color.WHITE, 0, 6)); // true test
		assertEquals("7:------------------WB-------WB-B---BWWB---B--B-------------------7/4/53", b.toString());
	}
}
