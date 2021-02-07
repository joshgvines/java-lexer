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
    public AbsNode getRightNode() {
        return super.getRightNode();
    }

    @Override
    public AbsNode getExpression() {
        return super.getExpression();
    }

    @Override
    public AbsNode getLeftNode() {
        return super.getRightNode();
    }

}
