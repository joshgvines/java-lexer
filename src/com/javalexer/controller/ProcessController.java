package com.javalexer.controller;

import com.javalexer.analyzers.LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class ProcessController {

    public void runJavaLexer() {
        JavaFileStreamReader jfs = new JavaFileStreamReader();
        String fileAsString = jfs.readFileToString("src/com/javalexer/TestRead.java");
        if (!fileAsString.isEmpty()) {
            runLexicalAnalysis(fileAsString);
        }
    }

    private void runLexicalAnalysis(String fileAsString) {
        LexicalAnalyzer la = new LexicalAnalyzer();
        List<String> tokens = new ArrayList<>();
        String[] splitFileString = la.formatAndSplit(fileAsString);
        if (splitFileString.length > 0) {
            tokens = la.tokenize(splitFileString);
        }

        for (String token : tokens) {
            System.out.println(token);
        }

    }

}
