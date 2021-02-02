package com.javalexer.analysis.semantics;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.TokenType;

/**
 * Orchestrates basic type checking for AST expressions and nodes.
 */
public class NodeTypeBinder {

    public AbsBoundNode bind(AbsNode node) throws Exception {
        switch (node.getType()) {
            case LITERAL: return bindLiteralExpression((LiteralNode) node);
            case BINARY_EXPRESSION: return bindBinaryExpression((BinaryExpressionNode) node);
            case UNARY_EXPRESSION: return bindUnaryExpression((UnaryExpressionNode) node);
            case PARENTHESES_EXPRESSION: return bindParenthesesExpression((ParenthesizedExpressionNode) node);
            default:
                throw new UnsupportedOperationException("Unexpected Expression Syntax:" + node.getType());
        }
    }

    private AbsBoundNode bindParenthesesExpression(
            ParenthesizedExpressionNode parenthesizedExpressionNode) throws Exception {
        AbsBoundNode left = bind(parenthesizedExpressionNode.getLeft());
        AbsBoundNode expression = bind(parenthesizedExpressionNode.getExpression());
        AbsBoundNode right = bind(parenthesizedExpressionNode.getRight());
        return new BoundParenthesizedExpressionNode(left, expression, right);
    }

    private AbsBoundNode bindLiteralExpression(LiteralNode literalNode) {
        Token value = literalNode.getData();
        return new BoundLiteralNode(value);
    }

    private AbsBoundNode bindBinaryExpression(BinaryExpressionNode binaryExpressionNode) throws Exception {
        AbsBoundNode left = bind(binaryExpressionNode.getLeft());
        BoundOperatorType operatorType = bindBinaryOperatorType(binaryExpressionNode.getData().getType());
        AbsBoundNode right = bind(binaryExpressionNode.getRight());
        return new BoundBinaryExpressionNode(left, operatorType, right);
    }

    private AbsBoundNode bindUnaryExpression(UnaryExpressionNode unaryExpressionNode) throws Exception {
        BoundOperatorType operatorType = bindUnaryOperatorType(unaryExpressionNode.getOperator().getType());
        AbsBoundNode operand = bind(unaryExpressionNode.getOperand());
        return new BoundUnaryExpressionNode(operatorType, operand);
    }

    private BoundOperatorType bindUnaryOperatorType(TokenType type) throws Exception {
        switch (type) {
            case PLUS: return BoundOperatorType.ADDITION;
            case MINUS: return BoundOperatorType.SUBTRACTION;
            default:
                throw new Exception("Unexpected unary Operator: " + type);
        }
    }

    private BoundOperatorType bindBinaryOperatorType(TokenType type) throws Exception {
        switch (type) {
            case PLUS: return BoundOperatorType.ADDITION;
            case MINUS: return BoundOperatorType.SUBTRACTION;
            case STAR: return BoundOperatorType.MULTIPLICATION;
            case FORWARD_SLASH: return BoundOperatorType.DIVISION;
            default:
                throw new Exception("Unexpected binary Operator: " + type);
        }
    }

}
