package com.javalexer.analyzers;

import com.javalexer.enums.CodeFilter;
import com.javalexer.enums.TokenType;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private char c = ' ';
    private int r = -1;
    private StringReader sr = null;
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int level = 0;
    private int currentPosition = 0;

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = fileAsString.replaceAll(CodeFilter.MULTI_COMMENT.get(), "");
        fileAsString = fileAsString.replaceAll(CodeFilter.SINGLE_COMMENT.get() + NEW_LINE, "");
        sr = new StringReader(fileAsString);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if (c == ' ') tokens.add(new Token(TokenType.WHITESPACE, " ", currentPosition++));
            if (c == '{') tokens.add(new Token(TokenType.LEFT_BRACE, "{", currentPosition++));
            if (c == '}') tokens.add(new Token(TokenType.RIGHT_BRACE, "}", currentPosition++));
            if (c == '=') tokens.add(new Token(TokenType.ASSIGNMENT, "=", currentPosition++));
            if (c == '+') tokens.add(new Token(TokenType.PLUS, "+", currentPosition++));
            if (c == '/') tokens.add(new Token(TokenType.PLUS, "+", currentPosition++));
            if (c == '*') tokens.add(new Token(TokenType.PLUS, "+", currentPosition++));
            if (c == '-') tokens.add(new Token(TokenType.MINUS, "-", currentPosition++));
            if (c == '(') tokens.add(new Token(TokenType.LEFT_PAREN, "(", currentPosition++));
            if (c == ')') tokens.add(new Token(TokenType.RIGHT_PAREN, ")", currentPosition++));
            if (c == '"') appendUntil("\"", TokenType.STRING);
            if (c == '\'') appendUntil("'", TokenType.CHAR);
            if (Character.isDigit(c)) appendNumberUntil(" ", TokenType.NUMBER);
        }
        sr.close();
        return tokens;
    }

    private boolean appendNumberUntil(String temp, TokenType tokenType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if ((c+"").equals(" ")) {
                tokens.add(new Token(TokenType.NUMBER, tokenString.toString(), currentPosition++));
                return tokens.add(new Token(TokenType.WHITESPACE, " ", currentPosition++));
            } else if (!Character.isDigit(c)) {
                return tokens.add(new Token(TokenType.NUMBER, tokenString.toString(), currentPosition++));
            }
            tokenString.append(c);
        }
        return tokens.add(new Token(TokenType.NUMBER, tokenString.toString(), currentPosition++));
    }

    private boolean appendUntil(String temp, TokenType tokenType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if ((temp.equals(c + "")) || (";".equals(c + ""))) {
                tokenString.append(c);
                return tokens.add(new Token(tokenType, tokenString.toString(), currentPosition++));
            } else if (" ".equals(c + "")) {
                tokens.add(new Token(TokenType.WHITESPACE, " ", currentPosition++));
            }
            tokenString.append(c);
        }
        return tokens.add(new Token(tokenType, tokenString.toString(), currentPosition++));
    }
}
