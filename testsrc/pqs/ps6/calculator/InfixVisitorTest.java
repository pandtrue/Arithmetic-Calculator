package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InfixVisitorTest {
	private InfixVisitor iv;
	
	@Before
  public void setUp() {
    iv = new InfixVisitor();
  }

	@Test
	public void test_visitNum() {
		NumericNode num = new NumericNode("3");
		iv.visitNumber(num);
		assertEquals("3", iv.toString());
	}

	@Test
	public void test_visitOperator() {
		OperatorNode op = new OperatorNode("-");
		iv.visitOperator(op);
		assertEquals("(-)", iv.toString());
	}
	
	@Test
	public void test_toString() {
		assertEquals("", iv.toString());
	}
}