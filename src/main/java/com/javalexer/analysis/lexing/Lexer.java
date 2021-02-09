package com.javalexer.analysis.lexing;

import com.javalexer.enums.CodeFilter;
import com.javalexer.diagnostics.Diagnostics;
import com.javalexer.enums.SyntaxType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.javalexer.enums.SyntaxType.*;

public class Lexer {

    private static final Logger log = LogManager.getLogger(Lexer.class);

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
        fileAsString = fileAsString.replaceAll(CodeFilter.MULTI_COMMENT.get(), "");
        return fileAsString.replaceAll(CodeFilter.SINGLE_COMMENT.get() + NEW_LINE, "");
    }

    /**
     * TODO: Refactor
     *
     * @return boolean
     * successfully found and built a keyword token.
     */
    private boolean keywordTokenFilter() {
        if (Character.isLetter(_char)) {
            tokenString = new StringBuilder().append(_char);
            while (isNextLetter()) {
                tokenString.append(charArray[++charPosition]);
            }
            SyntaxType syntaxType = tokenManager.getTypeFromKeyword(tokenString.toString());
            if (syntaxType == FALSE_KEYWORD) {
                return tokens.add(new Token(syntaxType, false, tokenPosition++));
            } else if (syntaxType == TRUE_KEYWORD) {
                return tokens.add(new Token(syntaxType, true, tokenPosition++));
            }
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
            log.info("Found Unknown Token: Token Object:  {}  ", tokens.get(tokens.size() - 1));
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
            case BANG:
                if (checkNextCharacter('=')) {
                    syntaxType = BANG_NOT_EQUALS;
                    tokenValueString = tokenValueString + "=";
                }
                break;
            case PIPE:
                if (checkNextCharacter(_char)) {
                    syntaxType = OR;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case AMPERSAND:
                if (checkNextCharacter(_char)) {
                    syntaxType = AND;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case EQUALS:
                if (checkNextCharacter(_char)) {
                    syntaxType = EQUALS_COMPARE;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
            case BACKSLASH:
                if (checkNextCharacter(_char)) {
                    syntaxType = MULTI_BACKSLASH;
                    tokenValueString = tokenValueString + tokenValueString;
                }
                break;
        }
        tokens.add(new Token(syntaxType, tokenValueString, tokenPosition++));
    }

    private boolean checkNextCharacter(char expected) {
        if (peek(1) == expected && charPosition < charArraySize) {
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
