package pqs.ps6.calculator;

/**
 * Numeric node that used as a tree node without children since all numbers 
 * are located on leaf node. 
 * @author Tianyi
 *
 */
public class NumericNode extends TreeNode {
	public NumericNode(String value) {
		super(value);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitNumber(this);
	}
}