package com.javalexer.parsing.nodes;

import com.javalexer.analyzers.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public class NumberNode extends AbsBinaryNode {

    public NumberNode(Token number) {
        super(number);
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
