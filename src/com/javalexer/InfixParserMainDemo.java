package com.javalexer;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.nodes.OperatorNode;
import com.javalexer.parsing.parsers.InfixParser;

import java.util.List;
import java.util.Spliterator;

import static com.javalexer.enums.TokenType.*;

public class InfixParserMainDemo {

    public static void main(String[] args) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer();
        List<Token> tokenList = la.lex("500 - 43 + 90 - 30 + 54 - 3 + 40000");

        InfixParser infixParser = new InfixParser(tokenList);
        MyNode root = infixParser.parse();

        //traverse(root);
        //System.out.println(infixTraversal(root));
    }

    /*
     *  ├──
     *  │
     *  └──
     */
    static String indent = "└──";
    static String space = "";
    static String indicator = "└──";

    private static void traverse(MyNode node) {

        if (node != null) {


            if (node instanceof OperatorNode) {
                System.out.println(space + indent + node.data());
                space = space + "    ";
            } else {
                System.out.println(space + indicator + node.data());
            }

            MyNode right = node.getRight();
            traverse(right);

            MyNode left = node.getLeft();
            traverse(left);

//            double a = Double.parseDouble(left.data().value);
//            double b = Double.parseDouble(right.data().value);
//            System.out.println(evaluate(root.data(), a, b));
        }

    }




}
