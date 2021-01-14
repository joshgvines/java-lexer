package com.javalexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String BLANK_SPACE = "\\s";

    // Regex Expressions
    private static final String FIND_BRACES = "(?<=\\{)|(?<=\\})";
    private static final String FIND_SEMI_COL = "(?<=\\;)";
    private static final String IGNORE_DOUBLE_QUOTES = "(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    private static final String IGNORE_SINGLE_QUOTES = "(?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)";
    private static String[] splitFileArray;

    public static void main(String[] args) {
        String fileAsString = "";
        try {
            formatAndSplit(new String(Files.readAllBytes(Paths.get("src/com/javalexer/TestRead.java"))));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int level = 0;

        // Node Builder Loop
        for (String codeString : splitFileArray) {
            if (codeString.contains("{")) {
                level++;
            } else if (codeString.contains("}")) {
                level--;
            }
            System.out.println(codeString);
        }
    }

    public static void formatAndSplit(String fileAsString) {
        // Remove Multi Line Comments
        fileAsString = fileAsString.replaceAll("(?s)/\\*.*?\\*/", "");
        // Remove Single Line Comments
        fileAsString = fileAsString.replaceAll("//(.*?)"+NEW_LINE, "");
        // Remove White Space and New Line Characters
        fileAsString = fileAsString.trim().replaceAll(BLANK_SPACE+"|"+ NEW_LINE, "");
        // Split by braces and semicolons, but ignore braces inside literals inside all double and single quotes.
        splitFileArray = fileAsString.split("(" + FIND_BRACES + "|" + FIND_SEMI_COL + ")"
                + IGNORE_DOUBLE_QUOTES
                + IGNORE_SINGLE_QUOTES);
    }
}
