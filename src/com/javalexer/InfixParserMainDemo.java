package com.javalexer;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperatorNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer();
        List<Token> tokenList = la.lex("1 + 2 + 3");

        InfixParser infixParser = new InfixParser(tokenList);
        MyNode root = infixParser.parse();

        System.out.println(root.getData());
        traverse(root);

    }

    static String indent = " ";
    private static void traverse(MyNode root) {
        List<MyNode> children = root.getChildren();
//        System.out.println(root.getData());
        if (children != null){
            for (MyNode node : children) {
                indent = indent + " ";
                System.out.println(indent + node);
                traverse(node);
            }
        }
    }

}
