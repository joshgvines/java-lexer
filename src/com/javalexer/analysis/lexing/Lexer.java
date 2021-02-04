package com.javalexer.analysis.lexing;

import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.enums.SyntaxType;

import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.SyntaxType.*;
import static com.javalexer.enums.CodeFilter.*;

public class Lexer {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private char _char;
    private char[] charArray;
    private StringBuilder tokenString;
    private List<Token> tokens = new ArrayList<>();
    private int charArrayLength = 0;
    private int tokenPosition = 0;
    private int charPosition = 0;

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = removeComments(fileAsString);
        charArray = fileAsString.toCharArray();
        charArrayLength = charArray.length;
        while (charPosition < charArrayLength) {
            _char = charArray[charPosition];

            if (!keywordTokenFilter())
                characterTokenFilter();

            charPosition++;
        }
        tokens.add(new Token(END, "/0", -1));
        return tokens;
    }

    private String removeComments(String fileAsString) {
        fileAsString = fileAsString.replaceAll(MULTI_COMMENT.get(), "");
        return fileAsString.replaceAll(SINGLE_COMMENT.get() + NEW_LINE, "");
    }

    private boolean keywordTokenFilter() throws Exception {
        if (Character.isLetter(_char)) {
            tokenString = new StringBuilder().append(_char);
            while ((charPosition < charArrayLength - 1) && Character.isLetter(peek(1))) {
                    tokenString.append(charArray[++charPosition]);
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
        tokens.add(new Token(type, tokenString.toString(), tokenPosition++));
        return true;
    }

    private void characterTokenFilter() throws Exception {
        switch (_char) {
            case '"': appendUntil("\"", STRING); break;
            case '\'': appendUntil("'", CHAR); break;
            case ' ': tokens.add(new Token(WHITESPACE, " ", tokenPosition++)); break;
            case '{': tokens.add(new Token(OPEN_BRACE, "{", tokenPosition++)); break;
            case '}': tokens.add(new Token(CLOSE_BRACE, "}", tokenPosition++)); break;
            case '=': tokens.add(new Token(ASSIGNMENT, "=", tokenPosition++)); break;
            case '+': tokens.add(new Token(PLUS, "+", tokenPosition++)); break;
            case '/': tokens.add(new Token(FORWARD_SLASH, "/", tokenPosition++)); break;
            case '*': tokens.add(new Token(STAR, "*", tokenPosition++)); break;
            case '%': tokens.add(new Token(MODULO, "%", tokenPosition++)); break;
            case '-': tokens.add(new Token(MINUS, "-", tokenPosition++)); break;
            case '(': tokens.add(new Token(OPEN_PAREN, "(", tokenPosition++)); break;
            case ')': tokens.add(new Token(CLOSE_PAREN, ")", tokenPosition++)); break;
            case '!': tokens.add(new Token(BANG, "!", tokenPosition++)); break;
            case '|': buildDuplicateTokenType(OR); break;
            case '&': buildDuplicateTokenType(AND); break;
            default:
                if (Character.isDigit(_char)) {
                    appendNumberUntil();
                } else {
                    tokens.add(new Token(UNKNOWN, null, tokenPosition++));
                    Diagnostics.addLexicalDiagnostic("Found Unknown Token: " + tokens.get(tokens.size() - 1));
                }
        }
    }

    private void buildDuplicateTokenType(SyntaxType syntaxType) {
        if (peek(1) == _char && charPosition < charArrayLength) {
            tokens.add(new Token(syntaxType, String.valueOf(_char + _char), tokenPosition++));
        }
        tokens.add(new Token(UNKNOWN, String.valueOf(_char), tokenPosition++));
    }

    private char peek(int offset) {
        if (charPosition + offset < charArrayLength) {
             return charArray[charPosition + offset];
        }
        return _char;
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
        while (isNextNumberToken()) {
            if (_char == '.') {
                isDouble = true;
            }
            if ((tokenString.length() > 8 && !isDouble)
                    || (tokenString.length() > 12 && isDouble)) {
                throw new Exception("Number was to large");
            }
            tokenString.append(charArray[++charPosition]);
        }
        tokens.add(new Token(NUMBER, tokenString.toString(), tokenPosition++));
    }

    private boolean isNextNumberToken() {
        return charPosition < charArrayLength - 1
                && (Character.isDigit(peek(1)) || peek(0) == '.');
    }

    /**
     * TODO: Fix random whitespace tokens
     * @param key
     * @param syntaxType
     * @return
     */
    private boolean appendUntil(String key, SyntaxType syntaxType) {
        tokenString = new StringBuilder();
        tokenString.append(_char);
        while (charPosition < charArrayLength) {
            _char = charArray[++charPosition];
            if (key.equals(_char) || (";").equals(_char)) {
                tokenString.append(_char);
                return tokens.add(new Token(syntaxType, tokenString.toString(), tokenPosition++));
            } else if ((" ").equals(_char)) {
                tokens.add(new Token(WHITESPACE, " ", tokenPosition++));
            }
            tokenString.append(_char);
        }
        return tokens.add(new Token(syntaxType, tokenString.toString(), tokenPosition++));
    }
}
