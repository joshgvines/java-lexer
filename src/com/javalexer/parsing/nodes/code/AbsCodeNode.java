package com.javalexer.parsing.nodes.code;

import com.javalexer.analyzers.Token;

public abstract class AbsCodeNode {

    private Token data;
    private AbsCodeNode left;
    private AbsCodeNode right;

    public AbsCodeNode(Token data) {
        this.data = data;
    }

    public AbsCodeNode(AbsCodeNode left, Token data, AbsCodeNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public AbsCodeNode(AbsCodeNode left, AbsCodeNode expression, AbsCodeNode right) {
        this.right = right;
        this.left = left;
    }

    public Token data() {
        return data;
    }

    public AbsCodeNode getLeft() {
        return left;
    }

    public AbsCodeNode getRight() {
        return right;
    }

    public void setData(Token data) {
        this.data = data;
    }

}
