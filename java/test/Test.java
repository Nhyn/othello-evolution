import junit.framework.TestCase;
import org.kuhn.oe.Driver;

public class Test extends TestCase {
	public void test() throws Exception {
		long start = System.currentTimeMillis();
		new Driver().game();
		System.out.println(System.currentTimeMillis() - start);
	}
}
