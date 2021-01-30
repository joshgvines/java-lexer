package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BinaryNode extends AbsNode {

    public BinaryNode(AbsNode left, Token operator, AbsNode right) {
        super(left, operator, right);
    }

    public AbsNode getRight() {
        return super.getRight();
    }

    public AbsNode getLeft() {
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
                "Operator: [ " + super.data() + " ] ",
                "right:" + super.getRight() + " ] }");
    }

}
