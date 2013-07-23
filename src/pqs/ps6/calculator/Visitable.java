package pqs.ps6.calculator;

/**
 * Visitable class. Can be implement by different class so that all of them 
 * can be treat as visitor.
 * @author Tianyi
 *
 */
public interface Visitable {
	public void accept(Visitor visitor);
}