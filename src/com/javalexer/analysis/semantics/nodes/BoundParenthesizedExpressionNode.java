package com.javalexer.analysis.semantics.nodes;

import com.javalexer.enums.NodeType;

public class BoundParenthesizedExpressionNode extends AbsBoundBinaryNode {

    public BoundParenthesizedExpressionNode(AbsBoundNode left, AbsBoundNode expression, AbsBoundNode right) {
        super(NodeType.PARENTHESES_EXPRESSION, left, expression, right);
    }

    @Override
    public AbsBoundNode getRight() {
        return super.getRight();
    }

    @Override
    public AbsBoundNode getExpression() {
        return super.getExpression();
    }

    @Override
    public AbsBoundNode getLeft() {
        return super.getRight();
    }

}
