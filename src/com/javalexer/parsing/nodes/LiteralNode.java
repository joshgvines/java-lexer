package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class LiteralNode extends AbsBinaryNode {

    public LiteralNode(Token literal) {
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
