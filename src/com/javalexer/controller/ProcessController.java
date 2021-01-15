package com.javalexer.controller;

import com.javalexer.analyzers.LexicalAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcessController {

    public void runJavaLexer() throws IOException {
        JavaFileStreamReader jfs = new JavaFileStreamReader();
        String fileAsString = jfs.readFileToString("src/com/javalexer/TestRead.java");
        if (!fileAsString.isEmpty()) {
            runLexicalAnalysis(fileAsString);
        }
    }

    private void runLexicalAnalysis(String fileAsString) throws IOException {
        LexicalAnalyzer la = new LexicalAnalyzer();
        List<String> tokens = la.tokenize(fileAsString);

        for (String token : tokens) {
            System.out.println(token);
        }

    }

}