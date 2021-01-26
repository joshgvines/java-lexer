package com.javalexer;

import com.javalexer.analyzers.Lexer;
import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.nodes.AbsBinaryNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: Still learning, these solutions could be MISLEADING or WRONG!!!
 */
public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {
        String expression = "2 - 3 * 4 + 5 + 43";
        Lexer la = new Lexer();
        List<Token> tokenList = la.lex(expression);

        InfixParser infixParser = new InfixParser(tokenList);
        AbsBinaryNode root = infixParser.parseForRoot();

        System.out.println("\nInfix:");
        traverseInfix(root);
        System.out.println("\nPostfix:");
        traversePostfix(root);

        System.out.println("\nPrefix:");
        infixParser = new InfixParser(reverseForPrefixDemo(tokenList));
        AbsBinaryNode rootForPrefix = infixParser.parseForRoot();
        traversePrefix(rootForPrefix);
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
     * I have noticed MANY of the online infix to prefix converters are incorrect.
     * Here is an example of one I use to check my output, which I believe to be correct:
     * https://www.web4college.com/converters/infix-to-postfix-prefix.php
     * @param node
     */
    private static void traversePrefix(AbsBinaryNode node) {
        if (node != null) {
            System.out.println(node.data());
            traversePrefix(node.getRight());
            traversePrefix(node.getLeft());
        }
    }

    private static List<Token> reverseForPrefixDemo(List<Token> tokens) {
        List<Token> reversedTokens = new ArrayList<>();
        Token tempEnd = null;
        for (int i = tokens.size()-1; i >= 0; i--) {
            Token t = tokens.get(i);
            if (t.type != TokenType.END) {
                reversedTokens.add(t);
            } else {
                tempEnd = t;
            }
        }
        reversedTokens.add(tempEnd);
        return reversedTokens;
    }
}
