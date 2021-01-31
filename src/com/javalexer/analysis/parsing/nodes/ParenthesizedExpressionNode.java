package com.javalexer.analysis.parsing.nodes;

import com.javalexer.enums.NodeType;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class ParenthesizedExpressionNode extends AbsBinaryNode {

    public ParenthesizedExpressionNode(AbsNode left, AbsNode expression, AbsNode right) {
        super(NodeType.PARENTHESES_EXPRESSION, left, expression, right);
    }

    @Override
    public AbsNode getRight() {
        return super.getRight();
    }

    @Override
    public AbsNode getExpression() {
        return super.getExpression();
    }

    @Override
    public AbsNode getLeft() {
        return super.getRight();
    }

}
