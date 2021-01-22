package com.javalexer.parsing;

import java.util.Stack;

public class ExperimentalTree {

    // Function to create new node
    public BinaryNode newNode(char c) {
        BinaryNode n = new BinaryNode();
        n.data = c;
        n.left = n.right = null;
        return n;
    }

    public BinaryNode build(String s) {
        Stack<BinaryNode> stN = new Stack<>();
        Stack<Character> stC = new Stack<>();
        BinaryNode t, t1, t2;

        // Prioritising the operators
        int[] p = new int[123];
        p['+'] = p['-'] = 1;
        p['/'] = p['*'] = 2;
        p['^'] = 3;
        p[')'] = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {

                // Push '(' in char stack
                stC.add(s.charAt(i));
            }

            // Push the operands in node stack
            else if (Character.isDigit(s.charAt(i))) {
                t = newNode(s.charAt(i));
                stN.add(t);
            } else if (p[s.charAt(i)] > 0) {

                // If an operator with lower or
                // same associativity appears
                while (
                        !stC.isEmpty() && stC.peek() != '('
                                && ((s.charAt(i) != '^' && p[stC.peek()] >= p[s.charAt(i)])
                                || (s.charAt(i) == '^'
                                && p[stC.peek()] > p[s.charAt(i)]))) {

                    // Get and remove the top element
                    // from the character stack
                    t = newNode(stC.peek());
                    stC.pop();

                    // Get and remove the top element
                    // from the node stack
                    t1 = stN.peek();
                    stN.pop();

                    // Get and remove the currently top
                    // element from the node stack
                    t2 = stN.peek();
                    stN.pop();

                    // Update the tree
                    t.left = t2;
                    t.right = t1;

                    // Push the node to the node stack
                    stN.add(t);
                }

                // Push s[i] to char stack
                stC.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                while (!stC.isEmpty() && stC.peek() != '(') {
                    t = newNode(stC.peek());
                    stC.pop();
                    t1 = stN.peek();
                    stN.pop();
                    t2 = stN.peek();
                    stN.pop();
                    t.left = t2;
                    t.right = t1;
                    stN.add(t);
                }
                stC.pop();
            }
        }
        t = stN.peek();
        return t;
    }

    public void inorder(BinaryNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public double evaluate(BinaryNode rootNode) {
        double result;
        if (rootNode == null) {
            result = 0;
        } else if (rootNode.isLeaf()) {
            char var = rootNode.data;
            result = Double.valueOf(var);
        } else {
            double firstOperand = evaluate(rootNode.left);

            double secondOperand = evaluate(rootNode.right);
            result = compute(' ', firstOperand, secondOperand);
        }
        return result;
    }

    public void postorder(BinaryNode root) {
        if (root != null) {
            postorder(root.right);
            postorder(root.left);
            System.out.print(root.data);
        }
    }

    private double compute(char operator, double a, double b) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '%': return a % b;
            case '/':
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }


}