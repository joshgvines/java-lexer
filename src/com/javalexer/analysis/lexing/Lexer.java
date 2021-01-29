package com.javalexer.analysis.lexing;

import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.enums.TokenType;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.TokenType.*;
import static com.javalexer.enums.CodeFilter.*;

public class Lexer {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private char c = ' ';
    private int charInt = -1;
    private StringReader sr = null;
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int position = 0;

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = removeComments(fileAsString);
        sr = new StringReader(fileAsString);
        while ((charInt = sr.read()) != -1) {
            c = (char) charInt;
            characterTokenFilter();
        }
        tokens.add(new Token(END, "/0", -1));
        sr.close();
        return tokens;
    }

    private String removeComments(String fileAsString) {
        fileAsString = fileAsString.replaceAll(MULTI_COMMENT.get(), "");
        return fileAsString.replaceAll(SINGLE_COMMENT.get() + NEW_LINE, "");
    }

    private void characterTokenFilter() throws Exception {
        switch (c) {
            case '"': appendUntil("\"", STRING); break;
            case '\'': appendUntil("'", CHAR); break;
            case ' ': tokens.add(new Token(WHITESPACE, " ", position++)); break;
            case '{': tokens.add(new Token(OPEN_BRACE, "{", position++)); break;
            case '}': tokens.add(new Token(CLOSE_BRACE, "}", position++)); break;
            case '=': tokens.add(new Token(ASSIGNMENT, "=", position++)); break;
            case '+': tokens.add(new Token(PLUS, "+", position++)); break;
            case '/': tokens.add(new Token(FORWARD_SLASH, "/", position++)); break;
            case '*': tokens.add(new Token(STAR, "*", position++)); break;
            case '%': tokens.add(new Token(MODULO, "%", position++)); break;
            case '-': tokens.add(new Token(MINUS, "-", position++)); break;
            case '(': tokens.add(new Token(OPEN_PAREN, "(", position++)); break;
            case ')': tokens.add(new Token(CLOSE_PAREN, ")", position++)); break;
            default:
                if (Character.isDigit(c)) {
                    appendNumberUntil();
                } else {
                    tokens.add(new Token(UNKNOWN, null, position++));
                    Diagnostics.addLexicalDiagnostic("Found Unknown Token: " + tokens.get(tokens.size()));
                }
        }
    }

    /**
     * You are going to need a peek() function and better error handling eventually, don't forget.
     *
     * @throws Exception
     */
    private void appendNumberUntil() throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        boolean isDouble = false;
        while ((charInt = sr.read()) != -1) {
            c = (char) charInt;
            if (!Character.isDigit(c) && c != '.') {
                tokens.add(new Token(NUMBER, tokenString.toString(), position++));
                characterTokenFilter();
                return;
            }
            if (c == '.') {
                isDouble = true;
            }
            if ((tokenString.length() > 8 && !isDouble)
                    || (tokenString.length() > 12 && isDouble)) {
                throw new Exception("Number was to large");
            }
            tokenString.append(c);
        }
        tokens.add(new Token(NUMBER, tokenString.toString(), position++));
    }

    private boolean appendUntil(String key, TokenType tokenType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((charInt = sr.read()) != -1) {
            c = (char) charInt;
            if (key.equals(c) || (";").equals(c)) {
                tokenString.append(c);
                return tokens.add(new Token(tokenType, tokenString.toString(), position++));
            } else if ((" ").equals(c)) {
                tokens.add(new Token(WHITESPACE, " ", position++));
            }
            tokenString.append(c);
        }
        return tokens.add(new Token(tokenType, tokenString.toString(), position++));
    }
}
