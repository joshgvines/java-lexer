package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.NodeType;

/**
 * Represents nodes in a tree during the semantic analysis phase.
 * The nodes and structure become 'bound' after type checking.
 */
public abstract class AbsBoundNode {
    private NodeType type;

    public AbsBoundNode(NodeType type) {
        this.type = type;
    }

    public NodeType getType() {
        return type;
    }

}

abstract class AbsBoundBinaryNode extends AbsBoundNode {
    private AbsBoundNode left;
    private AbsBoundNode right;
    private AbsBoundNode expression;
    private BoundOperatorType data;

    protected AbsBoundBinaryNode(NodeType type, AbsBoundNode left, BoundOperatorType data, AbsBoundNode right) {
        super(type);
        this.left = left;
        this.data = data;
        this.right = right;
    }

    protected AbsBoundBinaryNode(NodeType type, AbsBoundNode left, AbsBoundNode expression, AbsBoundNode right) {
        super(type);
        this.left = left;
        this.expression = expression;
        this.right = right;
    }

    AbsBoundNode getLeft() {
        return left;
    }

    BoundOperatorType getData() {
        return data;
    }

    AbsBoundNode getExpression() {
        return expression;
    }

    AbsBoundNode getRight() {
        return right;
    }
}

abstract class AbsBoundUnaryNode extends AbsBoundNode {
    private BoundOperatorType operatorType;
    private AbsBoundNode operand;

    protected AbsBoundUnaryNode(NodeType type, BoundOperatorType operatorType, AbsBoundNode operand) {
        super(type);
        this.operatorType = operatorType;
        this.operand = operand;
    }

    BoundOperatorType getOperator() {
        return operatorType;
    }

    AbsBoundNode getOperand() {
        return operand;
    }
}
