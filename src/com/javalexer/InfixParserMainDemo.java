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
        List<Token> tokenList = la.lex("2 - 3 + 4 + 5 + 43");

        InfixParser infixParser = new InfixParser(tokenList);
        AbsBinaryNode root = infixParser.parse();

        System.out.println("\nInfix:");
        traverseInfix(root);
        System.out.println("\nPostfix:");
        traversePostfix(root);
//        System.out.println("\nPrefix:");
//        traversePrefix(root);
    }

    /**
     * Inorder traversal output function.
     *
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
     * Postorder traversal output function.
     *
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
     * Preorder traversal output function.
     * Step 1: Reverse the infix expression i.e A+B*C will become C*B+A. Note while reversing each ‘(‘ will become ‘)’ and each ‘)’ becomes ‘(‘.
     * Step 2: Obtain the postfix expression of the modified expression i.e CB*A+.
     * Step 3: Reverse the postfix expression. Hence in our example prefix is +A*BC.
     * TODO: I may not implement a reversed tree for prefix demo.
     * @param node
     */
    private static void traversePrefix(AbsBinaryNode node) {
//        if (node != null) {
//            System.out.println(node.data());
//            traversePrefix(node.getRight());
//            traversePrefix(node.getLeft());
//
//        }
    }
}
