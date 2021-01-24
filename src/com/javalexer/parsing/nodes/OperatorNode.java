package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public class OperatorNode extends MyNode {

    public OperatorNode(MyNode left, Token operator, MyNode right) {
        super(left, operator, right);
    }

    public MyNode getRight() {
        return super.getRight();
    }

    public MyNode getLeft() {
        return super.getLeft();
    }

    @Override
    public Token data() {
        return super.data();
    }

    @Override
    public String toString() {
        return String.format("%-14s %-10s %-5s",
                "{ OperatorNode [ left:" + super.getLeft(),
                "Operator: [ "+super.data()+ " ] ",
                "right:" + super.getRight() + " ] }");
    }

}
