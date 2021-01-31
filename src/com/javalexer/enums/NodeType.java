package com.javalexer.enums;

/**
 * Defines Bound Node Types Used In Second Tree Type Binding.
 */
public enum NodeType {
    WHITESPACE_NODE,

    STRING, CHAR,
    NUMBER, INTEGER, DOUBLE,

    UNARY_EXPRESSION,
    BINARY_EXPRESSION,
    LITERAL,
    PARENTHESES_EXPRESSION,
    OPERATOR,

    ASSIGNMENT_NODE,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACK_SLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    UNKNOWN_NODE, END_NODE;
}
