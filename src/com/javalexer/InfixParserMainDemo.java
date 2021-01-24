package com.javalexer;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.AbsBinaryNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;

/**
 * Note: still learning, these solutions could be MISLEADING or WRONG!!!
 */
public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer();
        //List<Token> tokenList = la.lex("50 - 43 + 90 - 30 + 54");
        List<Token> tokenList = la.lex("2 - 3 + 4 + 5");

        InfixParser infixParser = new InfixParser(tokenList);
        AbsBinaryNode root = infixParser.parse();

        System.out.println("\nInfix:");
        traverseInfix(root);
        System.out.println("\nPostfix:");
        traversePostfix(root);
        System.out.println("\nPrefix:");
        traversePrefix(root);
    }

    /**
     * Inorder
     * @param node
     */
    private static void traverseInfix(AbsBinaryNode node) {
        if (node != null) {
            traverseInfix(node.getLeft());
            System.out.println(node.data());
            traverseInfix(node.getRight());
        }
    }

    /**
     * ...
     * @param node
     */
    private static void traversePostfix(AbsBinaryNode node) {
        if (node != null) {
            traversePostfix(node.getLeft());
            traversePostfix(node.getRight());
            System.out.println(node.data());
        }
    }

    /**
     * ...
     * @param node
     */
    private static void traversePrefix(AbsBinaryNode node) {
        if (node != null) {
            System.out.println(node.data());
            traversePrefix(node.getRight());
            traversePrefix(node.getLeft());

        }
    }
}
