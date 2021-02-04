package com.javalexer.analysis;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
import com.javalexer.analysis.semantics.nodes.*;
import com.javalexer.enums.BoundOperatorType;
import com.javalexer.enums.SyntaxType;

public class Evaluator {

    private BoundInfixExpressionTree expressionTree;

    public Evaluator(BoundInfixExpressionTree expressionTree) {
        this.expressionTree = expressionTree;
    }

    public Object evaluate() throws Exception {
        return evaluate(expressionTree.getRoot());
    }

    private double evaluate(AbsBoundNode node) throws Exception {
        double result;
        if (node == null) {
            throw new Exception("Expression node was null: ");
        }
        switch (node.getBoundNodeType()) {
            case LITERAL:
                BoundLiteralNode boundLiteralNode = (BoundLiteralNode) node;
                if (boundLiteralNode.getSyntaxType() != SyntaxType.NUMBER) {
                    return 0;
                }
                return toDouble(boundLiteralNode.getData().getValue());
            case BINARY_EXPRESSION:
                return buildBinaryComputation((BoundBinaryExpressionNode) node);
            case PARENTHESES_EXPRESSION:
                return evaluate(((BoundParenthesizedExpressionNode) node).getExpression());
            case UNARY_EXPRESSION:
                BoundUnaryExpressionNode unaryExpression = (BoundUnaryExpressionNode) node;
                result = evaluate(unaryExpression.getOperand());
                if (unaryExpression.getOperator() == BoundOperatorType.SUBTRACTION) {
                    result = -result;
                }
                return result;
            default:
                throw new UnsupportedOperationException("Unknown node type: " + node.getBoundNodeType());
        }
    }

    private double buildBinaryComputation(BoundBinaryExpressionNode binaryExpression) throws Exception {
        BoundOperatorType operatorType = binaryExpression.getData();
        double a = evaluate(binaryExpression.getLeft());
        double b = evaluate(binaryExpression.getRight());
        return compute(operatorType, a, b);
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

    private double toDouble(String number) {
        if (number == null) {
            return 0;
        }
        return Double.parseDouble(number);
    }

    private double toDouble(BoundLiteralNode literalNode) {
        Token token = literalNode.getData();
        if (token.getSyntaxType() == SyntaxType.NUMBER) {
            return Double.parseDouble(token.getValue());
        }
        throw new UnsupportedOperationException("Token was not a number: " + token);
    }

}
