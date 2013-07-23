package pqs.ps6.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * main class of the calculator. Class include methods that use for return 
 * prefix, infix, and postfix of the arithmetic expression. Also can calculate
 * the result of the expression.
 * 
 * @author Tianyi
 *
 */
public class Calculator {
	// Place store the postfix expression
	private static ArrayList<String> postfix;
	//Associativity constants for operators  
	private static final int LEFT_ASSOC = 0;  
	// The root node of the arithmetic expression tree
	private static TreeNode root;
	
	/**
	 * Constructor of the Calculator. Take a arithmetic expression and parse it.
	 * @param expression: arithmetic expression going to be parsed.
	 * @throws Exception if expression is not valid.
	 */
	public Calculator(String expression) throws Exception {
		// parse the expression to build the tree before evaluate it.
		parse(expression);
	}
	
	/**
	 * Method used to get the prefix of the arithmetic expression with PrefixVisitor.
	 * 
	 * @return prefix expression.
	 */
	public String getPrefix() {
		PrefixVisitor pre = new PrefixVisitor();
		Calculator.accept(pre);
		return pre.toString();
	}
	
	/**
	 * Method used to get the infix of the arithmetic expression with InfixVisitor.
	 * 
	 * @return infix expression.
	 */
	public String getInfix() {
		InfixVisitor in = new InfixVisitor();
		Calculator.accept(in);
		return in.toString();
	}
	
	/**
	 * Method used to get the postfix of the arithmetic expression convert from infix.
	 * 
	 * @return postfix expression.
	 */
	public String getPostfix() {
		StringBuilder sb = new StringBuilder();
		for(String s : postfix){
			sb.append(s);
		}
		return sb.toString();
	}
	
	/**
	 * Method used to evaluate the arithmetic expression with CalculateVisitor.
	 * 
	 * @return result of the expression.
	 */
	public double getResult() {
		CalculateVisitor cv = new CalculateVisitor();
		Calculator.accept(cv);
		return cv.getResult();
	}
	
	/**
	 * Map that contains the precedence and associativity of the operators.
	 */
	private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();  
  static {  
	  OPERATORS.put("+", new int[] { 0, LEFT_ASSOC });  
	  OPERATORS.put("-", new int[] { 0, LEFT_ASSOC });  
	  OPERATORS.put("*", new int[] { 5, LEFT_ASSOC });  
	  OPERATORS.put("/", new int[] { 5, LEFT_ASSOC });          
  }  
   
  /**
   * Private method that used to check whether operator is valid.  
   * 
   * @param token: the operator going to be checked.
   * @return true if support, otherwise false.
   */
  private static boolean isOperator(String token) {  
    return OPERATORS.containsKey(token);  
  }  
   
  /**
   * Private method that used to compare precedence of operators.  
   *     
   * @param token1: first operator
   * @param token2: second operator
   * @return token1 win if positive, token2 win if negative, due if 0.
   */
  private static final int cmpPrecedence(String token1, String token2) {   
    return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];  
  } 
  
	/**
	 * Private method that used to test associativity of operator token.
	 * 
	 * @param token 
	 * @param type: LEFT_ASSOC or RIGHT_ASSOC
	 * @return true if associate; otherwise, false.
	 */
  private static boolean isAssociative(String token, int type) {  
    if (OPERATORS.get(token)[1] == type) {  
      return true;  
    }  
    return false;  
  }  
  
	/**
	 * Private method runs when a new expression created. Convert infix expression 
	 * to postfix expression with Shunting-yard algorithm and check whether the 
	 * expression is valid at the same time. Then Create the expression tree with
	 * both infix and postfix expression. The tree can be used for applying different
	 * visitor to get infix expression, prefix expression, and evaluate the result.
	 * 
	 * @param exp arithmetic expression going to be parsed.
	 * @throws Exception if expression is not well formed.
	 */
	private void parse(String exp) throws Exception {
		Stack<String> st = new Stack<String>();
    ArrayList<String> postorder = new ArrayList<String>();
    ArrayList<String> inorder = new ArrayList<String>();
    st.push("#");
    String character = "";
    try {
	    for (int i = 0; i < exp.length(); i++) {
	      char ch = exp.charAt(i);
	      // If token is a number, number can be double. If the string can not be 
	      // parsed to double, throw exception to show the expression if not valid.
				if (!isOperator(String.valueOf(ch)) && ch != '(' && ch !=')') {
				  character += String.valueOf(ch);
				  // if next character is still not operator and parenthesis, then this whole
				  // number is probably a double or invalid syntax.
				  if ((i + 1) < exp.length() && !isOperator(String.valueOf(exp.charAt(i+1))) 
				  		&& exp.charAt(i+1) != '(' && exp.charAt(i+1) !=')') {
				    continue;
				  } else {
			  		Double.parseDouble(character);
				  	postorder.add(character);
				    inorder.add(character);
				    character = "";
				  }
				// If token is a left parenthesis
				} else if (ch == '(') {
				  st.push(String.valueOf(ch));
				// If token is a right parenthesis  
				} else if (ch == ')') {
					while (!st.empty() && !st.peek().equals("("))   
	        {  
	          postorder.add(st.pop());   
	        }  
	        st.pop();   
	      // If token is an operator  
				} else {
					inorder.add(String.valueOf(ch));
				  while (!st.empty() && isOperator(st.peek())) {                      
	          if ((isAssociative(String.valueOf(ch), LEFT_ASSOC) && 
	          		cmpPrecedence(String.valueOf(ch), st.peek()) <= 0) ||
	          		cmpPrecedence(String.valueOf(ch), st.peek()) < 0) {
	            postorder.add(st.pop());     
	            continue;  
	          }  
	          break;  
	        }  
	        // Push the new operator on the stack  
	        st.push(String.valueOf(ch));  
				}
	    }
	    String c = st.pop();
	    while (!c.equals("#")) {
	    	postorder.add(c);
	      c = st.pop();
	    }
  	} catch (Exception e) {
  		System.err.println("Not valid arthimatic expression!");
  	}
    // Assign value to infix and postfix.
    postfix = postorder;
    String[] u = (String[]) inorder.toArray(new String[inorder.size()]);  
  	String[] v = (String[]) postorder.toArray(new String[postorder.size()]); 
  	// Build tree with infix and postfix.
  	root = buildTree(u,v);
	}
	
	/**
	 * Private method that call the helper to build the arithmetic expression
	 * tree with infix and postfix.
	 * @param inorder infix array
	 * @param postorder postfix array
	 * @return root of the expression tree.
	 */
	private static TreeNode buildTree(String[] inorder, String[] postorder) {
    if (inorder.length != postorder.length || inorder.length < 1) {
      return null;
    } else {
      return build(inorder, postorder, 0, 0, inorder.length);
    }
  }
  
	/**
	 * Private helper method that used by buildTree method to actually build
	 * the tree.
	 * @param inorder infix array
	 * @param postorder postfix array
	 * @param ins start postion of the infix array
	 * @param posts start postion of the postfix array
	 * @param length length of the array going to be use
	 * @return root of the expression tree.
	 */
  private static TreeNode build(String[] inorder, String[] postorder, int ins, 
  		int posts, int length) {
    if (ins < 0 || ins + length > inorder.length || posts < 0 || posts + length
    		> postorder.length || length < 1) {
    	return null;
    }
    TreeNode root = null;    
    String temp = postorder[posts + length - 1];
			if (isOperator(temp)) {
				root = new OperatorNode(temp);
			} else {
				root = new NumericNode(temp);
			}
    int i=0;
    for (i = 0; i < length; i++) {
      if (inorder[ins+i].equals(temp)) {
      	break;
      }
    }
    int leftlength = i;
    int rightlength = length - 1 - leftlength;
    root.setLeft(build(inorder, postorder, ins, posts, leftlength));
    root.setRight(build(inorder, postorder, ins + leftlength + 1, posts + leftlength, rightlength));
    return root;
  }
  
  public static void accept(Visitor visitor) {
		root.accept(visitor);
	}
}