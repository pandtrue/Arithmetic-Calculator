package pqs.ps6.calculator;

import java.util.Stack;

/**
 * Custom visitor class that used for calculate the result of arithmetic 
 * expression. 
 * @author Tianyi
 *
 */
public class CalculateVisitor implements Visitor {
	private double result;
	// Store the number value after visit number Treenode and the value  
	// being calculated after visit operator TreeNode.  
	private Stack<String> st = new Stack<String>();  
	
	/**
	 * Push number into the stack.
	 */
	@Override
	public void visitNumber(NumericNode num) {
		st.push(num.getValue());
	}
	
	/**
	 * Pull two number out, calculate based on the operator then push the result
	 * back into stack for the later use.
	 */
	@Override
	public void visitOperator(OperatorNode op) {
		op.getLeft().accept(this);
		op.getRight().accept(this);
		Double right = Double.valueOf(st.pop());  
    Double left = Double.valueOf(st.pop()); 
    String operator = op.getValue();
		switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
		}
		 st.push(String.valueOf(result));  
	}
	
	/**
	 * Return the result after calculate
	 * @return the result
	 */
	public double getResult()	{
		return result;   
	}
}