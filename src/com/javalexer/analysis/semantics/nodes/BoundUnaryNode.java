package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class BoundUnaryNode extends AbsBoundNode {

    public BoundUnaryNode(Token operator, AbsBoundNode operand) {
        super(operator, operand);
    }

    @Override
    public Token data() {
        return super.data();
    }

    @Override
    public AbsBoundNode getExpression() {
        return super.getExpression();
    }

    @Override
    public String toString() {
        return "OperandNode:[ " + super.data() + " ] ";
    }

}
