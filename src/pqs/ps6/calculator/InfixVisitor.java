package pqs.ps6.calculator;

/**
 * Visitor that do inorder traversal to build the infix expression with 
 * parenthesis.
 * @author Tianyi
 */
public class InfixVisitor implements Visitor {
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
		sb.append("(");
		if (op.getLeft() != null) {
			op.getLeft().accept(this);
		}
		sb.append(op.getValue());
		if (op.getRight() != null) {
			op.getRight().accept(this);
		}
		sb.append(")");
	}
	
	/**
	 * Method that return the infix string.
	 * @return the infix string.
	 */
	public String toString() {
		return sb.toString();
	}
}