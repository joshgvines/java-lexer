package com.javalexer;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.enums.TokenType;
import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperatorNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer();
        List<Token> tokenList = la.lex("1 + 2 + 3 - 4 + 43");

        InfixParser infixParser = new InfixParser(tokenList);
        MyNode root = infixParser.parse();

        traverse(root);

    }

    static String indent = " ";
    private static void traverse(MyNode root) {
        List<MyNode> children = root.getChildren();
//        System.out.println(root.getData());
        if (children != null){
            System.out.println(indent + root.getData());
            for (MyNode node : children) {
                indent = indent + " ";
                if ( node.getData().type == TokenType.NUMBER) {
                    System.out.println(indent + node);
                }
                traverse(node);
            }
        }
    }

}
