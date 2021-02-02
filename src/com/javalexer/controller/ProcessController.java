package com.javalexer.controller;

import com.javalexer.analysis.Evaluator;
import com.javalexer.analysis.lexing.Lexer;
import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
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

    private void runParserTree(List<Token> tokens) throws Exception {
        InfixExpressionTree infixExpressionTree = new InfixExpressionTree();
        System.out.println(infixExpressionTree);
        if (infixExpressionTree.buildTree(tokens)) {
            BoundInfixExpressionTree boundInfixExpressionTree = new BoundInfixExpressionTree();
            boundInfixExpressionTree.buildTree(infixExpressionTree.getRoot());
            Evaluator evaluator = new Evaluator(boundInfixExpressionTree);
            System.out.println(evaluator.evaluate());
        }
        Diagnostics.printDiagnostics();
    }

}
