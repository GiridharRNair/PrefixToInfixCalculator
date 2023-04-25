/**
 * The ExpressionTree class accept an arithmetic expression in prefix form, with
 * no parenthesis, and blank space(s) between operators and operands. Then processes
 * the prefix form of an arithmetic expression from left to right to produce a
 * string with the arithmetic expression in infix form, along with the evaluated value.
 *
 * @author Giridhar Nair
 * Collaborators:
 * Teacher Name: Ms. Bailey
 * Period: 2
 * Due Date: 03-16-23
 */

import java.util.Scanner;

public class ExpressionTree
{
    /** Root of the expression tree */
    private ExpressionNode root;

    /** Used to parse a prefix expression */
    private Scanner strScan;

    /** Creates an empty expression tree.
     */
    public ExpressionTree()
    {
        root = null;
    }

    /** Creates an empty expression tree.
     *  @param prefix the arithmetic expression in prefix form.
     */
    public ExpressionTree(String prefix)
    {
        root = null;
        setExpression(prefix);
    }

    /** Builds the expression tree from the inputed arithmetic expression in prefix form.
     *  @param prefix the arithmetic expression in prefix form.
     */
    public void setExpression(String prefix)
    {
        strScan = new Scanner(prefix);
        root = treeCreator();
    }

    /** Helper method to build the expression tree recursively.
     *  @return a leaf of the expression tree.
     */
    private ExpressionNode treeCreator()
    {
        if(!strScan.hasNext())
            return null;
        if(strScan.hasNextInt())
            return new ExpressionNode(Integer.parseInt(strScan.next()));
        return new ExpressionNode(getOperator(strScan.next()), treeCreator(), treeCreator());
    }

    /** Helper method to build the expression tree by identifying the type
     *  operator in the arithmetic expression.
     *  @return the type of operator identified in the arithmetic expression.
     */
    private NodeType getOperator(String operator)
    {
        switch(operator)
        {
            case "+":
                return NodeType.ADD;
            case "-":
                return NodeType.SUBTRACT;
            case "/":
                return NodeType.DIVIDE;
            case "%":
                return NodeType.REMAINDER;
            case "*":
                return NodeType.MULTIPLY;
            case "^":
                return NodeType.EXPONENT;
            default:
                return NodeType.NUMBER;
        }
    }

    /** Evaluates the arithmetic expression in in-order traversal.
     *  @return the value of the arithmetic expression.
     */
    public double evaluate()
    {
        return evaluateHelper(root);
    }

    /** Recursive helper method to evalute the arithemetic expression.
     *  @param the root of the expression tree to traverse from.
     *  @return the value of the arithmetic expression.
     */
    private Double evaluateHelper(ExpressionNode node)
    {
        if (node.getType() == NodeType.NUMBER)
            return (double) node.getValue();
        double leftValue = evaluateHelper(node.getLeft());
        double rightValue = evaluateHelper(node.getRight());
        switch (node.getType()) {
            case ADD:
                return leftValue + rightValue;
            case SUBTRACT:
                return leftValue - rightValue;
            case MULTIPLY:
                return leftValue * rightValue;
            case DIVIDE:
                return leftValue / rightValue;
            case REMAINDER:
                return leftValue % rightValue;
            case EXPONENT:
                return Math.pow(leftValue, rightValue);
            default:
                return 0.0;
        }
    }

    /** Converts the arithmetic expression in prefix form to infix form.
     *  @return the string of the arithmetic expression in infix form.
     */
    @Override
    public String toString()
    {
        if (root == null)
            return "0";
        return toStringHelper(root);
    }

    /** Recursive helper method to convert the arithmetic expression
     *  in prefix form to infix form.
     *  @return the string of the arithmetic expression in infix form.
     */
    private String toStringHelper(ExpressionNode node)
    {
        if (node.getLeft() == null && node.getRight() == null)
            return String.valueOf(node.getValue());
        else if (node.getRight() == null)
            return node.getType().getSymbol() + " " + toStringHelper(node.getLeft());
        else
            return "(" + toStringHelper(node.getLeft()) + " " + node.getType().getSymbol()
                    + " " + toStringHelper(node.getRight()) + ")";
    }
}