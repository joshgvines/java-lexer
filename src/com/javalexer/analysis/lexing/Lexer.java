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
    private List<Token> tokens;
    private TokenManager tokenManager;
    private int charArrayLength, charArraySize;
    private int tokenPosition, charPosition = 0;

    public Lexer() {
        tokens = new ArrayList<>();
        tokenManager = new TokenManager();
    }

    public List<Token> lex(String fileAsString) throws Exception {
        fileAsString = removeComments(fileAsString);
        charArray = fileAsString.toCharArray();
        charArrayLength = charArray.length;
        charArraySize = charArrayLength - 1;
        while (charPosition < charArrayLength) {
            _char = charArray[charPosition];
            if (!keywordTokenFilter()) {
                characterTokenFilter();
            }
            charPosition++;
        }
        tokens.add(new Token(END, "/0", -1));
        return tokens;
    }

    private String removeComments(String fileAsString) {
        fileAsString = fileAsString.replaceAll(MULTI_COMMENT.get(), "");
        return fileAsString.replaceAll(SINGLE_COMMENT.get() + NEW_LINE, "");
    }

    private boolean keywordTokenFilter() {
        if (Character.isLetter(_char)) {
            tokenString = new StringBuilder().append(_char);
            while (isNextLetter()) {
                tokenString.append(charArray[++charPosition]);
            }
            SyntaxType syntaxType = tokenManager.getTypeFromKeyword(tokenString.toString());
            return tokens.add(new Token(syntaxType, tokenString.toString(), tokenPosition++));
        }
        return false;
    }

    private boolean isNextLetter() {
        return (charPosition < charArraySize && Character.isLetter(peek(1)));
    }

    private void characterTokenFilter() throws Exception {
        SyntaxType syntaxType = tokenManager.getTypeFromCharacter(_char);
        String tokenValueString = String.valueOf(_char);
        if (syntaxType == null) {
            tokens.add(new Token(UNKNOWN, tokenValueString, tokenPosition++));
            Diagnostics.addLexicalDiagnostic("Found Unknown Token: " + tokens.get(tokens.size() - 1));
            return;
        }
        switch (syntaxType) {
            case STRING:
            case CHARACTER:
                appendUntil(tokenValueString, syntaxType);
                return;
            case NUMBER:
                appendNumberUntil();
                return;
            case PIPE:
                if (checkDuplicateCharacter()) {
                    syntaxType = OR;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case AMPERSAND:
                if (checkDuplicateCharacter()) {
                    syntaxType = AND;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case EQUALS:
                if (checkDuplicateCharacter()) {
                    syntaxType = EQUALS_COMPARE;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case BACKSLASH:
                if (checkDuplicateCharacter()) {
                    syntaxType = MULTI_BACKSLASH;
                    tokenValueString = tokenValueString + tokenValueString;
                }
        }
        tokens.add(new Token(syntaxType, tokenValueString, tokenPosition++));
    }

    private boolean checkDuplicateCharacter() {
        if (peek(1) == _char && charPosition < charArraySize) {
            charPosition++;
            return true;
        }
        return false;
    }

    private char peek(int offset) {
        if (charPosition + offset < charArrayLength) {
            return charArray[charPosition + offset];
        }
        return _char;
    }

    /**
     * TODO: Better error when integer is too large.
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
        return (charPosition < charArraySize && (Character.isDigit(peek(1)) || peek(1) == '.'));
    }

    private boolean appendUntil(String key, SyntaxType syntaxType) {
        tokenString = new StringBuilder();
        tokenString.append(_char);
        while (isNextCharacterToken(key)) {
            tokenString.append(charArray[++charPosition]);
        }
        return tokens.add(new Token(syntaxType, tokenString.toString(), tokenPosition++));
    }

    private boolean isNextCharacterToken(String key) {
        return charPosition < charArraySize && !key.equals(_char);
    }
}
