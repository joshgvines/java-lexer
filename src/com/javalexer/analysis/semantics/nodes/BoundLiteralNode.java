package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;
import com.javalexer.enums.SyntaxType;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class BoundLiteralNode extends AbsBoundNode {

    private Token token;

    public BoundLiteralNode(Token token) {
        super(NodeType.LITERAL);
        this.token = token;
    }

    @Override
    public NodeType getBoundNodeType() {
        return super.getBoundNodeType();
    }

    public Token getToken() {
        return token;
    }

    public SyntaxType getSyntaxType() {
        return token.getSyntaxType();
    }

}
