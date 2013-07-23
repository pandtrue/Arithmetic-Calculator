package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class OperatorNodeTest {

	@Test
	public void test_NullValue() {
		OperatorNode op = new OperatorNode(null);
		assertEquals(null, op.getValue());
	}

	@Test
	public void test_NorMalValue() {
		OperatorNode op = new OperatorNode("+");
		assertEquals("+", op.getValue());
	}

	@Test
	public void test_SetandGetLeft() {
		OperatorNode op = new OperatorNode("+");
		OperatorNode left = new OperatorNode("-");
		op.setLeft(left);
		assertEquals(left, op.getLeft());
	}
	
	@Test
	public void test_SetandGetRight() {
		OperatorNode op = new OperatorNode("+");
		OperatorNode right = new OperatorNode("*");
		op.setRight(right);
		assertEquals(right, op.getRight());
	}
}