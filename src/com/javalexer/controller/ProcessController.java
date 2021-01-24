package com.javalexer.controller;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.nodes.MyNode;
import com.javalexer.parsing.nodes.OperandNode;
import com.javalexer.parsing.parsers.ExperimentalParser;
import com.javalexer.parsing.parsers.InfixParser;
import com.javalexer.parsing.parsers.Parser;

import java.util.List;

public class ProcessController {

    public void runJavaFromFileLexer(String filePath) throws Exception {
        JavaFileStreamReader jfs = new JavaFileStreamReader();
        String fileAsString = jfs.readFileToString(filePath);
        if (!fileAsString.isEmpty()) {
            runLexicalAnalysis(fileAsString);
        }
    }

    public void runJavaLexer(String toLex) throws Exception {
        runLexicalAnalysis(toLex);
    }

    private void runLexicalAnalysis(String fileAsString) throws Exception {
        LexicalAnalyzer la = new LexicalAnalyzer();
        parseTokens(la.lex(fileAsString));
    }

    /**
     * YES!!!
     * @param tokens
     * @throws Exception
     */
    private void parseTokens(List<Token> tokens) throws Exception {
        InfixParser ip = new InfixParser(tokens);
        MyNode root = ip.parse();

        System.out.println(infixTraversal(root));
    }

    private double infixTraversal(MyNode node) {
        double result;
        if (node == null) {
            result = 0;
        } else if (node instanceof OperandNode) {
            result = Double.parseDouble(node.data().value);
        } else {
            double a = infixTraversal(node.getLeft());
            double b = infixTraversal(node.getRight());
            result = evaluate(node.data(), a, b);
        }
        return result;
    }

    private double evaluate(Token operator, double a, double b) {
        switch (operator.type) {
            case PLUS:
                return a + b;
            case MINUS:
                return a - b;
            case STAR:
                return a * b;
            case SLASH:
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
    }

}
