package com.javalexer.parsing;

import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;

import java.util.List;

public class Parser {

    public void parse(List<Token> tokens) {

        /*
        ├──
        │
        └──
         */
        CodeTree codeTree = new CodeTree();

        for (Token token : tokens) {
            if (token.tokenType.equals(TokenType.NUMBER)) {
                codeTree.add(new Node());
            }
            if (isOperator(token.token)) {
                codeTree.add(new Node(token.token, new Node(), null));
            }
        }

        Node anode = codeTree.root;
        while (codeTree.currentNode.right != null) {
            anode = anode.right;
            System.out.println(anode.token);
        }

    }
    private boolean isOperator(String c) {
        return (c.equals('*') || c.equals('/') || c.equals('+') || c.equals('-') || c.equals('%'));
    }

    private class Node {
        String token;
        Node left;
        Node right;

        Node() {
        }

        Node(String token, Node right, Node left) {
            this.token = token;
            this.right = right;
            this.left = left;
        }
    }

    private class CodeTree {
        Node root;
        Node currentNode;

        CodeTree() {
            root = new Node();
            currentNode = root;
        }

        void add(Node node) {
            currentNode.right = node;
            currentNode = node;
        }
    }

}
