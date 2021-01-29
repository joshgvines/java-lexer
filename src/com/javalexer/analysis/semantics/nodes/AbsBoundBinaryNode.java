package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;

public abstract class AbsBoundBinaryNode {

    private Token data;
    private AbsBoundBinaryNode expression;
    private AbsBoundBinaryNode left;
    private AbsBoundBinaryNode right;

    public AbsBoundBinaryNode(Token data) {
        this.data = data;
    }

    public AbsBoundBinaryNode(Token data, AbsBoundBinaryNode operand) {
        this.data = data;
        this.expression = operand;
    }

    public AbsBoundBinaryNode(AbsBoundBinaryNode left, Token data, AbsBoundBinaryNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public AbsBoundBinaryNode(AbsBoundBinaryNode left, AbsBoundBinaryNode expression, AbsBoundBinaryNode right) {
        this.right = right;
        this.expression = expression;
        this.left = left;
    }

    public AbsBoundBinaryNode getExpression() {
        return expression;
    }

    public Token data() {
        return data;
    }

    public AbsBoundBinaryNode getLeft() {
        return left;
    }

    public AbsBoundBinaryNode getRight() {
        return right;
    }

}
