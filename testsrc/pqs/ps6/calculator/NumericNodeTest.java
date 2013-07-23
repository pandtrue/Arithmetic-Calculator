package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class NumericNodeTest {

	@Test
	public void test_NullValue() {
		NumericNode num = new NumericNode(null);
		assertEquals(null, num.getValue());
	}

	@Test
	public void test_NorMalValue() {
		NumericNode num = new NumericNode("val");
		assertEquals("val", num.getValue());
	}

	@Test
	public void test_SetandGetLeft() {
		NumericNode num = new NumericNode("val");
		NumericNode left = new NumericNode("left");
		num.setLeft(left);
		assertEquals(left, num.getLeft());
	}
	
	@Test
	public void test_SetandGetRight() {
		NumericNode num = new NumericNode("val");
		NumericNode right = new NumericNode("right");
		num.setRight(right);
		assertEquals(right, num.getRight());
	}
}