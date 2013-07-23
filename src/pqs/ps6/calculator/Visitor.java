package pqs.ps6.calculator;

/**
 * Visitor interface including two methods:
 * visitorNumber that used to visit NumericNode and
 * visitOperator that used to visit OperatorNode.
 * @author Tianyi
 *
 */
public interface Visitor {
	public void visitNumber(NumericNode num);
	public void visitOperator(OperatorNode op);
}