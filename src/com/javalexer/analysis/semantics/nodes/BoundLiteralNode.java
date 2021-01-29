package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class BoundLiteralNode extends AbsBoundBinaryNode {

    public BoundLiteralNode(Token literal) {
        super(literal);
    }

    @Override
    public Token data() {
        return super.data();
    }

    @Override
    public String toString() {
        return "OperandNode:[ " + super.data() + " ] ";
    }

}
