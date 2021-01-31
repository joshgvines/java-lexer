package com.javalexer.analysis.semantics;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.parsing.trees.ExperimentalExpressionTree;
import com.javalexer.analysis.semantics.nodes.AbsBoundNode;
import com.javalexer.analysis.semantics.nodes.BinaryBoundExpressionNode;
import com.javalexer.analysis.semantics.nodes.BoundLiteralNode;
import com.javalexer.analysis.semantics.nodes.BoundUnaryExpressionNode;

/**
 * Orchestrates basic type checking for AST expressions and nodes.
 */
public class Binder {

    public AbsBoundNode bind(AbsNode node) {
        switch (node.getType()) {
            case LITERAL:
                return bindLiteralExpression((LiteralNode) node);
            case BINARY_EXPRESSION:
                return bindBinaryExpression((BinaryExpressionNode) node);
            case UNARY_EXPRESSION:
                return bindUnaryExpression((UnaryExpressionNode) node);
            default:
                throw new UnsupportedOperationException("Unexpected Syntax:" + node.getType());
        }
    }

    private AbsBoundNode bindLiteralExpression(LiteralNode literalNode) {
        Token value = literalNode.getData();
        return new BoundLiteralNode(value);
    }

    private AbsBoundNode bindBinaryExpression(BinaryExpressionNode binaryExpressionNode) {
        AbsBoundNode left = bind(binaryExpressionNode.getLeft());
        Token value = binaryExpressionNode.getData();
        AbsBoundNode right = bind(binaryExpressionNode.getRight());
        return new BinaryBoundExpressionNode(left, value, right);
    }

    private AbsBoundNode bindUnaryExpression(UnaryExpressionNode unaryExpressionNode) {
        Token value = unaryExpressionNode.getOperator();
        AbsBoundNode operand = bind(unaryExpressionNode.getOperand());
        return new BoundUnaryExpressionNode(value, operand);
    }

}
