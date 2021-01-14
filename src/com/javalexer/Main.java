package com.javalexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String BR = System.getProperty("line.separator");

    public static void main(String[] args) {
        String fileAsString = "";
        try {
            fileAsString = new String(Files.readAllBytes(Paths.get("src/com/javalexer/TestRead.java")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        fileAsString = fileAsString.replaceAll(BR, "");

        // Split by braces, but ignore inside all double and single quotes.
        String[] splitFileArray = fileAsString.split(
                "((?=\\})|(?=\\{))" +
                        "(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)" +
                        "(?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)");

        int level = 0;

        // Node Builder Loop
        for (String codeString : splitFileArray) {

            if (codeString.contains("{")) {
                level++;
            } else if (codeString.contains("}")) {
                level--;
            }
            if (codeString.contains(";")) {
                int remove = 0;
                if (level > 0) {
                    remove = 1;
                }
                String forLastBlock = codeString.substring(remove, codeString.lastIndexOf(";")+1);
                //codeString.replace(forLastBlock, "");
            }

        }
    }
}
