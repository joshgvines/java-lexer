package com.javalexer.analysis.semantics.nodes;

import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.NodeType;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BoundBinaryExpressionNode extends AbsBoundBinaryNode {

    public BoundBinaryExpressionNode(AbsBoundNode left, BoundOperatorType operator, AbsBoundNode right) {
        super(NodeType.BINARY_EXPRESSION, left, operator, right);
    }

    @Override
    public NodeType getBoundNodeType() {
        return super.getBoundNodeType();
    }

    public AbsBoundNode getLeftNode() {
        return super.getLeftNode();
    }

    public BoundOperatorType getOperatorType() {
        return super.getOperatorType();
    }

    public AbsBoundNode getRightNode() {
        return super.getRightNode();
    }

}
