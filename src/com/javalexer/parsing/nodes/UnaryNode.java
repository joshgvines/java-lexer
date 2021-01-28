package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class UnaryNode extends AbsBinaryNode {

    public UnaryNode(Token operator, AbsBinaryNode operand) {
        super(operator, operand);
    }

    @Override
    public Token data() {
        return super.data();
    }

    @Override
    public AbsBinaryNode getExpression() {
        return super.getExpression();
    }

    @Override
    public String toString() {
        return "OperandNode:[ " + super.data() + " ] ";
    }

}
