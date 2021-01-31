package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BinaryBoundExpressionNode extends AbsBoundBinaryNode {

    public BinaryBoundExpressionNode(AbsBoundBinaryNode left, Token operator, AbsBoundBinaryNode right) {
        super(NodeType.BINARY_EXPRESSION, left, operator, right);
    }

    @Override
    public NodeType getType() {
        return super.getType();
    }

    public AbsBoundNode getLeft() {
        return super.getLeft();
    }

    public Token getData() {
        return super.getData();
    }

    public AbsBoundNode getRight() {
        return super.getRight();
    }

}
