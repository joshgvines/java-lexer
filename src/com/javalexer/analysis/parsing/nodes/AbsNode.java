package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

public abstract class AbsNode {

    private Token data;
    public NodeType type;
    private AbsNode expression;
    private AbsNode left;
    private AbsNode right;

    public AbsNode(Token data) {
        this.data = data;
    }

    public AbsNode(Token data, AbsNode operand) {
        this.data = data;
        this.expression = operand;
    }

    public AbsNode(AbsNode left, Token data, AbsNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public AbsNode(AbsNode left, AbsNode expression, AbsNode right) {
        this.right = right;
        this.expression = expression;
        this.left = left;
    }

    public AbsNode getExpression() {
        return expression;
    }

    public Token data() {
        return data;
    }

    public AbsNode getLeft() {
        return left;
    }

    public AbsNode getRight() {
        return right;
    }

}
