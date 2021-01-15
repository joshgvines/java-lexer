package com.javalexer.controller;

import com.javalexer.analyzers.LexicalAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JavaFileStreamReader {

    public String readFileToString(String filePath) {
        String fileAsString = "";
        try {
            fileAsString = new String(Files.readAllBytes(Paths.get(filePath)));
            new LexicalAnalyzer().formatAndSplit(fileAsString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileAsString;
    }


}
