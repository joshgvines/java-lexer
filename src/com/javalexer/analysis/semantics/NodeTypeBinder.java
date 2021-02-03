package com.javalexer.analysis.semantics;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.SyntaxType;

/**
 * Orchestrates basic type checking for AST expressions and nodes.
 */
public class NodeTypeBinder {

    public AbsBoundNode bind(AbsNode node) throws Exception {
        switch (node.getNodeType()) {
            case LITERAL: return bindLiteralExpression((LiteralNode) node);
            case BINARY_EXPRESSION: return bindBinaryExpression((BinaryExpressionNode) node);
            case UNARY_EXPRESSION: return bindUnaryExpression((UnaryExpressionNode) node);
            case PARENTHESES_EXPRESSION: return bindParenthesesExpression((ParenthesizedExpressionNode) node);
            default:
                throw new UnsupportedOperationException("Unexpected Expression Syntax:" + node.getNodeType());
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
        AbsNode left = binaryExpressionNode.getLeft();
        SyntaxType operatorType = binaryExpressionNode.getData().getSyntaxType();
        AbsNode right = binaryExpressionNode.getRight();
        if (!isLiteralBased(left) || !isLiteralBased(right)) {
            System.out.println("Unsupported Nonnumerical Binary Expression: "
                    + left.getNodeType() + " " + operatorType + " " + right.getNodeType());
            return null;
        }
        AbsBoundNode boundLeft = bind(binaryExpressionNode.getLeft());
        BoundOperatorType boundOperatorType = bindBinaryOperatorType(operatorType);
        AbsBoundNode boundRight = bind(binaryExpressionNode.getRight());
        return new BoundBinaryExpressionNode(boundLeft, boundOperatorType, boundRight);
    }

    private AbsBoundNode bindUnaryExpression(UnaryExpressionNode unaryExpressionNode) throws Exception {
        Token operator = unaryExpressionNode.getOperator();
        AbsNode operand = unaryExpressionNode.getOperand();
        if (!isLiteralBased(operand)) {
            System.out.println("Unsupported Nonnumerical Unary Expression: "
                    + operator.getSyntaxType() + " " + operand.getNodeType());
            return null;
        }
        AbsBoundNode boundOperand = bind(operand);
        BoundOperatorType operatorType = bindUnaryOperatorType(operator.getSyntaxType());
        return new BoundUnaryExpressionNode(operatorType, boundOperand);
    }

    private BoundOperatorType bindUnaryOperatorType(SyntaxType type) throws Exception {
        switch (type) {
            case PLUS: return BoundOperatorType.ADDITION;
            case MINUS: return BoundOperatorType.SUBTRACTION;
            default:
                throw new Exception("Unexpected unary Operator: " + type);
        }
    }

    private BoundOperatorType bindBinaryOperatorType(SyntaxType type) throws Exception {
        switch (type) {
            case PLUS: return BoundOperatorType.ADDITION;
            case MINUS: return BoundOperatorType.SUBTRACTION;
            case STAR: return BoundOperatorType.MULTIPLICATION;
            case FORWARD_SLASH: return BoundOperatorType.DIVISION;
            default:
                throw new Exception("Unexpected binary Operator: " + type);
        }
    }

    private boolean isLiteralBased(AbsNode operand) {
        if (operand instanceof LiteralNode) {
            LiteralNode literal = (LiteralNode) operand;
            return literal.getData().getSyntaxType() == SyntaxType.NUMBER;
        }
        return operand instanceof BinaryExpressionNode;
    }

}
