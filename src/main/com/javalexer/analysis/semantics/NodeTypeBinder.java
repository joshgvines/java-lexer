package main.com.javalexer.analysis.semantics;

import main.com.javalexer.analysis.parsing.nodes.*;
import main.com.javalexer.analysis.semantics.nodes.*;
import main.com.javalexer.enums.BoundOperatorType;
import main.com.javalexer.analysis.lexing.Token;
import main.com.javalexer.enums.SyntaxType;

import static main.com.javalexer.enums.SyntaxType.*;

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
        AbsBoundNode left = bind(parenthesizedExpressionNode.getLeftNode());
        AbsBoundNode expression = bind(parenthesizedExpressionNode.getExpression());
        AbsBoundNode right = bind(parenthesizedExpressionNode.getRightNode());
        return new BoundParenthesizedExpressionNode(left, expression, right);
    }

    private AbsBoundNode bindLiteralExpression(LiteralNode literalNode) {
        Token value = literalNode.getToken();
        return new BoundLiteralNode(value);
    }

    private AbsBoundNode bindBinaryExpression(BinaryExpressionNode binaryExpressionNode) throws Exception {
        AbsNode left = binaryExpressionNode.getLeftNode();
        SyntaxType operatorType = binaryExpressionNode.getToken().getSyntaxType();
        AbsNode right = binaryExpressionNode.getRightNode();
        if (!validBinaryExpression(operatorType, left, right)) {
            System.out.println("Unsupported Binary Expression: "
                    + left.getNodeType() + " " + operatorType + " " + right.getNodeType());
            return null;
        }
        AbsBoundNode boundLeft = bind(binaryExpressionNode.getLeftNode());
        BoundOperatorType boundOperatorType = bindBinaryOperatorType(operatorType);
        AbsBoundNode boundRight = bind(binaryExpressionNode.getRightNode());
        return new BoundBinaryExpressionNode(boundLeft, boundOperatorType, boundRight);
    }

    private AbsBoundNode bindUnaryExpression(UnaryExpressionNode unaryExpressionNode) throws Exception {
        Token operator = unaryExpressionNode.getOperatorToken();
        SyntaxType operatorType = operator.getSyntaxType();
        AbsNode operand = unaryExpressionNode.getOperandNode();
        if (!validUnaryExpression(operatorType, operand)) {
            System.out.println("Unsupported Unary Expression: "
                    + operator.getSyntaxType() + " " + operand.getNodeType());
            return null;
        }
        AbsBoundNode boundOperand = bind(operand);
        BoundOperatorType boundOperatorType = bindUnaryOperatorType(operator.getSyntaxType());
        return new BoundUnaryExpressionNode(boundOperatorType, boundOperand);
    }

    private BoundOperatorType bindUnaryOperatorType(SyntaxType type) throws Exception {
        switch (type) {
            case PLUS: return BoundOperatorType.ADDITION;
            case MINUS: return BoundOperatorType.SUBTRACTION;
            case BANG: return BoundOperatorType.LOGIC_NOT;
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
            case AND: return BoundOperatorType.LOGIC_AND;
            case OR: return BoundOperatorType.LOGIC_OR;
            case BANG_NOT_EQUALS: return BoundOperatorType.LOGIC_NOT_EQUALS;
            case EQUALS_COMPARE: return BoundOperatorType.LOGIC_EQUALS_COMPARE;
            default:
                throw new Exception("Unexpected binary Operator: " + type);
        }
    }

    private boolean validBinaryExpression(SyntaxType operatorType, AbsNode left, AbsNode right) {
        return (!isBooleanOperator(operatorType) && (possibleNumericalOperand(left) || possibleNumericalOperand(right)))
                || (isAnyOperator(operatorType));
    }

    private boolean validUnaryExpression(SyntaxType operatorType, AbsNode operand) {
        return (!isBooleanOperator(operatorType) && possibleNumericalOperand(operand));
    }

    private boolean isBooleanOperator(SyntaxType operatorType) {
        return (operatorType == AND || operatorType == OR || operatorType == BANG);
    }

    private boolean isAnyOperator(SyntaxType operatorType) {
        return (operatorType == EQUALS_COMPARE || operatorType == BANG_NOT_EQUALS);
    }

    private boolean possibleNumericalOperand(AbsNode operand) {
        if (operand instanceof LiteralNode) {
            LiteralNode literal = (LiteralNode) operand;
            return (literal.getToken().getSyntaxType() == SyntaxType.NUMBER);
        }
        // TODO: this could causes issues later on, don't forget about it.
        return (operand instanceof AbsExpressionNode);
    }

}
