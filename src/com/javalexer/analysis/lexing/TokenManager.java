package com.javalexer.analysis.lexing;

import com.javalexer.enums.SyntaxType;

import java.util.HashMap;

import static com.javalexer.enums.SyntaxType.*;

public final class TokenManager {

    private HashMap<Character, SyntaxType> tokenTypes;
    private HashMap<String, SyntaxType> keywordTypes;

    public TokenManager() {
        initCharacterTypes();
        initKeywordTypes();
    }

    public SyntaxType getTypeFromCharacter(char _char) {
        if (Character.isDigit(_char) || _char == '.') {
            return NUMBER;
        }
        return tokenTypes.get(_char);
    }

    public SyntaxType getTypeFromKeyword(String keyword) {
        return keywordTypes.get(keyword);
    }

    private void initCharacterTypes() {
        tokenTypes = new HashMap<>() {{
            put(' ', WHITESPACE);
            put('{', OPEN_BRACE); put('}', CLOSE_BRACE);
            put('(', OPEN_PAREN); put(')', CLOSE_PAREN);

            put(';', SEMICOLON); put(':', COLON);

            put('=', EQUALS);

            put('\"', STRING);
            put('\'', CHARACTER);

            put('+', PLUS);
            put('-', MINUS);
            put('/', FORWARD_SLASH); put('\\', BACKSLASH);
            put('*', STAR);

            put('&', AMPERSAND);
            put('|', PIPE);

            put('%', MODULO);
            put('!', BANG);
        }};
    }

    private void initKeywordTypes() {
        keywordTypes = new HashMap<>() {{
            put("false", FALSE_KEYWORD);
            put("true", TRUE_KEYWORD);

            put("public", PUBLIC_KEYWORD);
            put("private", PRIVATE_KEYWORD);
            put("protected", PROTECTED_KEYWORD);

            put("double", DOUBLE_KEYWORD);
            put("float", FLOAT_KEYWORD);
            put("int", INTEGER_KEYWORD);
            put("byte", BYTE_KEYWORD);
            put("char", CHARACTER_KEYWORD);

            put("void", VOID_KEYWORD);
            put("return", RETURN_KEYWORD);
        }};
    }

}
