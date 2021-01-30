package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class LiteralNode extends AbsNode {

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
