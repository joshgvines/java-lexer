package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class UnaryNode extends AbsNode {

    public UnaryNode(Token operator, AbsNode operand) {
        super(operator, operand);
    }

    @Override
    public Token data() {
        return super.data();
    }

    @Override
    public AbsNode getExpression() {
        return super.getExpression();
    }

    @Override
    public String toString() {
        return "OperandNode:[ " + super.data() + " ] ";
    }

}
