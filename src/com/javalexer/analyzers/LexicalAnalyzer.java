package com.javalexer.analyzers;

import com.javalexer.enums.CodeFilter;
import com.javalexer.enums.TokenType;

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
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int level = 0;

    private String[] formatAndSplit(String fileAsString) {
        // Remove Multi Line Comments
        fileAsString = fileAsString.replaceAll("(?s)/\\*.*?\\*/", "");
        // Remove Single Line Comments
        fileAsString = fileAsString.replaceAll("//(.*?)" + NEW_LINE, "");
        // Remove White Space and New Line Characters
        //fileAsString = fileAsString.trim().replaceAll(BLANK_SPACE + "|" + NEW_LINE, "");
        // Split by braces and semicolons, but ignore braces inside literals inside all double and single quotes.
        return fileAsString.split("(" + CodeFilter.FIND_BRACES.get() + "|" + CodeFilter.FIND_SEMI_COL.get() + ")"
                + CodeFilter.IGNORE_DOUBLE_QUOTES.get()
                + CodeFilter.IGNORE_SINGLE_QUOTES.get());
    }

    public List<Token> tokenize(String fileAsString) throws Exception {
        String[] splitFileArray = formatAndSplit(fileAsString);
        for (String codeString : splitFileArray) {
            sr = new StringReader(codeString);
            readCode();
        }
        return tokens;
    }

    public void readCode() throws Exception {
        while ((r = sr.read()) != -1) {
            c = (char) r;

            isTextLiteral();

            if (isOperator()) {

            } else if (Character.isDigit(c)) {
                appendNumberUntil();
            } else if (c == '{') {
                level++;
            } else if (c == '}') {
                level--;
            }
        }
        sr.close();
        level = 0;
    }

    private boolean isOperator() {
        if (c == '=') {
            return tokens.add(new Token(TokenType.ASSIGNMENT, "="));
        } else if (c == '+') {
            return tokens.add(new Token(TokenType.PLUS, "+"));
        } else if (c == '-') {
            return tokens.add(new Token(TokenType.MINUS, "-"));
        } else if (c == '(') {
            return tokens.add(new Token(TokenType.LEFT_PAREN, "("));
        } else if (c == ')') {
            return tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
        }
        return false;
    }

    private boolean isTextLiteral() throws Exception {
        if (c == '"') {
            return appendUntil("\"", TokenType.STRING);
        } else if (c == '\'') {
            return appendUntil("'", TokenType.CHAR);
        }
        return false;
    }

    private boolean appendUntil(String temp, TokenType tokenType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            tokenString.append(c);
            if (temp.equals(c + "")) {
                return tokens.add(new Token(tokenType, tokenString.toString()));
            }
        }
        throw new Exception("Expected " + temp);
    }

    private boolean appendNumberUntil() throws IOException {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if (!Character.isDigit(c)) {
                return tokens.add(new Token(TokenType.INT, tokenString.toString()));
            }
            tokenString.append(c);
        }
        return false;
    }

}
