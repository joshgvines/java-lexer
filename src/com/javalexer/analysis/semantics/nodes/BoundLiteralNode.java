package com.javalexer.analysis.semantics.nodes;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.NodeType;
import com.javalexer.enums.SyntaxType;

/**
 * An OperandNode cannot have a right or left node, it must always be a leaf.
 */
public final class BoundLiteralNode extends AbsBoundNode {

    private Token data;

    public BoundLiteralNode(Token data) {
        super(NodeType.LITERAL);
        this.data = data;
    }

    @Override
    public NodeType getBoundNodeType() {
        return super.getBoundNodeType();
    }

    public Token getData() {
        return data;
    }

    public SyntaxType getSyntaxType() {
        return data.getSyntaxType();
    }

}
