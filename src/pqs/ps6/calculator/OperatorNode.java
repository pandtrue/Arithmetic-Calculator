package pqs.ps6.calculator;

/**
 * Operator node that used as a tree node with children which are NumericNodes. 
 * @author Tianyi
 *
 */
public class OperatorNode extends TreeNode {
	public OperatorNode(String value) {
		super(value);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOperator(this);
	}
}
