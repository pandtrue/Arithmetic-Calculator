package pqs.ps6.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

	@Test
	public void test_evaluate1() throws Exception {
		Calculator cal = new Calculator("3*(5+2)");
		assertTrue(cal.getResult() - 21 < 0.00001);
	}

	@Test
	public void test_evaluate2() throws Exception {
		Calculator cal = new Calculator("1.1+2.2*3.3/4.4");
		assertTrue(cal.getResult() - 2.75 < 0.00001);
	}
	
	@Test
	public void test_evaluate3() throws Exception {
		Calculator cal = new Calculator("3*4-2.0+7/21");
		assertTrue(cal.getResult() - 10.33333333 < 0.00001);
	}
	
	@Test
	public void test_evaluate4() throws Exception {
		Calculator cal = new Calculator("(2+2)/(4-8)");
		assertTrue(cal.getResult() - -1 < 0.00001);
	}
	
	@Test
	public void test_evaluate5() throws Exception {
		Calculator cal = new Calculator("(1-2+3/4*5)");
		assertTrue(cal.getResult() - 2.75 < 0.00001);
	}
	
	
	@Test
	public void test_evaluate6() throws Exception {
		Calculator cal = new Calculator("3*2-5+2/4");
		assertTrue(cal.getResult() - 1.5 < 0.00001);
	}
	
	@Test
	public void test_getPrefix() throws Exception {
		Calculator cal = new Calculator("3*(5+2)");
		assertEquals("*3+52", cal.getPrefix());
	}
	
	@Test
	public void test_getInfix() throws Exception {
		Calculator cal = new Calculator("3*(5+2)");
		assertEquals("(3*(5+2))", cal.getInfix());
	}
	
	@Test
	public void test_getPostfix() throws Exception {
		Calculator cal = new Calculator("3*(5+2)");
		assertEquals("352+*", cal.getPostfix());
	}
	
	@Test(expected = Exception.class)
	public void test_NotValidExpression1() throws Exception {
			Calculator cal = new Calculator("3*#6");
			cal.getResult();
	}
	
	@Test(expected = Exception.class)
	public void test_NotValidExpression2() throws Exception{
			Calculator cal = new Calculator("3.2/2*2-3)");
			cal.getResult();
	}
	@Test(expected = Exception.class)
	public void test_NotValidExpression3() throws Exception{
			Calculator cal = new Calculator("-2///2");
			cal.getResult();
	}
	
	@Test(expected = NullPointerException.class)
	public void test_nullExpression() throws Exception{
		Calculator cal = new Calculator(null);
		cal.getResult();
	}
}