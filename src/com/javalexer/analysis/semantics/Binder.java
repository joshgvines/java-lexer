package com.javalexer.analysis.semantics;

import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.semantics.nodes.AbsBoundNode;

/**
 * Orchestrates basic type checking for expression types.
 */
public class Binder {

    public AbsBoundNode bind(AbsNode node) {
        switch (node.type) {
            case LITERAL_EXPRESSION:
                return bindLiteralExpression((LiteralNode) node);
            case BINARY_EXPRESSION:
                return bindBinaryExpression((BinaryNode) node);
            case UNARY_EXPRESSION:
                return bindUnaryExpression((UnaryNode) node);
            default:
                throw new UnsupportedOperationException("Unknown Expression Type:" + node.type);
        }
    }

    private AbsBoundNode bindLiteralExpression(LiteralNode literalNode) {
        return null;
    }

    private AbsBoundNode bindBinaryExpression(BinaryNode binaryNode) {
        return null;
    }

    private AbsBoundNode bindUnaryExpression(UnaryNode unaryNode) {
        return null;
    }

}
