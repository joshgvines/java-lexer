package com.javalexer.analyzers;

import com.javalexer.diagnostics.Diagnostics;

import static com.javalexer.enums.TokenType.*;

import com.javalexer.enums.TokenType;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.CodeFilter.*;

public class Lexer {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private char c = ' ';
    private int r = -1;
    private StringReader sr = null;
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int level = 0;
    private int position = 0;

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = fileAsString.replaceAll(MULTI_COMMENT.get(), "");
        fileAsString = fileAsString.replaceAll(SINGLE_COMMENT.get() + NEW_LINE, "");
        sr = new StringReader(fileAsString);
        while ((r = sr.read()) != -1) {
            c = (char) r;
            characterTokenFilter();
        }
        tokens.add(new Token(END, "/0", -1));
        sr.close();
        return tokens;
    }

    private void characterTokenFilter() throws Exception {
        if (c == '"') { appendUntil("\"", STRING);
        } else if (c == '\'') { appendUntil("'", CHAR);
        } else if (Character.isDigit(c)) { appendNumberUntil();
        } else if (c == ' ') { tokens.add(new Token(SPACE, " ", position++));
        } else if (c == '{') { tokens.add(new Token(LBRACE, "{", position++));
        } else if (c == '}') { tokens.add(new Token(RBRACE, "}", position++));
        } else if (c == '=') { tokens.add(new Token(ASSIGN, "=", position++));
        } else if (c == '+') { tokens.add(new Token(PLUS, "+", position++));
        } else if (c == '/') { tokens.add(new Token(SLASH, "/", position++));
        } else if (c == '*') { tokens.add(new Token(STAR, "*", position++));
        } else if (c == '-') { tokens.add(new Token(MINUS, "-", position++));
        } else if (c == '(') { tokens.add(new Token(LPAREN, "(", position++));
        } else if (c == ')') { tokens.add(new Token(RPAREN, ")", position++));
        } else {
            tokens.add(new Token(UNKNOWN, null, position++));
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
        while ((r = sr.read()) != -1) {
            c = (char) r;
            if (key.equals(c) || (";").equals(c)) {
                tokenString.append(c);
                return tokens.add(new Token(tokenType, tokenString.toString(), position++));
            } else if ((" ").equals(c)) {
                tokens.add(new Token(SPACE, " ", position++));
            }
            tokenString.append(c);
        }
        return tokens.add(new Token(tokenType, tokenString.toString(), position++));
    }
}
