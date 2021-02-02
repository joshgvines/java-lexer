package com.javalexer.enums;

/**
 * Defines Token types used in lexing and parsing phases.
 */
public enum TokenType {
    WHITESPACE,

    STRING, CHAR,
    NUMBER, INTEGER, DOUBLE,

    ASSIGNMENT,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACK_SLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    UNKNOWN, END;
}
