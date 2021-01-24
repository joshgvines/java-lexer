package com.javalexer.analyzers;

import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.enums.CodeFilter;
import com.javalexer.enums.TokenType;
import com.sun.jdi.IntegerValue;

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
            filter();
        }
        tokens.add(new Token(TokenType.END, "/0", -1));
        sr.close();
        return tokens;
    }

    private void filter() throws Exception {
        if (c == '"') { appendUntil("\"", TokenType.STRING);
        } else if (c == '\'') { appendUntil("'", TokenType.CHAR);
        } else if (Character.isDigit(c)) { appendNumberUntil();
        } else if (c == ' ') { tokens.add(new Token(TokenType.SPACE, " ", currentPosition++));
        } else if (c == '{') { tokens.add(new Token(TokenType.LBRACE, "{", currentPosition++));
        } else if (c == '}') { tokens.add(new Token(TokenType.RBRACE, "}", currentPosition++));
        } else if (c == '=') { tokens.add(new Token(TokenType.ASSIGN, "=", currentPosition++));
        } else if (c == '+') { tokens.add(new Token(TokenType.PLUS, "+", currentPosition++));
        } else if (c == '/') { tokens.add(new Token(TokenType.SLASH, "/", currentPosition++));
        } else if (c == '*') { tokens.add(new Token(TokenType.STAR, "*", currentPosition++));
        } else if (c == '-') { tokens.add(new Token(TokenType.MINUS, "-", currentPosition++));
        } else if (c == '(') { tokens.add(new Token(TokenType.LPAREN, "(", currentPosition++));
        } else if (c == ')') { tokens.add(new Token(TokenType.RPAREN, ")", currentPosition++));
        } else {
            tokens.add(new Token(TokenType.UNKNOWN, null, currentPosition++));
            Diagnostics.addLexicalDiagnostic("Found Unknown Token: " + tokens.get(tokens.size()));
        }
    }

    /**
     * You are going to need a peek() function and better error handling eventually, don't forget.
     * @throws Exception
     */
    private void appendNumberUntil() throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        boolean isDouble = false;
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if (!Character.isDigit(c) && c != '.') {
                tokens.add(new Token(TokenType.NUMBER, tokenString.toString(), currentPosition++));
                filter();
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
        tokens.add(new Token(TokenType.NUMBER, tokenString.toString(), currentPosition++));
    }

    private boolean appendUntil(String key, TokenType tokenType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(c);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if (key.equals(c) || (";").equals(c)) {
                tokenString.append(c);
                return tokens.add(new Token(tokenType, tokenString.toString(), currentPosition++));
            } else if ((" ").equals(c)) {
                tokens.add(new Token(TokenType.SPACE, " ", currentPosition++));
            }
            tokenString.append(c);
        }
        return tokens.add(new Token(tokenType, tokenString.toString(), currentPosition++));
    }
}
