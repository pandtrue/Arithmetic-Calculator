package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PrefixVisitorTest {
	private PrefixVisitor pv;
	
	@Before
  public void setUp() {
    pv = new PrefixVisitor();
  }
	
	@Test
	public void test_visitNum() {
		NumericNode num = new NumericNode("3");
		pv.visitNumber(num);
		assertEquals("3", pv.toString());
	}

	@Test
	public void test_visitOperator() {
		OperatorNode op = new OperatorNode("+");
		pv.visitOperator(op);
		assertEquals("+", pv.toString());
	}
	
	@Test
	public void test_toString() {
		assertEquals("", pv.toString());
	}
}