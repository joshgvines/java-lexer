package com.javalexer.enums;

/**
 * Defines Token types used in lexing and parsing phases.
 */
public enum SyntaxType {
    WHITESPACE,

    STRING, CHARACTER,
    CHARACTER_KEYWORD,

    SEMICOLON, COLON,

    NUMBER,
    INTEGER_KEYWORD, BYTE_KEYWORD,
    DOUBLE_KEYWORD, FLOAT_KEYWORD,

    EQUALS,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACKSLASH,

    MULTI_BACKSLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    PIPE, AND, OR, BANG, AMPERSAND,
    EQUALS_COMPARE,

    FALSE_KEYWORD, TRUE_KEYWORD,
    PUBLIC_KEYWORD, PRIVATE_KEYWORD, PROTECTED_KEYWORD,

    RETURN_KEYWORD, VOID_KEYWORD,

    UNKNOWN, END;
}
