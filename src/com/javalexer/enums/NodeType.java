package com.javalexer.enums;

/**
 * Defines Node types used in type checking and evaluation.
 */
public enum NodeType {
    WHITESPACE_NODE,

    LITERAL,
    STRING, CHAR,
    NUMBER, INTEGER, DOUBLE,

    UNARY_EXPRESSION,
    BINARY_EXPRESSION, PARENTHESES_EXPRESSION,
    OPERATOR,

    ASSIGNMENT_NODE,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACK_SLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    UNKNOWN_NODE, END_NODE;
}
