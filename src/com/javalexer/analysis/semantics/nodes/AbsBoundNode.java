package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
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
    private Token data;

    protected AbsBoundBinaryNode(NodeType type, AbsBoundNode left, Token data, AbsBoundNode right) {
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

    Token getData() {
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
    private Token operator;
    private AbsBoundNode operand;

    protected AbsBoundUnaryNode(NodeType type, Token operator, AbsBoundNode operand) {
        super(type);
        this.operator = operator;
        this.operand = operand;
    }

    Token getOperator() {
        return operator;
    }

    AbsBoundNode getOperand() {
        return operand;
    }
}
