package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperatorNode has a right and left node, both of which can be an OperandNode or another OperatorNode.
 */
public final class BoundOperatorNode extends AbsBoundBinaryNode {

    public BoundOperatorNode(AbsBoundBinaryNode left, Token operator, AbsBoundBinaryNode right) {
        super(left, operator, right);
    }

    public AbsBoundBinaryNode getRight() {
        return super.getRight();
    }

    public AbsBoundBinaryNode getLeft() {
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
