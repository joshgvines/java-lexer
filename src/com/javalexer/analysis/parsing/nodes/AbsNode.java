package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

public abstract class AbsNode {
    private NodeType type;

    public AbsNode(NodeType type) {
        this.type = type;
    }

    public NodeType getType() {
        return type;
    }

}

abstract class AbsBinaryNode extends AbsNode {
    private AbsNode left;
    private AbsNode right;
    private AbsNode expression;
    private Token data;

    protected AbsBinaryNode(NodeType type, AbsNode left, Token data, AbsNode right) {
        super(type);
        this.left = left;
        this.data = data;
        this.right = right;
    }

    protected AbsBinaryNode(NodeType type, AbsNode left, AbsNode expression, AbsNode right) {
        super(type);
        this.left = left;
        this.expression = expression;
        this.right = right;
    }

    AbsNode getLeft() {
        return left;
    }

    Token getData() {
        return data;
    }

    AbsNode getExpression() {
        return expression;
    }

    AbsNode getRight() {
        return right;
    }
}

abstract class AbsUnaryNode extends AbsNode {
    private Token operator;
    private AbsNode operand;

    protected AbsUnaryNode(NodeType type, Token operator, AbsNode operand) {
        super(type);
        this.operator = operator;
        this.operand = operand;
    }

    Token getOperator() {
        return operator;
    }

    AbsNode getOperand() {
        return operand;
    }
}
