package com.javalexer.analysis.lexing;

import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.enums.SyntaxType;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.SyntaxType.*;
import static com.javalexer.enums.CodeFilter.*;

public class Lexer {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private char _char;
    private int charInt;
    private StringReader sr;
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int position = 0;

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = removeComments(fileAsString);
        sr = new StringReader(fileAsString);
        while ((charInt = sr.read()) != -1) {
            _char = (char) charInt;
            if (!keywordTokenFilter()) {
                characterTokenFilter();
            }
        }
        tokens.add(new Token(END, "/0", -1));
        sr.close();
        return tokens;
    }

    private String removeComments(String fileAsString) {
        fileAsString = fileAsString.replaceAll(MULTI_COMMENT.get(), "");
        return fileAsString.replaceAll(SINGLE_COMMENT.get() + NEW_LINE, "");
    }

    private boolean keywordTokenFilter() throws Exception {
        if (Character.isLetter(_char)) {
            tokenString = new StringBuilder().append(_char);
            while (Character.isLetter(_char) && (charInt = sr.read()) != -1) {
                _char = (char) charInt;
                if (Character.isLetter(_char)) {
                    tokenString.append(_char);
                }
            }
            return buildKeywordToken();
        }
        return false;
    }

    private boolean buildKeywordToken() throws Exception {
        SyntaxType type = KeywordUtil.getKeyword(tokenString.toString());
        if (type == null) {
            return false;
        }
        tokens.add(new Token(type, tokenString.toString(), position++));
        return true;
    }

    private void characterTokenFilter() throws Exception {
        switch (_char) {
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
                if (Character.isDigit(_char)) {
                    appendNumberUntil();
                } else {
                    tokens.add(new Token(UNKNOWN, null, position++));
                    Diagnostics.addLexicalDiagnostic("Found Unknown Token: " + tokens.get(tokens.size() - 1));
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
        tokenString.append(_char);
        boolean isDouble = false;
        while ((charInt = sr.read()) != -1) {
            _char = (char) charInt;
            if (!Character.isDigit(_char) && _char != '.') {
                tokens.add(new Token(NUMBER, tokenString.toString(), position++));
                characterTokenFilter();
                return;
            }
            if (_char == '.') {
                isDouble = true;
            }
            if ((tokenString.length() > 8 && !isDouble)
                    || (tokenString.length() > 12 && isDouble)) {
                throw new Exception("Number was to large");
            }
            tokenString.append(_char);
        }
        tokens.add(new Token(NUMBER, tokenString.toString(), position++));
    }

    private boolean appendUntil(String key, SyntaxType syntaxType) throws Exception {
        tokenString = new StringBuilder();
        tokenString.append(_char);
        while ((charInt = sr.read()) != -1) {
            _char = (char) charInt;
            if (key.equals(_char) || (";").equals(_char)) {
                tokenString.append(_char);
                return tokens.add(new Token(syntaxType, tokenString.toString(), position++));
            } else if ((" ").equals(_char)) {
                tokens.add(new Token(WHITESPACE, " ", position++));
            }
            tokenString.append(_char);
        }
        return tokens.add(new Token(syntaxType, tokenString.toString(), position++));
    }
}
