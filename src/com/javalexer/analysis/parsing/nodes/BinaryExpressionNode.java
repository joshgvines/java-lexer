package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BinaryExpressionNode extends AbsBinaryNode {

    public BinaryExpressionNode(AbsNode left, Token operator, AbsNode right) {
        super(NodeType.BINARY_EXPRESSION, left, operator, right);
    }

    @Override
    public NodeType getNodeType() {
        return super.getNodeType();
    }

    public AbsNode getLeft() {
        return super.getLeft();
    }

    public Token getData() {
        return super.getData();
    }

    public AbsNode getRight() {
        return super.getRight();
    }

}
