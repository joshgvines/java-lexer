package com.javalexer.analyzers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The LexicalAnalyzer class will break down each block into java code tokens.
 */
public class LexicalAnalyzer {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String BLANK_SPACE = "\\s";

    // Regex Expressions
    private static final String FIND_BRACES = "(?<=\\{)|(?<=\\})";
    private static final String FIND_SEMI_COL = "(?<=\\;)";
    private static final String IGNORE_DOUBLE_QUOTES = "(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)";
    private static final String IGNORE_SINGLE_QUOTES = "(?=([^\\\']*\\\'[^\\\']*\\\')*[^\\\']*$)";
    private static String[] splitFileArray;

    public String[] formatAndSplit(String fileAsString) {
        // Remove Multi Line Comments
        fileAsString = fileAsString.replaceAll("(?s)/\\*.*?\\*/", "");
        // Remove Single Line Comments
        fileAsString = fileAsString.replaceAll("//(.*?)" + NEW_LINE, "");
        // Remove White Space and New Line Characters
        fileAsString = fileAsString.trim().replaceAll(BLANK_SPACE + "|" + NEW_LINE, "");
        // Split by braces and semicolons, but ignore braces inside literals inside all double and single quotes.
        return fileAsString.split("(" + FIND_BRACES + "|" + FIND_SEMI_COL + ")"
                + IGNORE_DOUBLE_QUOTES
                + IGNORE_SINGLE_QUOTES);
    }

    public List<String> tokenize(String[] splitFileArray) {
        List<String> tokens = new ArrayList<>();
        int level = 0;
        // Node Builder Loop
        for (String codeString : splitFileArray) {
            if (codeString.contains("{")) {
                for (char i : codeString.toCharArray())

                level++;
            } else if (codeString.contains("}")) {
                level--;
            }
            tokens.add(codeString);
        }
        return tokens;
    }

}
