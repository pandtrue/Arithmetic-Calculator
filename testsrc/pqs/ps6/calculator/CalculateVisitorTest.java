package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculateVisitorTest {
	private CalculateVisitor cv;
	
	@Before
  public void setUp() {
    cv = new CalculateVisitor();
  }
	
	@Test
	public void test_visitNum() {
		NumericNode num = new NumericNode("3");
		cv.visitNumber(num);
		assertTrue(cv.getResult() - 0.0 < 0.00001);
	}
	
	@Test
	public void test_visitOperator() {
		OperatorNode op = new OperatorNode("+");
		NumericNode left = new NumericNode("3");
		NumericNode right = new NumericNode("4");
		op.setLeft(left);
		op.setRight(right);
		cv.visitOperator(op);
		assertTrue(cv.getResult() - 7 < 0.00001);
	}
	
	@Test
	public void test_toString() {
		assertEquals("", cv.toString());
	}
}