package com.javalexer.analysis.parsing.nodes;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class ExpressionNode extends AbsNode {

    public ExpressionNode(AbsNode left, AbsNode expression, AbsNode right) {
        super(left, expression, right);
    }

    @Override
    public AbsNode getRight() {
        return super.getRight();
    }

    @Override
    public AbsNode getLeft() {
        return super.getLeft();
    }

    @Override
    public AbsNode getExpression() {
        return super.getExpression();
    }

}
