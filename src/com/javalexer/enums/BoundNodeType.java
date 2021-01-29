package com.javalexer.enums;

/**
 * Defines Bound Node Types Used In Second Tree Type Binding.
 */
public enum BoundNodeType {
    WHITESPACE_NODE,

    STRING, CHAR,
    NUMBER_NODE, INTEGER, DOUBLE,

    ASSIGNMENT_NODE,
    PLUS, MINUS, STAR, MODULO,
    FORWARD_SLASH, BACK_SLASH,

    OPEN_PAREN, CLOSE_PAREN,
    OPEN_BRACE, CLOSE_BRACE,

    UNKNOWN_NODE, END_NODE;
}
