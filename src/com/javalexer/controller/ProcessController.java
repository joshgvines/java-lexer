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

    private void runLexicalAnalysis(String fileAsString) throws Exception {
        Lexer la = new Lexer();
        runParserTree(la.lex(fileAsString));
    }

    private void runParserTree(List<Token> tokens) throws Exception {
        InfixExpressionTree infixExpressionTree = new InfixExpressionTree();
        if (infixExpressionTree.buildTree(tokens)) {
            Evaluator evaluator = new Evaluator(infixExpressionTree);
            evaluator.evaluate();
        }
        Diagnostics.printDiagnostics();
    }

}
