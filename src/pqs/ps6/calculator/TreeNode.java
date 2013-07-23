package pqs.ps6.calculator;

/**
 * Base abstract TreeNode class that implements Visitable interface. 
 * Other class can inherit from this base class to be used as a node in the tree.
 * @author Tianyi
 *
 */
public abstract class TreeNode implements Visitable {
	private String value;
	private Visitable left, right;
	
	public TreeNode(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setLeft(Visitable left) {
		this.left = left;
	}
	
	public void setRight(Visitable right) {
		this.right = right;
	}
	
	public Visitable getLeft() {
		return this.left;
	}
	
	public Visitable getRight() {
		return this.right;
	}
	
	public abstract void accept(Visitor visitor);
}
