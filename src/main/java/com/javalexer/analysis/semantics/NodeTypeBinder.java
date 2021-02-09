package com.javalexer.analysis.semantics;

import com.javalexer.analysis.lexing.Lexer;
import com.javalexer.analysis.parsing.nodes.*;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.SyntaxType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.javalexer.enums.BoundOperatorType.*;
import static com.javalexer.enums.SyntaxType.*;

/**
 * Orchestrates basic type checking for AST expressions and nodes.
 */
public class NodeTypeBinder {

    private static final Logger log = LogManager.getLogger(NodeTypeBinder.class);

    public AbsBoundNode bind(AbsNode node) throws Exception {
        switch (node.getNodeType()) {
            case LITERAL: return bindLiteralExpression((LiteralNode) node);
            case BINARY_EXPRESSION: return bindBinaryExpression((BinaryExpressionNode) node);
            case UNARY_EXPRESSION: return bindUnaryExpression((UnaryExpressionNode) node);
            case PARENTHESES_EXPRESSION: return bindParenthesesExpression((ParenthesizedExpressionNode) node);
            default:
                log.error("Node did not contain a recognized type.");
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
            log.warn("Unsupported Binary Expression: [ Left: {}, Operator: {}, Right: {} ]",
                    left.getNodeType(), operatorType, right.getNodeType());
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
            log.warn("Unsupported Unary Expression: [ Operator: {}, Operand: {} ]",
                    operatorType, operand.getNodeType());
            return null;
        }
        AbsBoundNode boundOperand = bind(operand);
        BoundOperatorType boundOperatorType = bindUnaryOperatorType(operator.getSyntaxType());
        return new BoundUnaryExpressionNode(boundOperatorType, boundOperand);
    }

    private BoundOperatorType bindUnaryOperatorType(SyntaxType type) throws Exception {
        switch (type) {
            case PLUS: return ADDITION;
            case MINUS: return SUBTRACTION;
            case BANG: return LOGIC_NOT;
            default:
                log.error("Unary Operator did not contain a recognized type: {}", type);
                throw new Exception("Unexpected unary Operator: " + type);
        }
    }

    private BoundOperatorType bindBinaryOperatorType(SyntaxType type) throws Exception {
        switch (type) {
            case PLUS: return ADDITION;
            case MINUS: return SUBTRACTION;
            case STAR: return MULTIPLICATION;
            case FORWARD_SLASH: return DIVISION;
            case AND: return LOGIC_AND;
            case OR: return LOGIC_OR;
            case BANG_NOT_EQUALS: return LOGIC_NOT_EQUALS;
            case EQUALS_COMPARE: return LOGIC_EQUALS_COMPARE;
            default:
                log.error(" Binary Operator did not contain a recognized type: {}", type);
                throw new Exception("Unexpected binary Operator: " + type);
        }
    }

    private boolean validBinaryExpression(SyntaxType operatorType, AbsNode left, AbsNode right) {
        if (anyOperator(operatorType)) {
            return true;
        }
        if (booleanOperator(operatorType)) {
            return (!numericalOperand(left) && !numericalOperand(right));
        }
        return (numericalOperand(left) && numericalOperand(right));
    }

    private boolean validUnaryExpression(SyntaxType operatorType, AbsNode operand) {
        return (!booleanOperator(operatorType) && numericalOperand(operand));
    }

    private boolean booleanOperator(SyntaxType operatorType) {
        return (operatorType == AND || operatorType == OR || operatorType == BANG);
    }

    private boolean anyOperator(SyntaxType operatorType) {
        return (operatorType == EQUALS_COMPARE || operatorType == BANG_NOT_EQUALS);
    }

    private boolean numericalOperand(AbsNode operand) {
        if (operand instanceof LiteralNode) {
            LiteralNode literal = (LiteralNode) operand;
            return (literal.getToken().getSyntaxType() == SyntaxType.NUMBER);
        }
        // TODO: this could causes issues later on, don't forget about it.
        return (operand instanceof AbsExpressionNode);
    }

}
