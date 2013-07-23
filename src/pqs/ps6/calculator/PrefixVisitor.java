package pqs.ps6.calculator;

/**
 * Visitor that do preorder traversal to build the prefix expression without 
 * parenthesis since only infix expression has parenthesis.
 * @author Tianyi
 */
public class PrefixVisitor implements Visitor {
	StringBuilder sb = new StringBuilder();
	
	/**
	 * Append number to the infix string.
	 */
	@Override
	public void visitNumber(NumericNode num) {
		sb.append(num.getValue());
	}

	/**
	 * Append operator to the infix string.
	 */
	@Override
	public void visitOperator(OperatorNode op) {
		sb.append(op.getValue());
		if (op.getLeft() != null) {
			op.getLeft().accept(this);
		}
		if (op.getRight() != null) {
			op.getRight().accept(this);
		}
	}
	
	/**
	 * Method that return the infix string.
	 * @return the infix string.
	 */
	public String toString() {
		return sb.toString();
	}
}