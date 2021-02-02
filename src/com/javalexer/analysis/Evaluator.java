package com.javalexer.analysis;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.TokenType;

public class Evaluator {

    private BoundInfixExpressionTree expressionTree;

    public Evaluator(BoundInfixExpressionTree expressionTree) {
        this.expressionTree = expressionTree;
    }

    public double evaluate() {
        return evaluate(expressionTree.getRoot());
    }

    private double evaluate(AbsBoundNode node) {
        double result;
        if (node == null) {
            return 0;
        }
        switch (node.getType()) {
            case LITERAL:
                return toDouble((BoundLiteralNode) node);
            case UNARY_EXPRESSION:
                BoundUnaryExpressionNode unaryExpression = (BoundUnaryExpressionNode) node;
                result = evaluate(unaryExpression.getOperand());
                if (unaryExpression.getOperator() == BoundOperatorType.SUBTRACTION) {
                    result = -result;
                }
                return result;
            case BINARY_EXPRESSION:
                BoundBinaryExpressionNode binaryExpression = (BoundBinaryExpressionNode) node;
                BoundOperatorType operatorType = binaryExpression.getData();
                double a = evaluate(binaryExpression.getLeft());
                double b = evaluate(binaryExpression.getRight());
                return compute(operatorType, a, b);
            case PARENTHESES_EXPRESSION:
                return evaluate(((BoundParenthesizedExpressionNode) node).getExpression());
            default:
                return 0;
        }
    }

    private double compute(BoundOperatorType operator, double a, double b) {
        switch (operator) {
            case ADDITION: return a + b;
            case SUBTRACTION: return a - b;
            case MULTIPLICATION: return a * b;
            case MODULO: return a % b;
            case DIVISION:
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    private double toDouble(BoundLiteralNode literalNode) {
        Token token = literalNode.getData();
        if (token.getType() == TokenType.NUMBER) {
            return Double.parseDouble(token.getValue());
        }
        throw new UnsupportedOperationException("Token was not a number: " + token);
    }

}
