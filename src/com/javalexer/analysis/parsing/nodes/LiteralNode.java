package com.javalexer.analysis.parsing.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class LiteralNode extends AbsNode {

    private Token data;

    public LiteralNode(Token data) {
        super(NodeType.LITERAL);
        this.data = data;
    }

    @Override
    public NodeType getType() {
        return super.getType();
    }

    public Token getData() {
        return data;
    }

}
