package com.javalexer.analyzers;

import com.javalexer.enums.CodeFilter;
import com.javalexer.enums.CodeType;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The LexicalAnalyzer class will break down each block into java code tokens.
 */
public class LexicalAnalyzer {

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String BLANK_SPACE = "\\s";

    private static String[] splitFileArray;

    private char c = ' ';
    private int r = -1;
    private StringReader sr = null;
    private StringBuilder token;
    private List<String> tokens = new ArrayList<>();
    private int level = 0;

    private String[] formatAndSplit(String fileAsString) {
        // Remove Multi Line Comments
        fileAsString = fileAsString.replaceAll("(?s)/\\*.*?\\*/", "");
        // Remove Single Line Comments
        fileAsString = fileAsString.replaceAll("//(.*?)" + NEW_LINE, "");
        // Remove White Space and New Line Characters
        fileAsString = fileAsString.trim().replaceAll(BLANK_SPACE + "|" + NEW_LINE, "");
        // Split by braces and semicolons, but ignore braces inside literals inside all double and single quotes.
        return fileAsString.split("(" + CodeFilter.FIND_BRACES.get() + "|" + CodeFilter.FIND_SEMI_COL.get() + ")"
                + CodeFilter.IGNORE_DOUBLE_QUOTES.get()
                + CodeFilter.IGNORE_SINGLE_QUOTES.get());
    }

    /**
     * @param fileAsString
     * @return
     */
    public List<String> tokenize(String fileAsString) throws IOException {
        String[] splitFileArray = formatAndSplit(fileAsString);
        for (String codeString : splitFileArray) {
            sr = new StringReader(codeString);
            readCode();
        }
        return tokens;
    }

    public void readCode() throws IOException {
        while ((r = sr.read()) != -1) {
            c = (char) r;

            checkCharacterLiterals();
            checkIntegerLiterals();

            if (c == '=') {
                tokens.add(CodeType.ASSIGNMENT + ", " + c);
            } else if (c == '{') {
                System.out.println(level);
                level++;
            } else if (c == '}') {
                level--;
            }
//            System.out.print(c);
        }
        sr.close();
        level = 0;
    }

    private boolean checkCharacterLiterals() throws IOException {
        if (c == '"' || c == '\'') {
            appendUntil(c);
            return true;
        }
        return false;
    }

    private boolean checkIntegerLiterals() throws IOException {
        if (Character.isDigit(c)) {
            appendUntil(';');
            return true;
        }
        return false;
    }

    private void appendUntil(char temp) throws IOException {
        token = new StringBuilder();
        token.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            token.append(c);
            if (c == temp) {
                if (temp == '\'') {
                    tokens.add(CodeType.CHAR + ", " + token.toString());
                } else if (temp == '"') {
                    tokens.add(CodeType.STRING + ", " + token.toString());
                }
                return;
            }
        }

    }

}
