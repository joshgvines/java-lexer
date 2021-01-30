package com.javalexer.analysis;

import com.javalexer.analysis.lexing.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.analysis.parsing.nodes.AbsNode;
import com.javalexer.analysis.parsing.trees.InfixExpressionTree;

public class Evaluator {

    private InfixExpressionTree expressionTree;

    public Evaluator(InfixExpressionTree expressionTree) {
        this.expressionTree = expressionTree;
    }

    public double evaluate() {
        return evaluate(expressionTree.getRoot());
    }

    private double evaluate(AbsNode node) {
        double result;
        if (node == null) {
            result = 0;
        } else if (expressionTree.isUnary(node)) {
            result = evaluate(node.getExpression());
            if (node.data().getType() == TokenType.MINUS) {
                result = -result;
            }
        } else if (expressionTree.isParentheses(node)) {
            result = evaluate(node.getExpression());
        } else if (expressionTree.isLeaf(node)) {
            result = Double.parseDouble(node.data().getValue());
        } else {
            double a = evaluate(node.getLeft());
            double b = evaluate(node.getRight());
            result = compute(node.data(), a, b);
        }
        return result;
    }

    private double compute(Token operator, double a, double b) {
        switch (operator.getType()) {
            case PLUS: return a + b;
            case MINUS: return a - b;
            case STAR: return a * b;
            case MODULO: return a % b;
            case FORWARD_SLASH:
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

}
