package com.javalexer.controller;

import com.javalexer.analyzers.LexicalAnalyzer;
import com.javalexer.analyzers.Token;
import com.javalexer.parsing.Parser;

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

    private void parseTokens(List<Token> tokens) {
        Parser parser = new Parser();
        parser.parse(tokens);
    }

}
