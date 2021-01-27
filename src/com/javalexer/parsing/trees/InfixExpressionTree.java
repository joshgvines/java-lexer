package com.javalexer.parsing.trees;

import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.AbsBinaryNode;
import com.javalexer.parsing.nodes.NumberNode;
import com.javalexer.parsing.nodes.ParenthesesExpressionNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;

public class InfixExpressionTree {

    private AbsBinaryNode root;

    public InfixExpressionTree() {
        this.root = null;
    }

    public InfixExpressionTree(AbsBinaryNode root) {
        this.root = root;
    }

    public boolean buildTree(List<Token> tokenList) {
        InfixParser parser = new InfixParser(tokenList);
        root = parser.parseForRoot();
        return (root != null);
    }

    public boolean isLeaf(AbsBinaryNode absBinaryNode) {
        return (absBinaryNode instanceof NumberNode);
    }

    public boolean isParentheses(AbsBinaryNode absBinaryNode) {
        return (absBinaryNode instanceof ParenthesesExpressionNode);
    }

    public double evaluate() {
        return evaluate(root);
    }

    private double evaluate(AbsBinaryNode node) {
        double result;
        if (node == null) {
            result = 0;
        } else if (isParentheses(node)) {
            result = evaluate(node.getExpression());
        } else if (isLeaf(node)) {
            result = Double.parseDouble(node.data().value);
        } else {
            double a = evaluate(node.getLeft());
            double b = evaluate(node.getRight());
            result = compute(node.data(), a, b);
        }
        return result;
    }

    private double compute(Token operator, double a, double b) {
        switch (operator.type) {
            case PLUS: return a + b;
            case MINUS: return a - b;
            case STAR: return a * b;
            case FORWARD_SLASH:
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

}
