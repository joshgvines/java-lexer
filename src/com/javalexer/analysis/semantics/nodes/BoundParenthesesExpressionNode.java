package com.javalexer.analysis.semantics.nodes;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class BoundParenthesesExpressionNode extends AbsBoundBinaryNode {

    public BoundParenthesesExpressionNode(AbsBoundBinaryNode left,
                                          AbsBoundBinaryNode expression,
                                          AbsBoundBinaryNode right) {
        super(left, expression, right);
    }

    public AbsBoundBinaryNode getRight() {
        return super.getRight();
    }

    public AbsBoundBinaryNode getLeft() {
        return super.getLeft();
    }

    @Override
    public AbsBoundBinaryNode getExpression() {
        return super.getExpression();
    }

}
