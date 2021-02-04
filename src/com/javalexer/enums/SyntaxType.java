package com.javalexer.enums;

/**
 * Defines Token types used in lexing and parsing phases.
 */
public enum SyntaxType {
    WHITESPACE,

    STRING, CHAR,
    NUMBER, INTEGER, DOUBLE,

    ASSIGNMENT,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACK_SLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    PIPE, AND, OR, BANG,

    FALSE_KEYWORD, TRUE_KEYWORD,
    PUBLIC_KEYWORD,

    UNKNOWN, END;
}
