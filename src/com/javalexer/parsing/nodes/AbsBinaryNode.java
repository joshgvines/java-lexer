package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

public abstract class AbsBinaryNode {

    private Token data;
    private AbsBinaryNode expression;
    private AbsBinaryNode left;
    private AbsBinaryNode right;

    public AbsBinaryNode(Token data) {
        this.data = data;
    }

    public AbsBinaryNode(Token data, AbsBinaryNode operand) {
        this.data = data;
        this.expression = operand;
    }

    public AbsBinaryNode(AbsBinaryNode left, Token data, AbsBinaryNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public AbsBinaryNode(AbsBinaryNode left, AbsBinaryNode expression, AbsBinaryNode right) {
        this.right = right;
        this.expression = expression;
        this.left = left;
    }

    public AbsBinaryNode getExpression() {
        return expression;
    }

    public Token data() {
        return data;
    }

    public AbsBinaryNode getLeft() {
        return left;
    }

    public AbsBinaryNode getRight() {
        return right;
    }

    public void setData(Token data) {
        this.data = data;
    }

}
