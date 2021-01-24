package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

public abstract class AbsBinaryNode {

    private Token data;
    private AbsBinaryNode left;
    private AbsBinaryNode right;

    public AbsBinaryNode(Token data) {
        this.data = data;
    }

    public AbsBinaryNode(AbsBinaryNode left, Token data, AbsBinaryNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
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
