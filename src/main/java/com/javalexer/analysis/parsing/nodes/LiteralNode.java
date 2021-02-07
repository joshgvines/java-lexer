package com.javalexer.analysis.parsing.nodes;

import com.javalexer.enums.NodeType;
import com.javalexer.analysis.lexing.Token;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class LiteralNode extends AbsNode {

    private Token token;

    public LiteralNode(Token token) {
        super(NodeType.LITERAL);
        this.token = token;
    }

    @Override
    public NodeType getNodeType() {
        return super.getNodeType();
    }

    public Token getToken() {
        return token;
    }

}
