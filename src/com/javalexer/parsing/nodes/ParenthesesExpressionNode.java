package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class ParenthesesExpressionNode extends AbsBinaryNode {

    public ParenthesesExpressionNode(AbsBinaryNode left, AbsBinaryNode expression, AbsBinaryNode right) {
        super(left, expression, right);
    }

    public AbsBinaryNode getRight() {
        return super.getRight();
    }

    public AbsBinaryNode getLeft() {
        return super.getLeft();
    }

    @Override
    public AbsBinaryNode getExpression() {
        return super.getExpression();
    }

}
