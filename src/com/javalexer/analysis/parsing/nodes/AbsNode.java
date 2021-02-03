package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

public abstract class AbsNode {
    private NodeType nodeType;

    public AbsNode(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }
}

abstract class AbsExpression extends AbsNode {
    private Token tokenData;
    private AbsNode expressionData;

    public AbsExpression(NodeType type, Token tokenData) {
        super(type);
        this.tokenData = tokenData;
    }

    public AbsExpression(NodeType type, AbsNode expressionData) {
        super(type);
        this.expressionData = expressionData;
    }
}

abstract class AbsBinaryNode extends AbsExpression {
    private AbsNode left;
    private AbsNode right;
    private AbsNode expression;
    private Token data;

    protected AbsBinaryNode(NodeType type, AbsNode left, Token data, AbsNode right) {
        super(type, data);
        this.left = left;
        this.data = data;
        this.right = right;
    }

    protected AbsBinaryNode(NodeType type, AbsNode left, AbsNode expression, AbsNode right) {
        super(type, expression);
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

abstract class AbsUnaryNode extends AbsExpression {
    private Token operator;
    private AbsNode operand;

    protected AbsUnaryNode(NodeType type, Token operator, AbsNode operand) {
        super(type, operator);
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
