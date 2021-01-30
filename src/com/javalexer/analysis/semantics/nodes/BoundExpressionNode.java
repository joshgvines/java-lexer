package com.javalexer.analysis.semantics.nodes;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class BoundExpressionNode extends AbsBoundNode {

    public BoundExpressionNode(AbsBoundNode left,
                               AbsBoundNode expression,
                               AbsBoundNode right) {
        super(left, expression, right);
    }

    @Override
    public AbsBoundNode getRight() {
        return super.getRight();
    }

    @Override
    public AbsBoundNode getLeft() {
        return super.getLeft();
    }

    @Override
    public AbsBoundNode getExpression() {
        return super.getExpression();
    }

}
