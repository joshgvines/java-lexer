package com.javalexer.controller;

import com.javalexer.analysis.Evaluator;
import com.javalexer.analysis.lexing.Lexer;
import com.javalexer.analysis.lexing.Token;
import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.analysis.parsing.trees.InfixExpressionTree;

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

    private void runLexicalAnalysis(String stringToLex) throws Exception {
        Lexer lex = new Lexer();
        runParserTree(lex.lex(stringToLex));
    }

    private void runParserTree(List<Token> tokens) {
        InfixExpressionTree infixExpressionTree = new InfixExpressionTree();
        System.out.println(infixExpressionTree);
        if (infixExpressionTree.buildTree(tokens)) {
            Evaluator evaluator = new Evaluator(infixExpressionTree);
            System.out.println(evaluator.evaluate());
        }
        Diagnostics.printDiagnostics();
    }

}
