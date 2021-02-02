package com.javalexer.controller;

import com.javalexer.analysis.Evaluator;
import com.javalexer.analysis.lexing.Lexer;
import com.javalexer.analysis.lexing.Token;
import com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.analysis.parsing.trees.InfixExpressionTree;

import java.util.List;

/**
 * This controller is temporary for experimentation
 */
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

    public void runLexicalAnalysis(String stringToLex) throws Exception {
        Lexer lex = new Lexer();
        runParser(lex.lex(stringToLex));
    }

    public void runParser(List<Token> tokens) throws Exception {
        InfixExpressionTree infixExpressionTree = new InfixExpressionTree();
        if (infixExpressionTree.buildTree(tokens)) {
            runTypeCheck(infixExpressionTree);
        }
        Diagnostics.printDiagnostics();
    }

    public void runTypeCheck(InfixExpressionTree infixExpressionTree) throws Exception {
        BoundInfixExpressionTree boundInfixExpressionTree = new BoundInfixExpressionTree();
        if (boundInfixExpressionTree.buildTree(infixExpressionTree.getRoot())) {
            runEvaluator(boundInfixExpressionTree);
        }
    }

    public void runEvaluator(BoundInfixExpressionTree boundInfixExpressionTree) throws Exception {
        Evaluator evaluator = new Evaluator(boundInfixExpressionTree);
        System.out.println(evaluator.evaluate());
    }

}
