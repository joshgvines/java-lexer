package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class OperatorMyNode implements MyNode {

    private MyNode right, left;
    private Token operator;

    public OperatorMyNode() {
    }

    public OperatorMyNode(Token operator, MyNode right, MyNode left) {
        this.operator = operator;
        this.right = right;
        this.left = left;
    }

    public MyNode getRight() {
        return right;
    }

    public MyNode getLeft() {
        return left;
    }

    public void setRight(MyNode right) {
        this.right = right;
    }

    public void setLeft(MyNode left) {
        this.left = left;
    }

    @Override
    public Token getData() {
        return operator;
    }

    @Override
    public void setData(Token operator) {
        this.operator = operator;
    }

    public Token[] getChildren() {
        return new Token[] { left.getData(), operator, right.getData() };
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "OperatorNode: [ " + operator.type + ", ",
                right.getData().type, left.getData().type);
    }

}
