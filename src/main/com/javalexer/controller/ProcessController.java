package main.com.javalexer.controller;

import main.com.javalexer.analysis.Evaluator;
import main.com.javalexer.analysis.lexing.Lexer;
import main.com.javalexer.analysis.lexing.Token;
import main.com.javalexer.analysis.parsing.trees.BoundInfixExpressionTree;
import main.com.javalexer.diagnostics.Diagnostics;
import main.com.javalexer.analysis.parsing.trees.InfixExpressionTree;

import java.util.List;

/**
 * This controller is temporary for experimentation
 */
public class ProcessController {

//    public void runJavaFromFileLexer(String filePath) throws Exception {
//        JavaFileStreamReader jfs = new JavaFileStreamReader();
//        String fileAsString = jfs.readFileToString(filePath);
//        if (!fileAsString.isEmpty()) {
//            runLexicalAnalysis(fileAsString);
//        }
//    }

    public void runJavaLexer(String toLex, boolean readTokens) throws Exception {
        runLexicalAnalysis(toLex, readTokens);
    }

    public void runLexicalAnalysis(String stringToLex, boolean readTokens) throws Exception {
        Diagnostics.clear();
        Lexer lex = new Lexer();
        runParser(lex.lex(stringToLex), readTokens);
    }

    public void runParser(List<Token> tokens, boolean readTokens) throws Exception {
        InfixExpressionTree infixExpressionTree = new InfixExpressionTree();
        if (infixExpressionTree.buildTree(tokens, readTokens)) {
            runTypeCheck(infixExpressionTree);
        }
    }

    public void runTypeCheck(InfixExpressionTree infixExpressionTree) throws Exception {
        BoundInfixExpressionTree boundInfixExpressionTree = new BoundInfixExpressionTree();
        if (boundInfixExpressionTree.buildTree(infixExpressionTree.getRoot())) {
            runEvaluator(boundInfixExpressionTree);
        }
    }

    public void runEvaluator(BoundInfixExpressionTree boundInfixExpressionTree) throws Exception {
        Evaluator evaluator = new Evaluator(boundInfixExpressionTree);
        System.out.println("ANS= " + evaluator.evaluate());
        Diagnostics.printDiagnostics();
    }

}
