package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class OperatorNode implements Node {

    private Node right, left;
    private Token operator;

    public OperatorNode() {
    }

    public OperatorNode(Token operator, Node right, Node left) {
        this.operator = operator;
        this.right = right;
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
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

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "OperatorNode: [ " + operator.tokenType + ", ",
                right.getData().tokenType, left.getData().tokenType);
    }

}
