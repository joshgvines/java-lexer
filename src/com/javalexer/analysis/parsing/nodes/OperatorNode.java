package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class OperatorNode extends AbsBinaryNode {

    public OperatorNode(AbsBinaryNode left, Token operator, AbsBinaryNode right) {
        super(left, operator, right);
    }

    public AbsBinaryNode getRight() {
        return super.getRight();
    }

    public AbsBinaryNode getLeft() {
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
