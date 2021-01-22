package com.javalexer.parsing;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;

import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.TokenType.*;
import static com.javalexer.enums.TokenType.MINUS;

public class ExperimentalParser {

    List<BinaryNode> nodes = new ArrayList<>();
    List<BinaryNode> myNodes = new ArrayList<>();

    public void parse(List<Token> tokens) throws Exception {
        List<Token> vas = new ArrayList<>();
        List<Token> ops = new ArrayList<>();
        for (Token token : tokens) {
            TokenType type = token.tokenType;
            if (token.tokenType == NUMBER) {
                vas.add(token);
            } else if (type == STAR || type == SLASH || type == PLUS || type == MINUS) {
                ops.add(token);
            }
        }
        buildNodes(vas, ops);
        printAll();
    }

    private void buildNodes(List<Token> vas, List<Token> ops) throws Exception {
        Token prevOperator = null, thisOperator, nextOperator, thisValue, nextValue;
        for (int O = 0, V = 0; O < ops.size(); O++, V++) {
            thisOperator = ops.get(O);
            thisValue = vas.get(V);
            nextOperator = nextValue = null;
            int precedence = precedence(thisOperator);

            if (O+1 < ops.size()) { nextOperator = ops.get(O+1); }
            if (V < vas.size()) { nextValue = vas.get(V+1); }

            if ((nextOperator != null && prevOperator != null
                    && precedence >= precedence(nextOperator)
                    && precedence > precedence(prevOperator))
                    || (O == 0) ||  nextOperator == null) {
                nodes.add(new BinaryNode(thisValue, thisOperator, nextValue, precedence));
            } else if (prevOperator != null && nextOperator != null
                    && precedence <= precedence(prevOperator)
                    && precedence < precedence(nextOperator) /*&& (O+1 != ops.size())*/) {
                nodes.add(new BinaryNode(null, thisOperator, null, precedence));
            } else {
                nodes.add(new BinaryNode(null, thisOperator, nextValue, precedence));
            }
            prevOperator = thisOperator;
        }
    }

    private int precedence(Token token) throws Exception {
        switch (token.tokenType) {
            case SLASH: case STAR: return 1;
            case PLUS: case MINUS: return 0;
            default: return -1;
        }
    }

    private void printAll() throws Exception {
        BinaryNode currentNode = null;
        BinaryNode tempNode, epicNode = null;
        for (BinaryNode node : nodes) {
            if (currentNode == null && node.precedence == 1) {
                currentNode = node;
            } else if (node.precedence == 1) {
                tempNode = decideNode(currentNode, node);
                if (tempNode != null) {
                    currentNode = tempNode;
                }
            }
        }
        for (BinaryNode node : nodes) {
            if (node.precedence == 0) {
                tempNode = decideNode(currentNode, node);
                if (tempNode != null) {
                    currentNode = tempNode;
                }
            }
        }
        bloop(currentNode);
    }

    String test = "|__";
    private String bloop(BinaryNode root) {

        if (root.left != null) {
            if (isLeaf(root.left)) {
                System.out.println(test + "__" + bloop(root.left));
            } else {
                System.out.println(test + bloop(root.left));
            }
        }

        if (root.right != null) {
            if (isLeaf(root.right)) {
                System.out.println(test + "__" + bloop(root.right));
            } else {
                System.out.println(test + bloop(root.right));
            }
        }

        return root.toString();
    }

    private boolean isLeaf(BinaryNode node) {
        return (node.left == null && node.right == null);
    }

    private BinaryNode decideNode(BinaryNode nodeA, BinaryNode nodeB) throws Exception {
        if (nodeA.left == null) {
            nodeA.left = nodeB;
            return nodeA;
        } else if (nodeA.right == null) {
            nodeA.right = nodeB;
            return nodeA;
        } else if (nodeB.right == null) {
            nodeB.right = nodeA;
            return nodeB;
        } else if (nodeB.left == null) {
            nodeB.left = nodeA;
            return nodeB;
        } else {
            //throw new Exception("How???");
        }
        return null;
    }

    private class BinaryNode {

        int precedence = -1;
        boolean isOperator = false;

        Token data;
        BinaryNode left = null;
        BinaryNode right = null;

        BinaryNode(Token left, Token data, Token right) {
            init(left, data, right);
        }

        BinaryNode(Token left, Token data, Token right, int precedence) {
            init(left, data, right);
            this.precedence = precedence;
            isOperator = true;
        }

        private void init(Token left, Token data, Token right) {
            if (right != null) {
                this.right = new BinaryNode(null, right, null);
            }
            if (left != null) {
                this.left = new BinaryNode(null, left, null);
            }
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString() + " :: PREC: " + precedence;
        }

    }

}
