package com.javalexer.parsing;

import com.javalexer.analyzers.Token;

public class ExpressionTree {
    private ExpressionNode root;

    public ExpressionTree(Token rootData) {
        this(rootData, null, null);
    }

    public ExpressionTree(Token rootData, ExpressionTree leftEt, ExpressionTree rightEt) {
//        initializeTree(rootData, leftEt, rightEt);
    }

    void setRootData(Token data) {
        this.root.data = data;
    }

    ExpressionNode getRootNode() {
        if (isEmpty()) {
            throw new NullPointerException("Root data is null");
        }
        return root;
    }

    boolean isEmpty() {
        return root == null;
    }

    double evaluate() {
        return evaluate();
    }

    private double evaluate(ExpressionNode rootNode) {
        double result;
        if (rootNode == null) {
            result = 0;
        } else if (rootNode.isLeaf()) {
            String var = rootNode.getData();
            result = Double.valueOf(var);
        } else {
            double firstOperand = evaluate(rootNode.left);
            double secondOperand = evaluate(rootNode.right);
            String operator = rootNode.getData();
            result = compute(operator, firstOperand, secondOperand);
        }
        return result;
    }

    private double compute(String operator, double a, double b) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "%": return a % b;
            case "/":
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

    private class ExpressionNode {
        Token data;
        ExpressionNode left, right;

        ExpressionNode(Token data) {
            this.data = data;
            right = left = null;
        }

        String getData() {
            return data.token;
        }

        boolean isLeaf() {
            return (right == null && left == null);
        }
    }

    class NodeStackOne {
        private ExpressionNode[] a;
        private int top, m;

        public NodeStackOne(int max) {
            m = max;
            a = new ExpressionNode[m];
            top = -1;
        }

        public void push(ExpressionNode key) {
            a[++top] = key;
        }

        public ExpressionNode pop() {
            return (a[top--]);
        }

        public boolean isEmpty() {
            return (top == -1);
        }
    }

}
