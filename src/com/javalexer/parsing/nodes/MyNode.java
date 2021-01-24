package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class MyNode {

    private Token data;
    private MyNode left;
    private MyNode right;

    public MyNode(Token data) {
        this.data = data;
    }

    public MyNode(MyNode left, Token data, MyNode right) {
        this.right = right;
        this.data = data;
        this.left = left;
    }

    public Token data() {
        return data;
    }

    public MyNode getLeft() {
        return left;
    }

    public MyNode getRight() {
        return right;
    }

    public void setData(Token data) {
        this.data = data;
    }

}
