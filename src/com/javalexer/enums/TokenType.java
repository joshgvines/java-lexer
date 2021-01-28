package com.javalexer.enums;

/**
 * Defines Token Types Used In Lexers And Parsers.
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
